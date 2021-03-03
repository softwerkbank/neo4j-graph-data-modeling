package ch.swb.graphgenerator.graph;

import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;

import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.nodes.Methodology;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Technology;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;
import ch.swb.graphgenerator.graph.model.relationships.PassedExam;

public class GraphDataRepository {

	public void persist(GraphData graph) {
		Neo4JNativeElementIdProvider idProvider = new Neo4JNativeElementIdProvider();
		try (Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "Neo4jAdmin4711"));
				Neo4JGraph neo4jGraph = new Neo4JGraph(driver, "skillsight", idProvider, idProvider)) {
			persistCompanies(neo4jGraph, graph);
			persistCertificates(neo4jGraph, graph);
			persistTechnologies(neo4jGraph, graph);
			persistMethodologies(neo4jGraph, graph);
			persistCourses(neo4jGraph, graph);
			persistProjects(neo4jGraph, graph);
			persistEmployees(neo4jGraph, graph);
		}
	}

	private void persistCompanies(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Company company : graph.getCompanies()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				g.addV(Company.LABEL)
						.property(Company.KEY_ID, company.getId().toString())
						.property(Company.KEY_NAME, company.getName())
						.property(Company.KEY_INDUSTRY, company.getIndustry())
						.next();

				transaction.commit();
			}
		}
	}

	private void persistCertificates(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Certificate certificate : graph.getCertificates()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				GraphTraversal<Vertex, Vertex> traversal = g.addV(Certificate.LABEL)
						.property(Certificate.KEY_ID, certificate.getId().toString())
						.property(Certificate.KEY_NAME, certificate.getName())
						.property(Certificate.KEY_AUTHORITY, certificate.getAuthority());

				if (certificate.getValidity() != null) {
					traversal.property(Certificate.KEY_VALIDITY, certificate.getValidity());
				}

				traversal.next();
				transaction.commit();
			}
		}
	}

	private void persistTechnologies(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Technology knowledge : graph.getTechnologies()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				String tags = knowledge.getTags().stream().collect(Collectors.joining(","));
				GraphTraversal<Vertex, Vertex> traversal = g.addV(Technology.LABEL)
						.property(Technology.KEY_ID, knowledge.getId().toString())
						.property(Technology.KEY_NAME, knowledge.getName());

				if (knowledge.getDescription() != null) {
					traversal.property(Technology.KEY_DESCRIPTION, knowledge.getDescription());
				}

				if (!tags.isBlank()) {
					traversal.property(Technology.KEY_TAGS, tags);
				}

				traversal.next();
				transaction.commit();
			}
		}
	}

	private void persistMethodologies(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Methodology skill : graph.getMethodologies()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				String tags = skill.getTags().stream().collect(Collectors.joining(","));
				GraphTraversal<Vertex, Vertex> traversal = g.addV(Methodology.LABEL)
						.property(Methodology.KEY_ID, skill.getId().toString())
						.property(Methodology.KEY_NAME, skill.getName());

				if (skill.getDescription() != null) {
					traversal.property(Methodology.KEY_DESCRIPTION, skill.getDescription());
				}

				if (!tags.isBlank()) {
					traversal.property(Methodology.KEY_TAGS, tags);
				}

				traversal.next();
				transaction.commit();
			}
		}
	}

	private void persistCourses(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Course course : graph.getCourses()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				GraphTraversal<Vertex, Vertex> traversal = g.addV(Course.LABEL)
						.property(Course.KEY_ID, course.getId().toString())
						.property(Course.KEY_NAME, course.getName())
						.property(Course.KEY_DURATION, course.getDuration())
						.property(Course.KEY_ORGANIZER, course.getOrganizer());

				if (course.getDescription() != null) {
					traversal.property(Course.KEY_DESCRIPTION, course.getDescription());
				}

				if (course.getPlatform() != null) {
					traversal.property(Course.KEY_PLATFORM, course.getPlatform());
				}

				Vertex courseNode = traversal.next();

				for (String transferredKnowledge : course.getKnowledges()) {
					// check if the course transfers knowledge of a technology or methodology
					GraphTraversal<Vertex, Vertex> technologyTraversal = g.V().has(Technology.LABEL, Technology.KEY_NAME, transferredKnowledge);
					if (technologyTraversal.hasNext()) {
						Vertex technologyNode = technologyTraversal.next();
						g.addE(Constants.COURSE_TECHNOLOGY_METHODOLOGY_LABEL).from(courseNode).to(technologyNode).next();
					} else {
						GraphTraversal<Vertex, Vertex> methodologyTraversal = g.V().has(Methodology.LABEL, Methodology.KEY_NAME, transferredKnowledge);
						if (methodologyTraversal.hasNext()) {
							Vertex methodologyNode = methodologyTraversal.next();
							g.addE(Constants.COURSE_TECHNOLOGY_METHODOLOGY_LABEL).from(courseNode).to(methodologyNode).next();
						}
					}
				}

				transaction.commit();
			}
		}
	}

	private void persistProjects(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Project project : graph.getProjects()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				Vertex projectNode = g.addV(Project.LABEL)
						.property(Project.KEY_ID, project.getId().toString())
						.property(Project.KEY_NAME, project.getName())
						.property(Project.KEY_DESCRIPTION, project.getDescription())
						.property(Project.KEY_WORKING_LANGUAGE, project.getWorkingLanguage())
						.next();

				for (Technology technology : project.getUsedTechnologies()) {
					Vertex technologyNode = g.V().has(Technology.LABEL, Technology.KEY_ID, technology.getId().toString()).next();
					g.addE(Constants.PROJECT_TECHNOLOGY_LABEL).from(projectNode).to(technologyNode).next();
				}

				for (Methodology methodology : project.getUsedMethodologies()) {
					Vertex methodologyNode = g.V().has(Methodology.LABEL, Methodology.KEY_ID, methodology.getId().toString()).next();
					g.addE(Constants.PROJECT_TECHNOLOGY_LABEL).from(projectNode).to(methodologyNode).next();
				}

				transaction.commit();
			}
		}
	}

	private void persistEmployees(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Employee employee : graph.getEmployees()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				Vertex employeeNode = g.addV(Employee.LABEL)
						.property(Employee.KEY_ID, employee.getId().toString())
						.property(Employee.KEY_FIRSTNAME, employee.getFirstname())
						.property(Employee.KEY_LASTNAME, employee.getLastname())
						.property(Employee.KEY_LOGINNAME, employee.getLoginname())
						.property(Employee.KEY_BIRTHDAY, employee.getDateOfBirth().format(DateTimeFormatter.ISO_DATE))
						.next();

				for (Employment employment : employee.getEmployments()) {
					Vertex employmentNode = g.addV(Employment.LABEL)
							.property(Employment.KEY_START, employment.getStart().format(DateTimeFormatter.ISO_DATE))
							.property(Employment.KEY_END, employment.getEnd() != null ? employment.getEnd().format(DateTimeFormatter.ISO_DATE) : null)
							.property(Employment.KEY_POSITION, employment.getPosition())
							.next();

					g.addE(Constants.EMPLOYEE_EMPLOYMENT_LABEL).from(employeeNode).to(employmentNode).next();

					Company company = employment.getCompany();
					Vertex companyNode = g.V().has(Company.LABEL, Company.KEY_ID, company.getId().toString()).next();
					g.addE(Constants.EMPLOYMENT_COMPANY_LABEL).from(employmentNode).to(companyNode).next();

					for (AssignedProject assignedProject : employment.getAssignedProjects()) {
						Vertex projectNode = g.V().has(assignedProject.getTo().getNodeLabel(),
								Project.KEY_ID,
								assignedProject.getTo().getNodeId().toString()).next();

						String roles = assignedProject.getRoles().stream().map(r -> r.getName()).collect(Collectors.joining(","));

						g.addE(AssignedProject.LABEL).from(employmentNode).to(projectNode)
								.property(AssignedProject.KEY_START, assignedProject.getStart().format(DateTimeFormatter.ISO_DATE))
								.property(AssignedProject.KEY_END,
										assignedProject.getEnd() != null ? assignedProject.getEnd().format(DateTimeFormatter.ISO_DATE) : null)
								.property(AssignedProject.KEY_WORKLOAD, String.valueOf(assignedProject.getWorkload()))
								.property(AssignedProject.KEY_ROLES, roles)
								.next();
					}
				}

				for (PassedExam passedExam : employee.getPassedExams()) {
					Vertex certificateNode = g.V().has(passedExam.getTo().getNodeLabel(),
							Certificate.KEY_ID,
							passedExam.getTo().getNodeId().toString()).next();

					g.addE(PassedExam.LABEL).from(employeeNode).to(certificateNode)
							.property(PassedExam.KEY_EXAMINATION_DATE, passedExam.getExaminationDate().format(DateTimeFormatter.ISO_DATE))
							.property(PassedExam.KEY_EXAMINATION_INSTITUTE, passedExam.getExaminationInstitute())
							.property(PassedExam.KEY_EXAM, passedExam.getExam())
							.next();
				}

				for (ParticipatedCourse participatedCourse : employee.getParticipatedCourses()) {
					Vertex courseNode = g.V().has(participatedCourse.getTo().getNodeLabel(),
							Course.KEY_ID,
							participatedCourse.getTo().getNodeId().toString()).next();

					g.addE(ParticipatedCourse.LABEL).from(employeeNode).to(courseNode)
							.property(ParticipatedCourse.KEY_START_DATE, participatedCourse.getStartDate().format(DateTimeFormatter.ISO_DATE))
							.property(ParticipatedCourse.KEY_END_DATE, participatedCourse.getEndDate().format(DateTimeFormatter.ISO_DATE))
							.next();
				}

				transaction.commit();
			}
		}
	}
}

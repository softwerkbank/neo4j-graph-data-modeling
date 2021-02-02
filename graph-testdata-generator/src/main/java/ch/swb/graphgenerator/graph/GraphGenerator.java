package ch.swb.graphgenerator.graph;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import ch.swb.graphgenerator.graph.generator.nodes.EmployeeNodeGenerator;
import ch.swb.graphgenerator.graph.generator.nodes.EmploymentNodeGenerator;
import ch.swb.graphgenerator.graph.generator.nodes.FixedNodeProvider;
import ch.swb.graphgenerator.graph.generator.relationships.AssignedProjectGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.ParticipatedCourseGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.PassedExamGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;
import ch.swb.graphgenerator.graph.model.relationships.PassedExam;
import jakarta.inject.Inject;

public class GraphGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphGenerator.class);

	private final GraphParameters parameters;
	private final GraphData graph;
	private final Faker faker;
	private final FixedNodeProvider fixedNodeProvider;
	private final EmployeeNodeGenerator employeeGenerator;
	private final EmploymentNodeGenerator employmentGenerator;
	private final AssignedProjectGenerator assignedProjectGenerator;
	private final PassedExamGenerator passedExamGenerator;
	private final ParticipatedCourseGenerator participatedCourseGenerator;

	@Inject
	public GraphGenerator(GraphParameters parameters, FixedNodeProvider fixedNodeProvider, EmployeeNodeGenerator employeeGenerator,
			EmploymentNodeGenerator employmentGenerator, AssignedProjectGenerator assignedProjectGenerator, PassedExamGenerator passedExamGenerator,
			ParticipatedCourseGenerator participatedCourseGenerator) {
		this.parameters = parameters;
		this.graph = new GraphData();
		this.faker = new Faker(Locale.GERMANY);
		this.fixedNodeProvider = fixedNodeProvider;
		this.employeeGenerator = employeeGenerator;
		this.employmentGenerator = employmentGenerator;
		this.assignedProjectGenerator = assignedProjectGenerator;
		this.passedExamGenerator = passedExamGenerator;
		this.participatedCourseGenerator = participatedCourseGenerator;
	}

	public GraphData generateGraph() {
		generateCompanies();
		generateCertificates();
		generateKnowledges();
		generateSkills();
		generateCourses();
		generateProjects();
		generateEmployees();
		generateEmployments();
		generateAssignedProjects();
		generatePassedExams();
		generateParticipatedCourses();
		return graph;
	}

	private void generateEmployees() {
		List<Employee> employees = employeeGenerator.generateNodes(parameters.getNumberOfEmployees());
		graph.addEmployees(employees);
		LOGGER.info("Generated {} employees", graph.getEmployees().size());
	}

	private void generateCompanies() {
		for (int i = 0; i < parameters.getNumberOfCompanies() - 1; i++) {
			Company company = new Company(UUID.randomUUID(), faker.company().name(), faker.company().industry());
			graph.addCompany(company);
		}
		graph.addCompany(fixedNodeProvider.getCompanyForLastEmployment());
		LOGGER.info("Generated {} companies", graph.getCompanies().size());
	}

//	private void generateCustomers() {
//		List<Customer> customers = new ArrayList<>();
//		for (int i = 0; i < Constants.NUMBER_OF_CUSTOMERS; i++) {
//			Customer customer = new Customer(UUID.randomUUID(), faker.company().name(), faker.company().industry());
//			customers.add(customer);
//		}
//	}

	private void generateProjects() {
		for (int i = 0; i < parameters.getNumberOfProjects(); i++) {
			Project project = new Project(UUID.randomUUID(), faker.superhero().name(), faker.lorem().characters(2000, 4000), i % 2 == 0 ? "german" : "english");
			graph.addProject(project);
		}
		LOGGER.info("Generated {} projects", graph.getProjects().size());
	}

	private void generateEmployments() {
		for (Employee employee : graph.getEmployees()) {
			List<Employment> employments = employmentGenerator.generateEmploymentsForEmployee(employee.getDateOfBirth(),
					graph.getCompanies(),
					parameters.getAverageEmploymentPeriod(),
					parameters.getFirstEmploymentAfter(),
					parameters.getJitterFirstEmployment());
			employee.addEmployments(employments);
			LOGGER.info("Generated {} employments for employee {}", employee.getEmployments().size(), employee.getLoginname());
		}
	}

	private void generateAssignedProjects() {
		for (Employee employee : graph.getEmployees()) {
			for (Employment employment : employee.getEmployments()) {
				List<AssignedProject> assignedProjects = assignedProjectGenerator.generateAssignedProjects(graph.getProjects(),
						employment,
						parameters.getMinPeriodProjectAssignment(),
						parameters.getMaxPeriodProjectAssignment(),
						parameters.getMaxRolesProject());
				employment.addAssignedProjects(assignedProjects);
				// TODO: LOGGER.info("Generated {} employees", graph.getEmployees().size());
			}
		}
	}

	private void generatePassedExams() {
		for (Employee employee : graph.getEmployees()) {
			List<PassedExam> passedExams = passedExamGenerator.generatePassedExams(employee,
					employee.getFirstEmployment().getStart(),
					parameters.getCertifcateEveryNumberOfYears());
			employee.addPassedExams(passedExams);
			LOGGER.info("Generated {} passed exams for employee {}", employee.getPassedExams().size(), employee.getLoginname());
		}
	}

	private void generateParticipatedCourses() {
		for (Employee employee : graph.getEmployees()) {
			List<ParticipatedCourse> participatedCourses = participatedCourseGenerator.generateParticipatedCourses(employee,
					employee.getFirstEmployment().getStart(),
					parameters.getTrainingDaysPerYear());
			employee.addParticipatedCourses(participatedCourses);
			LOGGER.info("Generated {} participated courses for employee {}", employee.getParticipatedCourses().size(), employee.getLoginname());
		}
	}

	private void generateCertificates() {
		int toIndex = Math.min(parameters.getNumberOfCertificates(), fixedNodeProvider.getCertificates().size());
		graph.addCertificates(fixedNodeProvider.getCertificates().subList(0, toIndex));
		LOGGER.info("Generated {} certificates", graph.getCertificates().size());
	}

	private void generateKnowledges() {
		int toIndex = Math.min(parameters.getNumberOfKnowledges(), fixedNodeProvider.getKnowledges().size());
		graph.addKnowledges(fixedNodeProvider.getKnowledges().subList(0, toIndex));
		LOGGER.info("Generated {} knowledges", graph.getKnowledges().size());
	}

	private void generateSkills() {
		int toIndex = Math.min(parameters.getNumberOfSkills(), fixedNodeProvider.getSkills().size());
		graph.addSkills(fixedNodeProvider.getSkills().subList(0, toIndex));
		LOGGER.info("Generated {} skills", graph.getSkills().size());
	}

	private void generateCourses() {
		int toIndex = Math.min(parameters.getNumberOfKnowledges(), fixedNodeProvider.getCourses().size());
		graph.addCourses(fixedNodeProvider.getCourses().subList(0, toIndex));
		LOGGER.info("Generated {} courses", graph.getCourses().size());
	}

}

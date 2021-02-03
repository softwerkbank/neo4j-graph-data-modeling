package ch.swb.graphgenerator.graph;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.generator.nodes.NodeGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.AssignedProjectGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.ParticipatedCourseGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.PassedExamGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;
import ch.swb.graphgenerator.graph.model.relationships.PassedExam;
import jakarta.inject.Inject;

public class GraphGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphGenerator.class);

	private final GraphParameters parameters;
	private final NodeGenerator nodeGenerator;
	private final AssignedProjectGenerator assignedProjectGenerator;
	private final PassedExamGenerator passedExamGenerator;
	private final ParticipatedCourseGenerator participatedCourseGenerator;

	private GraphData graph;

	@Inject
	public GraphGenerator(GraphParameters parameters, NodeGenerator nodeGenerator, AssignedProjectGenerator assignedProjectGenerator,
			PassedExamGenerator passedExamGenerator,
			ParticipatedCourseGenerator participatedCourseGenerator) {
		this.parameters = parameters;
		this.nodeGenerator = nodeGenerator;
		this.assignedProjectGenerator = assignedProjectGenerator;
		this.passedExamGenerator = passedExamGenerator;
		this.participatedCourseGenerator = participatedCourseGenerator;
	}

	public GraphData generateGraph() {
		this.graph = nodeGenerator.generateGraphWithNodes();
		generateAssignedProjects();
		generatePassedExams();
		generateParticipatedCourses();
		return graph;
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

}

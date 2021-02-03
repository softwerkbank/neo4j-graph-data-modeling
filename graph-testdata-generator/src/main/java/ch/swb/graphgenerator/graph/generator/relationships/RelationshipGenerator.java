package ch.swb.graphgenerator.graph.generator.relationships;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.GraphParameters;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;
import ch.swb.graphgenerator.graph.model.relationships.PassedExam;
import jakarta.inject.Inject;

public class RelationshipGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(RelationshipGenerator.class);

	private final GraphParameters parameters;
	private final AssignedProjectGenerator assignedProjectGenerator;
	private final PassedExamGenerator passedExamGenerator;
	private final ParticipatedCourseGenerator participatedCourseGenerator;

	@Inject
	public RelationshipGenerator(GraphParameters parameters, AssignedProjectGenerator assignedProjectGenerator, PassedExamGenerator passedExamGenerator,
			ParticipatedCourseGenerator participatedCourseGenerator) {
		this.parameters = parameters;
		this.assignedProjectGenerator = assignedProjectGenerator;
		this.passedExamGenerator = passedExamGenerator;
		this.participatedCourseGenerator = participatedCourseGenerator;
	}

	public void addRelationshipsToGraph(GraphData graph) {
		generateAssignedProjects(graph);
		generatePassedExams(graph);
		generateParticipatedCourses(graph);
	}

	private void generateAssignedProjects(GraphData graph) {
		for (Employee employee : graph.getEmployees()) {
			for (Employment employment : employee.getEmployments()) {
				List<AssignedProject> assignedProjects = assignedProjectGenerator.generateAssignedProjects(graph.getProjects(),
						employment,
						parameters.getMinPeriodProjectAssignment(),
						parameters.getMaxPeriodProjectAssignment(),
						parameters.getMaxRolesProject());
				employment.addAssignedProjects(assignedProjects);
				LOGGER.info("Generated {} assigned projects for employment from {} to {} of employee {}",
						assignedProjects.size(), employment.getStart(), employment.getEnd(), employee.getLoginname());
			}
		}
	}

	private void generatePassedExams(GraphData graph) {
		for (Employee employee : graph.getEmployees()) {
			List<PassedExam> passedExams = passedExamGenerator.generatePassedExams(employee,
					employee.getFirstEmployment().getStart(),
					parameters.getCertifcateEveryNumberOfYears());
			employee.addPassedExams(passedExams);
			LOGGER.info("Generated {} passed exams for employee {}", employee.getPassedExams().size(), employee.getLoginname());
		}
	}

	private void generateParticipatedCourses(GraphData graph) {
		for (Employee employee : graph.getEmployees()) {
			List<ParticipatedCourse> participatedCourses = participatedCourseGenerator.generateParticipatedCourses(employee,
					employee.getFirstEmployment().getStart(),
					parameters.getTrainingDaysPerYear());
			employee.addParticipatedCourses(participatedCourses);
			LOGGER.info("Generated {} participated courses for employee {}", employee.getParticipatedCourses().size(), employee.getLoginname());
		}
	}

}

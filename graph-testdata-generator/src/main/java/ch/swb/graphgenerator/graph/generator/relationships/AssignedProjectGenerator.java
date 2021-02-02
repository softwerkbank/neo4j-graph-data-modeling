package ch.swb.graphgenerator.graph.generator.relationships;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ch.swb.graphgenerator.graph.generator.nodes.FixedNodeProvider;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Role;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.RelationshipNode;
import jakarta.inject.Inject;

public class AssignedProjectGenerator {

	private FixedNodeProvider fixedNodeProvider;

	@Inject
	public AssignedProjectGenerator(FixedNodeProvider fixedNodeProvider) {
		this.fixedNodeProvider = fixedNodeProvider;
	}

	public List<AssignedProject> generateAssignedProjects(List<Project> projects, Employment employment, Period minPeriodProjectAssignment,
			Period maxPeriodProjectAssignment, int maxRolesProject) {
		List<AssignedProject> assignedProjects = new ArrayList<>();

		RelationshipNode from = new RelationshipNode(Employment.LABEL, employment.getId());
		Period employmentPeriod = employment.getEnd() != null ? Period.between(employment.getStart(), employment.getEnd())
				: Period.between(employment.getStart(), LocalDate.now());
		Period difference = employmentPeriod;
		LocalDate lastEndDateProject = null;

		int minMonthsProjectAssignment = (int) minPeriodProjectAssignment.toTotalMonths();
		int maxMonthsProjectAssignment = (int) maxPeriodProjectAssignment.toTotalMonths();

		do {
			int projectPeriodInMonths = ThreadLocalRandom.current().nextInt(minMonthsProjectAssignment, maxMonthsProjectAssignment + 1);
			difference = difference.minusMonths(projectPeriodInMonths).normalized();

			int workload = ThreadLocalRandom.current().nextInt(4, 11) * 10;
			LocalDate projectStart = lastEndDateProject == null ? employment.getStart() : lastEndDateProject.plusDays(1);
			LocalDate projectEnd = difference.isNegative() || difference.isZero() ? employment.getEnd() : projectStart.plusMonths(projectPeriodInMonths);

			Project project = projects.get(ThreadLocalRandom.current().nextInt(projects.size()));
			RelationshipNode to = new RelationshipNode(Project.LABEL, project.getId());

			assignedProjects.add(new AssignedProject(from, to, projectStart, projectEnd, workload, getRandomRoles(maxRolesProject)));

		} while (!difference.isNegative());

		return assignedProjects;
	}

	private Role[] getRandomRoles(int maxRolesProject) {
		int numberOfRoles = ThreadLocalRandom.current().nextInt(1, maxRolesProject + 1);
		Role[] roles = new Role[numberOfRoles];
		for (int index = 0; index < numberOfRoles; index++) {
			roles[index] = fixedNodeProvider.getRandomRole();
		}
		return roles;
	}
}

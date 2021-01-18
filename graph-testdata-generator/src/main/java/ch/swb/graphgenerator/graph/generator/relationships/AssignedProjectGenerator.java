package ch.swb.graphgenerator.graph.generator.relationships;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.model.Employment;
import ch.swb.graphgenerator.graph.model.Project;
import ch.swb.graphgenerator.graph.model.Role;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;
import ch.swb.graphgenerator.graph.model.relationships.RelationshipNode;

public class AssignedProjectGenerator {

	private final Employment employment;
	private final int minMonthsProjectAssignment;
	private final int maxMonthsProjectAssignment;
	private final int maxRolesProject;

	public AssignedProjectGenerator(Employment employment, Period minPeriodProjectAssignment, Period maxPeriodProjectAssignment, int maxRolesProject) {
		this.maxRolesProject = maxRolesProject;
		this.employment = employment;
		this.minMonthsProjectAssignment = (int) minPeriodProjectAssignment.toTotalMonths();
		this.maxMonthsProjectAssignment = (int) maxPeriodProjectAssignment.toTotalMonths();
	}

	public List<AssignedProject> generateAssignedProjects(List<Project> projects) {
		List<AssignedProject> assignedProjects = new ArrayList<>();

		RelationshipNode from = new RelationshipNode(Employment.LABEL, employment.getId());
		Period employmentPeriod = Period.between(employment.getStart(), employment.getEnd());
		Period difference = employmentPeriod;
		LocalDate lastEndDateProject = null;

		do {
			int projectPeriodInMonths = ThreadLocalRandom.current().nextInt(minMonthsProjectAssignment, maxMonthsProjectAssignment + 1);
			difference = difference.minusMonths(projectPeriodInMonths).normalized();

			int workload = ThreadLocalRandom.current().nextInt(4, 11) * 10;
			LocalDate projectStart = lastEndDateProject == null ? employment.getStart() : lastEndDateProject.plusDays(1);
			LocalDate projectEnd = difference.isNegative() || difference.isZero() ? employment.getEnd() : projectStart.plusMonths(projectPeriodInMonths);

			Project project = projects.get(ThreadLocalRandom.current().nextInt(projects.size()));
			RelationshipNode to = new RelationshipNode(Project.LABEL, project.getId());

			assignedProjects.add(new AssignedProject(from, to, projectStart, projectEnd, workload, getRandomRoles()));

		} while (!difference.isNegative());

		return assignedProjects;
	}

	private Role[] getRandomRoles() {
		int numberOfRoles = ThreadLocalRandom.current().nextInt(1, maxRolesProject + 1);
		Role[] roles = new Role[numberOfRoles];
		for (int index = 0; index < numberOfRoles; index++) {
			roles[index] = SpecialNodeProvider.getInstance().getRandomRole();
		}
		return roles;
	}
}

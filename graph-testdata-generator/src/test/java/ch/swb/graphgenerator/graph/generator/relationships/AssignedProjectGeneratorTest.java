package ch.swb.graphgenerator.graph.generator.relationships;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.relationships.AssignedProjectGenerator;
import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Employment;
import ch.swb.graphgenerator.graph.model.Project;
import ch.swb.graphgenerator.graph.model.relationships.AssignedProject;

@DisplayName("Testing the generation of \"ASSIGNED_PROJECT\" relationships")
class AssignedProjectGeneratorTest {

	private static List<Project> projects = new ArrayList<>();

	static {
		projects.add(new Project(UUID.randomUUID(), "Project 1", "Description 1", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 2", "Description 2", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 3", "Description 3", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 4", "Description 4", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 5", "Description 5", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 6", "Description 6", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 7", "Description 7", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 8", "Description 8", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 9", "Description 9", "english"));
		projects.add(new Project(UUID.randomUUID(), "Project 10", "Description 10", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 11", "Description 11", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 12", "Description 12", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 13", "Description 13", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 14", "Description 14", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 15", "Description 15", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 16", "Description 16", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 17", "Description 17", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 18", "Description 18", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 19", "Description 19", "german"));
		projects.add(new Project(UUID.randomUUID(), "Project 20", "Description 20", "german"));
	}

	private AssignedProjectGenerator testee;

	@Test
	@DisplayName("When generating assigned projects for an employment with start and end date then all assigned projects have start and end dates")
	void when_generateAssignedProjectsForEmploymentWithEnd_then_allAssignedProjectsHaveStartAndEndDates() {
		// Arrange
		Employment employment = new Employment(UUID.randomUUID(),
				LocalDate.of(2013, 5, 1),
				LocalDate.of(2019, 2, 28),
				"Software Engineer",
				new Company(UUID.randomUUID(), "Test Inc.", "IT"));
		testee = new AssignedProjectGenerator(employment, Period.ofMonths(6), Period.ofMonths(18), 3);

		// Act
		List<AssignedProject> assignedProjects = testee.generateAssignedProjects(projects);

		// Assert
		assertThat(assignedProjects).hasSizeGreaterThanOrEqualTo(3);
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getRoles()).hasSizeLessThanOrEqualTo(3));
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getFrom().getNodeId()).isEqualTo(employment.getId()));
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getTo()).isNotNull());
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getStart()).isNotNull());
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getEnd()).isNotNull());
	}

	@Test
	@DisplayName("When generating assigned projects for an employment without end date then the last assigned project has no end date")
	void when_generateAssignedProjectsForEmploymentWithoutEnd_then_lastAssignedProjectHasNoEndDate() {
		// Arrange
		Employment employment = new Employment(UUID.randomUUID(),
				LocalDate.of(2015, 7, 1),
				null,
				"Software Engineer",
				new Company(UUID.randomUUID(), "Test Inc.", "IT"));
		testee = new AssignedProjectGenerator(employment, Period.ofMonths(6), Period.ofMonths(18), 3);

		// Act
		List<AssignedProject> assignedProjects = testee.generateAssignedProjects(projects);

		// Assert
		assertThat(assignedProjects).hasSizeGreaterThanOrEqualTo(3);
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getRoles()).hasSizeLessThanOrEqualTo(3));
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getFrom().getNodeId()).isEqualTo(employment.getId()));
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getTo()).isNotNull());
		assertThat(assignedProjects).allSatisfy(a -> assertThat(a.getStart()).isNotNull());
		assertThat(assignedProjects.get(assignedProjects.size() - 1).getEnd()).isNull();
	}
}

package ch.swb.graphgenerator.graph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.EmployeeNodeGenerator;
import ch.swb.graphgenerator.graph.generator.nodes.EmploymentNodeGenerator;
import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.generator.relationships.AssignedProjectGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.ParticipatedCourseGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.PassedExamGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;

@DisplayName("Testing the graph generator")
class GraphGeneratorTest {

	private GraphGenerator generator;

	@Test
	@DisplayName("Using default graph Parameters should generate default number of Nodes with Edges")
	void when_generateGraphWithDefaultParameters_then_defaultNumberOfEmployeesWithEmploymentsWereGenerated() {
		// Arrange
		GraphParameters defaultParameters = new GraphParameters();
		SpecialNodeProvider specialNodeProvider = new SpecialNodeProvider(defaultParameters);
		generator = new GraphGenerator(defaultParameters, specialNodeProvider,
				new EmployeeNodeGenerator(),
				new EmploymentNodeGenerator(specialNodeProvider),
				new AssignedProjectGenerator(specialNodeProvider),
				new PassedExamGenerator(specialNodeProvider),
				new ParticipatedCourseGenerator(specialNodeProvider));

		// Act
		GraphData graph = generator.generateGraph();

		// Assert
		assertThat(graph.getCompanies()).hasSize(defaultParameters.getNumberOfCompanies());
		assertThat(graph.getCertificates()).hasSize(defaultParameters.getNumberOfCertificates());
		assertThat(graph.getKnowledges()).hasSize(defaultParameters.getNumberOfKnowledges());
		assertThat(graph.getSkills()).hasSize(defaultParameters.getNumberOfSkills());
		assertThat(graph.getCourses()).hasSize(defaultParameters.getNumberOfCourses());
		assertThat(graph.getProjects()).hasSize(defaultParameters.getNumberOfProjects());
		assertThat(graph.getEmployees()).hasSize(defaultParameters.getNumberOfEmployees());
		assertThat(graph.getEmployees()).allSatisfy(employee -> {
			assertThat(employee).hasNoNullFieldsOrProperties();
			assertThat(employee.getEmployments()).hasSizeGreaterThan(0);
			assertThat(employee.getEmployments()).allSatisfy(employment -> {
				assertThat(employment.getAssignedProjects()).hasSizeGreaterThanOrEqualTo(1);
			});
			assertThat(employee.getPassedExams()).hasSizeGreaterThan(0);
			assertThat(employee.getParticipatedCourses()).hasSizeGreaterThan(0);
		});
	}
}

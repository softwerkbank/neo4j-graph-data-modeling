package ch.swb.graphgenerator.graph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.model.GraphData;

@DisplayName("Testing the graph generator")
class GraphGeneratorTest {

	private GraphGenerator generator;

	@Test
	@DisplayName("Using default graph Parameters should generate default number of Nodes with Edges")
	void when_generateGraphWithDefaultParameters_then_defaultNumberOfEmployeesWithEmploymentsWereGenerated() {
		// Arrange
		GraphParameters defaultParameters = new GraphParameters();
		generator = new GraphGenerator(defaultParameters);

		// Act
		GraphData graph = generator.generateGraph();

		// Assert
		assertThat(graph.getCompanies()).hasSize(defaultParameters.getNumberOfCompanies());
		assertThat(graph.getCertificates()).hasSize(defaultParameters.getNumberOfCertificates());
		assertThat(graph.getProjects()).hasSize(defaultParameters.getNumberOfProjects());
		assertThat(graph.getEmployees()).hasSize(defaultParameters.getNumberOfEmployees());
		assertThat(graph.getEmployees()).allSatisfy(employee -> {
			assertThat(employee).hasNoNullFieldsOrProperties();
			assertThat(employee.getEmployments()).hasSizeGreaterThan(0);
			assertThat(employee.getEmployments()).allSatisfy(employment -> {
				assertThat(employment.getAssignedProjects()).hasSizeGreaterThanOrEqualTo(1);
			});
		});
	}
}

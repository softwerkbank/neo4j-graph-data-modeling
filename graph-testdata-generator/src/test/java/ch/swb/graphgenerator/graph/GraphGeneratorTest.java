package ch.swb.graphgenerator.graph;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.configuration.GraphParameters;
import ch.swb.graphgenerator.graph.generator.nodes.EmploymentNodeGenerator;
import ch.swb.graphgenerator.graph.generator.nodes.FixedNodeProvider;
import ch.swb.graphgenerator.graph.generator.nodes.NodeGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.AssignedProjectGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.ParticipatedCourseGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.PassedExamGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.RelationshipGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;

@DisplayName("Testing the graph generator")
class GraphGeneratorTest {

	private GraphGenerator generator;

	@Test
	@DisplayName("Using default graph Parameters should generate default number of Nodes with Edges")
	void when_generateGraphWithDefaultParameters_then_defaultNumberOfEmployeesWithEmploymentsWereGenerated() {
		// Arrange
		GraphParameters defaultParameters = new GraphParameters();
		FixedNodeProvider fixedNodeProvider = new FixedNodeProvider(defaultParameters);
		NodeGenerator nodeGenerator = new NodeGenerator(new EmploymentNodeGenerator(fixedNodeProvider), fixedNodeProvider, defaultParameters);
		RelationshipGenerator relationshipGenerator = new RelationshipGenerator(defaultParameters,
				new AssignedProjectGenerator(fixedNodeProvider),
				new PassedExamGenerator(fixedNodeProvider),
				new ParticipatedCourseGenerator(fixedNodeProvider));
		generator = new GraphGenerator(nodeGenerator, relationshipGenerator);

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

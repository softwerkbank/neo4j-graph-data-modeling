package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.configuration.GraphConfiguration;
import ch.swb.graphgenerator.graph.configuration.GraphParameters;
import ch.swb.graphgenerator.graph.model.nodes.Project;

class ProjectNodeGeneratorTest {

	private ProjectNodeGenerator testee;

	@BeforeEach
	void setup() {
		testee = new ProjectNodeGenerator(new FixedNodeProvider(new GraphParameters(new GraphConfiguration())));
	}

	@Test
	@DisplayName("When generateProjects() is called, then the configured number of projects with max number of used technologies and methodologies are generated")
	void when_generateProjects_then_numberOfProjectsWithUsedTechnologiesAndMethodologiesAreCreated() {
		List<Project> projects = testee.generateProjects(10, 5, 3);

		assertThat(projects).hasSize(10);
		assertThat(projects).allSatisfy(p -> {
			assertThat(p.getUsedTechnologies()).hasSizeLessThanOrEqualTo(5);
			assertThat(p.getUsedMethodologies()).hasSizeLessThanOrEqualTo(3);
		});
	}
}

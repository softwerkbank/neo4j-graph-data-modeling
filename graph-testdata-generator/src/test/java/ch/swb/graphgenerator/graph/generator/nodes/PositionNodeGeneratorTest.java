package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.PositionNodeGenerator;
import ch.swb.graphgenerator.graph.model.Position;

public class PositionNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allPostionsFromYAMLAreReturned() throws Exception {
		PositionNodeGenerator positionGenerator = new PositionNodeGenerator("src/test/resources/data/positions.yaml");
		List<Position> positions = positionGenerator.generateNodes();

		assertThat(positions).hasSize(7);
		assertThat(positions).allSatisfy(position -> {
			assertThat(position.getName()).isNotBlank();
		});
	}
}

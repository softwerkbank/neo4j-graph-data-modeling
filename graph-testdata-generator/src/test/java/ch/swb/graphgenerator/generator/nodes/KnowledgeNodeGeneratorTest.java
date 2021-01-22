package ch.swb.graphgenerator.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.KnowledgeNodeGenerator;
import ch.swb.graphgenerator.graph.model.Knowledge;

public class KnowledgeNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allKnowledgesFromYAMLAreReturned() throws Exception {
		KnowledgeNodeGenerator knowledgeGenerator = new KnowledgeNodeGenerator("src/test/resources/data/knowledges.yaml");
		List<Knowledge> knowledges = knowledgeGenerator.generateNodes();

		assertThat(knowledges).hasSize(5);
		assertThat(knowledges).allSatisfy(knowledge -> {
			assertThat(knowledge.getName()).isNotBlank();
		});
		Condition<Knowledge> containsTags = new Condition<>(k -> k.getTags() != null && k.getTags().size() >= 2, "knowledge contains tags");
		assertThat(knowledges).areExactly(3, containsTags);

		Condition<Knowledge> containsDescription = new Condition<>(k -> k.getDescription() != null, "knowledge contains description");
		assertThat(knowledges).areExactly(3, containsDescription);
	}
}

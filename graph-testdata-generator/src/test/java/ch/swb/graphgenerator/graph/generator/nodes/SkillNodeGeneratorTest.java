package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.SkillNodeGenerator;
import ch.swb.graphgenerator.graph.model.nodes.Skill;

public class SkillNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allKnowledgesFromYAMLAreReturned() throws Exception {
		SkillNodeGenerator knowledgeGenerator = new SkillNodeGenerator("src/test/resources/data/skills.yaml");
		List<Skill> skills = knowledgeGenerator.generateNodes();

		assertThat(skills).hasSize(5);
		assertThat(skills).allSatisfy(skill -> {
			assertThat(skill.getName()).isNotBlank();
		});
		Condition<Skill> containsTags = new Condition<>(s -> s.getTags() != null && s.getTags().size() >= 2, "skill contains tags");
		assertThat(skills).areExactly(3, containsTags);

		Condition<Skill> containsDescription = new Condition<>(s -> s.getDescription() != null, "skill contains description");
		assertThat(skills).areExactly(3, containsDescription);
	}
}

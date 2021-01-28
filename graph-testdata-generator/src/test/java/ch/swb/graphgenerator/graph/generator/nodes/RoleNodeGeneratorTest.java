package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.RoleNodeGenerator;
import ch.swb.graphgenerator.graph.model.nodes.Role;

public class RoleNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allRolesFromYAMLAreReturned() throws Exception {
		RoleNodeGenerator roleGenerator = new RoleNodeGenerator("src/test/resources/data/roles.yaml");
		List<Role> roles = roleGenerator.generateNodes();

		assertThat(roles).hasSize(5);
		assertThat(roles).allSatisfy(role -> {
			assertThat(role.getName()).isNotBlank();
		});
	}
}

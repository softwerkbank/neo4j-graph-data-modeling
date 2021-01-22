package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Role;

public class RoleNodeGenerator extends YamlNodeGenerator<Role> {

	private final String yaml;

	public RoleNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Role> generateNodes() throws Exception {
		return YAMLUtil.getListOfObjects(new File(yaml), Role.class);
	}

}

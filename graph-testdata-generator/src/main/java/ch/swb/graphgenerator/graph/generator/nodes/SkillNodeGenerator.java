package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Skill;

public class SkillNodeGenerator extends YamlNodeGenerator<Skill> {

	private final String yaml;

	public SkillNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Skill> generateNodes() throws Exception {
		return YAMLUtil.getListOfObjects(new File(yaml), Skill.class);
	}

}

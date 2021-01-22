package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Knowledge;

public class KnowledgeNodeGenerator extends YamlNodeGenerator<Knowledge> {

	private final String yaml;

	public KnowledgeNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Knowledge> generateNodes() throws Exception {
		return YAMLUtil.getListOfObjects(new File(yaml), Knowledge.class);
	}

}

package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Position;

public class PositionNodeGenerator extends YamlNodeGenerator<Position> {

	private final String yaml;

	public PositionNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Position> generateNodes() throws Exception {
		List<Position> positions = YAMLUtil.getListOfObjects(new File(yaml), Position.class);
		return positions;
	}

}

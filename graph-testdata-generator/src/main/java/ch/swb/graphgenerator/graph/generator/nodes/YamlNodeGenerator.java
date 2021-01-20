package ch.swb.graphgenerator.graph.generator.nodes;

import java.util.List;

public abstract class YamlNodeGenerator<T> {

	public abstract List<T> generateNodes() throws Exception;
}

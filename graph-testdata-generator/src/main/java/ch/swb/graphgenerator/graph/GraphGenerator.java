package ch.swb.graphgenerator.graph;

import ch.swb.graphgenerator.graph.generator.nodes.NodeGenerator;
import ch.swb.graphgenerator.graph.generator.relationships.RelationshipGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;
import jakarta.inject.Inject;

public class GraphGenerator {
	private final NodeGenerator nodeGenerator;
	private final RelationshipGenerator relationshipGenerator;

	@Inject
	public GraphGenerator(NodeGenerator nodeGenerator, RelationshipGenerator relationshipGenerator) {
		this.nodeGenerator = nodeGenerator;
		this.relationshipGenerator = relationshipGenerator;
	}

	public GraphData generateGraph() {
		GraphData graph = nodeGenerator.generateGraphWithNodes();
		relationshipGenerator.addRelationshipsToGraph(graph);
		return graph;
	}

}

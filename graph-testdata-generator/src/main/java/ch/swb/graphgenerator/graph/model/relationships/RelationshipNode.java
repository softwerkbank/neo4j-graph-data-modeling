package ch.swb.graphgenerator.graph.model.relationships;

import java.util.UUID;

public class RelationshipNode {
	private final String nodeLabel;
	private final UUID nodeId;

	public RelationshipNode(String nodeLabel, UUID nodeId) {
		this.nodeLabel = nodeLabel;
		this.nodeId = nodeId;
	}

	public String getNodeLabel() {
		return nodeLabel;
	}

	public UUID getNodeId() {
		return nodeId;
	}

}

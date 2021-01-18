package ch.swb.graphgenerator.graph.model.relationships;

import java.util.Objects;
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

	@Override
	public int hashCode() {
		return Objects.hash(nodeId, nodeLabel);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RelationshipNode other = (RelationshipNode) obj;
		return Objects.equals(nodeId, other.nodeId) && Objects.equals(nodeLabel, other.nodeLabel);
	}

}

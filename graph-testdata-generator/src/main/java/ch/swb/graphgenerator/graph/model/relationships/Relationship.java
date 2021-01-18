package ch.swb.graphgenerator.graph.model.relationships;

public abstract class Relationship {

	protected final RelationshipNode from;
	protected final RelationshipNode to;

	public Relationship(RelationshipNode from, RelationshipNode to) {
		this.from = from;
		this.to = to;
	}

	public RelationshipNode getFrom() {
		return from;
	}

	public RelationshipNode getTo() {
		return to;
	}

}

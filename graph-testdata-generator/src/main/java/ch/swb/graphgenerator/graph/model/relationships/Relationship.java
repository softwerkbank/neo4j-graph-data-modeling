package ch.swb.graphgenerator.graph.model.relationships;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(from, to);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Relationship other = (Relationship) obj;
		return Objects.equals(from, other.from) && Objects.equals(to, other.to);
	}
}

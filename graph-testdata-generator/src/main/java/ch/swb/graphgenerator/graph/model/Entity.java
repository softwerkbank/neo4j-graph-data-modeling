package ch.swb.graphgenerator.graph.model;

import java.util.Objects;
import java.util.UUID;

public abstract class Entity {
	protected final UUID id;

	public Entity(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Entity other = (Entity) obj;
		return Objects.equals(id, other.id);
	}

}

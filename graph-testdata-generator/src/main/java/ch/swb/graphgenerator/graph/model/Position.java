package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class Position extends Entity {
	private String name;

	public Position(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

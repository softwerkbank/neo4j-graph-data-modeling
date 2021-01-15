package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class Role extends Entity {
	private String name;

	public Role(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

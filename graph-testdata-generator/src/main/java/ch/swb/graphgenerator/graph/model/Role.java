package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Role extends Entity {
	private String name;

	public Role(@JsonProperty("name") String name) {
		this(UUID.randomUUID(), name);
	}

	public Role(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

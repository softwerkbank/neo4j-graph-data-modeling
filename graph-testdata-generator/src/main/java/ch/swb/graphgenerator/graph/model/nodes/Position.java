package ch.swb.graphgenerator.graph.model.nodes;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Position extends Entity {
	private String name;

	public Position(@JsonProperty("name") String name) {
		this(UUID.randomUUID(), name);
	}

	public Position(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

}

package ch.swb.graphgenerator.graph.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Knowledge extends Entity {

	private final String name;
	private String description;
	private List<String> tags;

//	public Knowledge(@JsonProperty("name") String name) {
//		this(name, null);
//	}
//
//	public Knowledge(@JsonProperty("name") String name, @JsonProperty("description") String description) {
//		this(UUID.randomUUID(), name, description);
//	}

	public Knowledge(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("tags") List<String> tags) {
		this(UUID.randomUUID(), name, description);
		this.tags = tags;
	}

	private Knowledge(UUID id, String name, String description) {
		super(id);
		this.name = name;
		this.description = description;
		this.tags = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void addTag(String tag) {
		this.tags.add(tag);
	}

	public void addTags(Collection<String> tags) {
		this.tags.addAll(tags);
	}

	public List<String> getTags() {
		return tags;
	}
}

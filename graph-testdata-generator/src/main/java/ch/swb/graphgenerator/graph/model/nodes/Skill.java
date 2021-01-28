package ch.swb.graphgenerator.graph.model.nodes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Skill extends Entity {
	public static final String LABEL = "Skill";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_TAGS = "tags";

	private final String name;
	private String description;
	private List<String> tags;

	public Skill(@JsonProperty("name") String name, @JsonProperty("description") String description, @JsonProperty("tags") List<String> tags) {
		this(UUID.randomUUID(), name, description);
		if (tags != null && !tags.isEmpty()) {
			this.tags = tags;
		}
	}

	private Skill(UUID id, String name, String description) {
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

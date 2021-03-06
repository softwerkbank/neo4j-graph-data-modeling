package ch.swb.graphgenerator.graph.model.nodes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Course extends Entity {
	public static final String LABEL = "Course";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_ORGANIZER = "organizer";
	public static final String KEY_PLATFORM = "platform";
	public static final String KEY_DURATION = "duration";

	private final String name;
	private final String organizer;
	private final String duration;

	private String description;
	private String platform;
	private List<String> knowledges;

	public Course(@JsonProperty("name") String name, @JsonProperty("organizer") String organizer, @JsonProperty("duration") String duration,
			@JsonProperty("description") String description, @JsonProperty("platform") String platform,
			@JsonProperty("knowledges") List<String> knowledges) {
		this(UUID.randomUUID(), name, organizer, duration);
		this.description = description;
		this.platform = platform;
		if (knowledges != null && !knowledges.isEmpty()) {
			this.knowledges = knowledges;
		}
	}

	public Course(UUID id, String name, String organizer, String duration) {
		super(id);
		this.name = name;
		this.organizer = organizer;
		this.duration = duration;
		this.knowledges = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public String getOrganizer() {
		return organizer;
	}

	public String getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public String getPlatform() {
		return platform;
	}

	public List<String> getKnowledges() {
		return knowledges;
	}

}

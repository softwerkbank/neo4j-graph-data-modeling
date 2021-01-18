package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class Project {
	public static final String LABEL = "Project";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_DESCRIPTION = "description";
	public static final String KEY_WORKING_LANGUAGE = "working_language";

	private final UUID id;
	private final String name;
	private final String description;
	private final String workingLanguage;

	public Project(UUID id, String name, String description, String workingLanguage) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.workingLanguage = workingLanguage;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getWorkingLanguage() {
		return workingLanguage;
	}

}

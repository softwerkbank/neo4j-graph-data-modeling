package ch.swb.graphgenerator.graph.model.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	private final List<Technology> usedTechnologies;
	private final List<Methodology> usedMethodologies;

	public Project(UUID id, String name, String description, String workingLanguage) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.workingLanguage = workingLanguage;
		this.usedTechnologies = new ArrayList<>();
		this.usedMethodologies = new ArrayList<>();
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

	public List<Technology> getUsedTechnologies() {
		return Collections.unmodifiableList(usedTechnologies);
	}

	public void addUsedTechnology(Technology technology) {
		this.usedTechnologies.add(technology);
	}

	public void addUsedTechnologies(List<Technology> technologies) {
		this.usedTechnologies.addAll(technologies);
	}

	public List<Methodology> getUsedMethodologies() {
		return Collections.unmodifiableList(usedMethodologies);
	}

	public void addUsedMethodology(Methodology methodology) {
		this.usedMethodologies.add(methodology);
	}

	public void addUsedMethodologies(List<Methodology> methodologies) {
		this.usedMethodologies.addAll(methodologies);
	}

}

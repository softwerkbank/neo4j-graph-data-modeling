package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class Company extends Entity {
	public static final String LABEL = "Company";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_INDUSTRY = "industry";

	private final String name;
	private final String industry;

	public Company(UUID id, String name, String industry) {
		super(id);
		this.name = name;
		this.industry = industry;
	}

	public String getName() {
		return name;
	}

	public String getIndustry() {
		return industry;
	}

	@Override
	public String toString() {
		return String.format("Company [id=%s, name=%s, industry=%s]", id, name, industry);
	}
}

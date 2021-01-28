package ch.swb.graphgenerator.graph.model.nodes;

import java.util.UUID;

public class Customer extends Entity {
	private final String name;
	private final String industry;

	public Customer(UUID id, String name, String industry) {
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
		return String.format("Customer [id=%s, name=%s, industry=%s]", id, name, industry);
	}

}

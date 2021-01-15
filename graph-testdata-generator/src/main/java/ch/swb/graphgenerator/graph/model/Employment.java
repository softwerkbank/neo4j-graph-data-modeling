package ch.swb.graphgenerator.graph.model;

import java.time.LocalDate;
import java.util.UUID;

public class Employment extends Entity {

	public static final String LABEL = "Employment";
	public static final String KEY_START = "start_date";
	public static final String KEY_END = "end_date";
	public static final String KEY_POSITION = "position";

	private final LocalDate start;
	private final LocalDate end;
	private final String position;
	private final Company company;
	// TODO: roles

	public Employment(UUID id, LocalDate start, LocalDate end, String position, Company company) {
		super(id);
		this.start = start;
		this.end = end;
		this.position = position;
		this.company = company;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public String getPosition() {
		return position;
	}

	public Company getCompany() {
		return company;
	}

}

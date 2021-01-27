package ch.swb.graphgenerator.graph.model.relationships;

import java.time.LocalDate;

public class ParticipatedCourse extends Relationship {

	public static final String LABEL = "PARTICIPATED_COURSE";
	public static final String KEY_START_DATE = "start_date";
	public static final String KEY_END_DATE = "end_date";

	private final LocalDate startDate;
	private final LocalDate endDate;

	public ParticipatedCourse(RelationshipNode from, RelationshipNode to, LocalDate startDate, LocalDate endDate) {
		super(from, to);
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

}

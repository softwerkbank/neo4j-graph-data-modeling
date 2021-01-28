package ch.swb.graphgenerator.graph.model.relationships;

import java.time.LocalDate;

public class PassedExam extends Relationship {

	public static final String LABEL = "PASSED_EXAM";
	public static final String KEY_EXAMINATION_DATE = "examination_date";
	public static final String KEY_EXAM = "exam";
	public static final String KEY_EXAMINATION_INSTITUTE = "examination_institute";

	private final LocalDate examinationDate;
	private final String exam;
	private final String examinationInstitute;

	public PassedExam(RelationshipNode from, RelationshipNode to, LocalDate examinationDate, String exam, String examinationInstitute) {
		super(from, to);
		this.examinationDate = examinationDate;
		this.exam = exam;
		this.examinationInstitute = examinationInstitute;
	}

	public LocalDate getExaminationDate() {
		return examinationDate;
	}

	public String getExam() {
		return exam;
	}

	public String getExaminationInstitute() {
		return examinationInstitute;
	}

}

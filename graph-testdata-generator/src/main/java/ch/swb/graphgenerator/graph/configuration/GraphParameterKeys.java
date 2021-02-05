package ch.swb.graphgenerator.graph.configuration;

public class GraphParameterKeys {

	public static final String NUMBER_EMPLOYEES = "number.employees";
	public static final String NUMBER_CUSTOMERS = "number.customers";
	public static final String NUMBER_COMPANIES = "number.companies";
	public static final String NUMBER_CERTIFICATES = "number.certificates";
	public static final String NUMBER_KNOWLEDGES = "number.knowledges";
	public static final String NUMBER_SKILLS = "number.skills";
	public static final String NUMBER_COURSES = "number.courses";
	public static final String NUMBER_PROJECTS = "number.projects";

	// Employment
	public static final String EMPLOYMENT_AVERAGE_PERIOD = "employment.average.period";
	public static final String EMPLOYMENT_JITTER_AVERAGE_PERIOD = "employment.jitter.average.period";
	public static final String EMPLOYMENT_FIRST_AFTER_YEARS = "employment.first.after.years";
	public static final String EMPLOYMENT_JITTER_FIRST_EMPLOYMENT = "employment.jitter.first.employment";
	public static final String EMPLOYMENT_JITTER_BETWEEN_EMPLOYMENTS = "employment.jitter.between.employments";

	// Projects
	public static final String PROJECT_MIN_PERIOD_ASSIGNMENT = "project.min.period.assignment";
	public static final String PROJECT_MAX_PERIOD_ASSIGNMENT = "project.max.period.assignment";
	public static final String PROJECT_MAX_ROLES = "project.max.roles";

	// Certificates
	public static final String CERTIFICATE_NUMBER_YEARS_PER_CERTIFICATE = "certificate.number.years.per.certificate";

	// Courses
	public static final String COURSE_TRAINING_DAYS_PER_YEAR = "course.training.days.per.year";

	private GraphParameterKeys() {

	}
}

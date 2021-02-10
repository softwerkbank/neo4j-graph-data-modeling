package ch.swb.graphgenerator.graph.configuration;

import java.time.Period;

public class DefaultGraphParameters {
	public static final int DEFAULT_NUMBER_EMPLOYEES = 2;
	public static final int DEFAULT_NUMBER_CUSTOMERS = 20;
	public static final int DEFAULT_NUMBER_COMPANIES = 30;
	public static final int DEFAULT_NUMBER_CERTIFICATES = 60;
	public static final int DEFAULT_NUMBER_TECHNOLOGIES = 55;
	public static final int DEFAULT_NUMBER_METHODOLOGIES = 10;
	public static final int DEFAULT_NUMBER_COURSES = 65;
	public static final int DEFAULT_NUMBER_PROJECTS = 30;
//	public static final int DEFAULT_NUMBER_OF_ROLES = 15;

	// Employment
	public static final Period EMPLOYMENT_DEFAULT_AVERAGE_PERIOD = Period.ofYears(3);
	public static final Period EMPLOYMENT_DEFAULT_JITTER_AVERAGE_PERIOD = Period.ofMonths(18);
	public static final Period EMPLOYMENT_DEFAULT_FIRST_AFTER_YEARS = Period.ofYears(20);
	public static final Period EMPLOYMENT_DEFAULT_JITTER_FIRST_EMPLOYMENT = Period.ofYears(6);
	public static final Period EMPLOYMENT_DEFAULT_JITTER_BETWEEN_EMPLOYMENTS = Period.ofMonths(24);

	// Projects
	public static final Period PROJECT_DEFAULT_MIN_PERIOD_ASSIGNMENT = Period.ofMonths(6);
	public static final Period PROJECT_DEFAULT_MAX_PERIOD_ASSIGNMENT = Period.ofMonths(36);
	public static final int PROJECT_DEFAULT_MAX_ROLES = 3;
	public static final int PROJECT_DEFAULT_MAX_USED_TECHNOLOGIES = 10;
	public static final int PROJECT_DEFAULT_MAX_USED_METHODOLOGIES = 5;

	// Certificates
	public static final int CERTIFICATE_DEFAULT_NUMBER_YEARS_PER_CERTIFICATE = 3;

	// Courses
	public static final int COURSE_DEFAULT_TRAINING_DAYS_PER_YEAR = 5;

	private DefaultGraphParameters() {
	}
}

package ch.swb.graphgenerator.graph;

import java.time.Period;

public class DefaultGraphParameters {
	public static final int DEFAULT_NUMBER_OF_EMPLOYEES = 2;
	public static final int DEFAULT_NUMBER_OF_CUSTOMERS = 20;
	public static final int DEFAULT_NUMBER_OF_COMPANIES = 30;
	public static final int DEFAULT_NUMBER_OF_CERTIFICATES = 60;
	public static final int DEFAULT_NUMBER_OF_KNOWLEDGES = 66;
	public static final int DEFAULT_NUMBER_OF_SKILLS = 60;
	public static final int DEFAULT_NUMBER_OF_COURSES = 70;
	public static final int DEFAULT_NUMBER_OF_PROJECTS = 30;
//	public static final int DEFAULT_NUMBER_OF_ROLES = 15;

	// Employment
	public static final Period DEFAULT_AVERAGE_EMPLOYMENT_PERIOD = Period.ofYears(3);
	public static final Period DEFAULT_JITTER_AVERAGE_EMPLOYMENT_PERIOD = Period.ofMonths(18);
	public static final Period DEFAULT_FIRST_EMPLOYMENT_AFTER = Period.ofYears(20);
	public static final Period DEFAULT_JITTER_FIRST_EMPLOYMENT = Period.ofYears(6);
	public static final Period DEFAULT_JITTER_BETWEEN_EMPLOYMENTS = Period.ofMonths(24);

	// Projects
	public static final Period DEFAULT_MIN_PERIOD_OF_PROJECT_ASSIGNMENT = Period.ofMonths(6);
	public static final Period DEFAULT_MAX_PERIOD_OF_PROJECT_ASSIGNMENT = Period.ofMonths(36);
	public static final int DEFAULT_MAX_ROLES_PROJECT = 3;
	public static final int DEFAULT_CERTIFICATE_EVERY_NUMBER_OF_YEARS = 3;

	private DefaultGraphParameters() {
	}
}

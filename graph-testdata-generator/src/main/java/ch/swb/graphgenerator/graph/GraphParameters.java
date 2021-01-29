package ch.swb.graphgenerator.graph;

import java.time.Period;

public class GraphParameters {
	private int numberOfEmployees;
	private int numberOfCompanies;
	private int numberOfCertificates;
	private int numberOfProjects;
	private int numberOfKnowledges;
	private int numberOfSkills;
	private int numberOfCourses;

	private Period averageEmploymentPeriod;
	private Period jitterAverageEmploymentPeriod;
	private Period firstEmploymentAfter;
	private Period jitterFirstEmployment;
	private Period jitterBetweenEmployments;

	private Period minPeriodProjectAssignment;
	private Period maxPeriodProjectAssignment;
	private int maxRolesProject;

	private int certificateEveryNumberOfYears;
	private int trainingDaysPerYear;

	public GraphParameters() {
		numberOfEmployees = DefaultGraphParameters.DEFAULT_NUMBER_OF_EMPLOYEES;
		numberOfCompanies = DefaultGraphParameters.DEFAULT_NUMBER_OF_COMPANIES;
		numberOfCertificates = DefaultGraphParameters.DEFAULT_NUMBER_OF_CERTIFICATES;
		numberOfProjects = DefaultGraphParameters.DEFAULT_NUMBER_OF_PROJECTS;
		numberOfKnowledges = DefaultGraphParameters.DEFAULT_NUMBER_OF_KNOWLEDGES;
		numberOfSkills = DefaultGraphParameters.DEFAULT_NUMBER_OF_SKILLS;
		numberOfCourses = DefaultGraphParameters.DEFAULT_NUMBER_OF_COURSES;

		// Employment
		averageEmploymentPeriod = DefaultGraphParameters.DEFAULT_AVERAGE_EMPLOYMENT_PERIOD;
		jitterAverageEmploymentPeriod = DefaultGraphParameters.DEFAULT_JITTER_AVERAGE_EMPLOYMENT_PERIOD;
		firstEmploymentAfter = DefaultGraphParameters.DEFAULT_FIRST_EMPLOYMENT_AFTER;
		jitterFirstEmployment = DefaultGraphParameters.DEFAULT_JITTER_FIRST_EMPLOYMENT;
		jitterBetweenEmployments = DefaultGraphParameters.DEFAULT_JITTER_BETWEEN_EMPLOYMENTS;

		// Project
		minPeriodProjectAssignment = DefaultGraphParameters.DEFAULT_MIN_PERIOD_OF_PROJECT_ASSIGNMENT;
		maxPeriodProjectAssignment = DefaultGraphParameters.DEFAULT_MAX_PERIOD_OF_PROJECT_ASSIGNMENT;
		maxRolesProject = DefaultGraphParameters.DEFAULT_MAX_ROLES_PROJECT;

		// Certificate
		certificateEveryNumberOfYears = DefaultGraphParameters.DEFAULT_CERTIFICATE_EVERY_NUMBER_OF_YEARS;

		trainingDaysPerYear = DefaultGraphParameters.DEFAULT_TRAINING_DAYS_PER_YEAR;
	}

	public GraphParameters numberOfEmployees(int number) {
		numberOfEmployees = number;
		return this;
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public GraphParameters numberOfCompanies(int number) {
		numberOfCompanies = number;
		return this;
	}

	public int getNumberOfCompanies() {
		return numberOfCompanies;
	}

	public GraphParameters numberOfCertificates(int number) {
		numberOfCertificates = number;
		return this;
	}

	public int getNumberOfCertificates() {
		return numberOfCertificates;
	}

	public GraphParameters numberOfKnowledges(int number) {
		numberOfKnowledges = number;
		return this;
	}

	public int getNumberOfKnowledges() {
		return numberOfKnowledges;
	}

	public GraphParameters numberOfSkills(int number) {
		numberOfSkills = number;
		return this;
	}

	public int getNumberOfSkills() {
		return numberOfSkills;
	}

	public GraphParameters numberOfCourses(int number) {
		numberOfCourses = number;
		return this;
	}

	public int getNumberOfCourses() {
		return numberOfCourses;
	}

	public GraphParameters numberOfProjects(int number) {
		numberOfProjects = number;
		return this;
	}

	public int getNumberOfProjects() {
		return numberOfProjects;
	}

	public GraphParameters averageEmploymentPeriod(Period period) {
		averageEmploymentPeriod = period;
		return this;
	}

	public Period getAverageEmploymentPeriod() {
		return averageEmploymentPeriod;
	}

	public GraphParameters jitterAverageEmploymentPeriod(Period period) {
		jitterAverageEmploymentPeriod = period;
		return this;
	}

	public Period getJitterAverageEmploymentPeriod() {
		return jitterAverageEmploymentPeriod;
	}

	public GraphParameters firstEmploymentAfter(Period period) {
		firstEmploymentAfter = period;
		return this;
	}

	public Period getFirstEmploymentAfter() {
		return firstEmploymentAfter;
	}

	public GraphParameters jitterFirstEmployment(Period period) {
		jitterFirstEmployment = period;
		return this;
	}

	public Period getJitterFirstEmployment() {
		return jitterFirstEmployment;
	}

	public GraphParameters jitterBetweenEmployments(Period period) {
		jitterBetweenEmployments = period;
		return this;
	}

	public Period getJitterBetweenEmployments() {
		return jitterBetweenEmployments;
	}

	public GraphParameters minPeriodProjectAssignment(Period period) {
		minPeriodProjectAssignment = period;
		return this;
	}

	public Period getMinPeriodProjectAssignment() {
		return minPeriodProjectAssignment;
	}

	public GraphParameters maxPeriodProjectAssignment(Period period) {
		maxPeriodProjectAssignment = period;
		return this;
	}

	public Period getMaxPeriodProjectAssignment() {
		return maxPeriodProjectAssignment;
	}

	public GraphParameters maxRolesProject(int number) {
		maxRolesProject = number;
		return this;
	}

	public int getMaxRolesProject() {
		return maxRolesProject;
	}

	public GraphParameters certificateEveryNumberOfYears(int number) {
		certificateEveryNumberOfYears = number;
		return this;
	}

	public int getCertifcateEveryNumberOfYears() {
		return certificateEveryNumberOfYears;
	}

	public GraphParameters trainingDaysPerYear(int number) {
		trainingDaysPerYear = number;
		return this;
	}

	public int getTrainingDaysPerYear() {
		return trainingDaysPerYear;
	}
}

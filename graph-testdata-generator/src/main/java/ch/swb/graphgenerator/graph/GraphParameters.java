package ch.swb.graphgenerator.graph;

import java.time.Period;

public class GraphParameters {
	private int numberOfEmployees;
	private int numberOfCompanies;
	private int numberOfCertificates;
	private int numberOfProjects;

	private Period averageEmploymentPeriod;
	private Period jitterAverageEmploymentPeriod;
	private Period firstEmploymentAfter;
	private Period jitterFirstEmployment;
	private Period jitterBetweenEmployments;

	private Period minPeriodProjectAssignment;
	private Period maxPeriodProjectAssignment;
	private int maxRolesProject;

	public GraphParameters() {
		numberOfEmployees = DefaultGraphParameters.DEFAULT_NUMBER_OF_EMPLOYEES;
		numberOfCompanies = DefaultGraphParameters.DEFAULT_NUMBER_OF_COMPANIES;
		numberOfCertificates = DefaultGraphParameters.DEFAULT_NUMBER_OF_CERTIFICATES;
		numberOfProjects = DefaultGraphParameters.DEFAULT_NUMBER_OF_PROJECTS;

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
}

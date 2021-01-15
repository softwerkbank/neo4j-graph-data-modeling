package ch.swb.graphgenerator.graph;

import java.time.Period;

public class GraphParameters {
	private int numberOfEmployees;
	private int numberOfCompanies;
	private int numberOfCertificates;
	private Period averageEmploymentPeriod;
	private Period jitterAverageEmploymentPeriod;
	private Period firstEmploymentAfter;
	private Period jitterFirstEmployment;
	private Period jitterBetweenEmployments;

	public GraphParameters() {
		numberOfEmployees = DefaultGraphParameters.DEFAULT_NUMBER_OF_EMPLOYEES;
		numberOfCompanies = DefaultGraphParameters.DEFAULT_NUMBER_OF_COMPANIES;
		numberOfCertificates = DefaultGraphParameters.DEFAULT_NUMBER_OF_CERTIFICATES;
		averageEmploymentPeriod = DefaultGraphParameters.DEFAULT_AVERAGE_EMPLOYMENT_PERIOD;
		jitterAverageEmploymentPeriod = DefaultGraphParameters.DEFAULT_JITTER_AVERAGE_EMPLOYMENT_PERIOD;
		firstEmploymentAfter = DefaultGraphParameters.DEFAULT_FIRST_EMPLOYMENT_AFTER;
		jitterFirstEmployment = DefaultGraphParameters.DEFAULT_JITTER_FIRST_EMPLOYMENT;
		jitterBetweenEmployments = DefaultGraphParameters.DEFAULT_JITTER_BETWEEN_EMPLOYMENTS;
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
}

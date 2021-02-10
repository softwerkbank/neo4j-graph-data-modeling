package ch.swb.graphgenerator.graph.configuration;

import java.time.Period;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class GraphParameters {
	private int numberOfEmployees;
	private int numberOfCompanies;
	private int numberOfCertificates;
	private int numberOfProjects;
	private int numberOfTechnologies;
	private int numberOfMethodologies;
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

	@Inject
	public GraphParameters(GraphConfiguration graphConfig) {
		numberOfEmployees = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_EMPLOYEES, DefaultGraphParameters.DEFAULT_NUMBER_EMPLOYEES);
		numberOfCompanies = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_COMPANIES, DefaultGraphParameters.DEFAULT_NUMBER_COMPANIES);
		numberOfCertificates = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_CERTIFICATES, DefaultGraphParameters.DEFAULT_NUMBER_CERTIFICATES);
		numberOfProjects = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_PROJECTS, DefaultGraphParameters.DEFAULT_NUMBER_PROJECTS);
		numberOfTechnologies = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_TECHNOLOGIES, DefaultGraphParameters.DEFAULT_NUMBER_TECHNOLOGIES);
		numberOfMethodologies = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_METHODOLOGIES, DefaultGraphParameters.DEFAULT_NUMBER_METHODOLOGIES);
		numberOfCourses = graphConfig.getConfigValue(GraphParameterKeys.NUMBER_COURSES, DefaultGraphParameters.DEFAULT_NUMBER_COURSES);

		// Employment
		averageEmploymentPeriod = graphConfig.getConfigValue(GraphParameterKeys.EMPLOYMENT_AVERAGE_PERIOD,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_AVERAGE_PERIOD);
		jitterAverageEmploymentPeriod = graphConfig.getConfigValue(GraphParameterKeys.EMPLOYMENT_JITTER_AVERAGE_PERIOD,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_JITTER_AVERAGE_PERIOD);
		firstEmploymentAfter = graphConfig.getConfigValue(GraphParameterKeys.EMPLOYMENT_FIRST_AFTER_YEARS,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_FIRST_AFTER_YEARS);
		jitterFirstEmployment = graphConfig.getConfigValue(GraphParameterKeys.EMPLOYMENT_JITTER_FIRST_EMPLOYMENT,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_JITTER_FIRST_EMPLOYMENT);
		jitterBetweenEmployments = graphConfig.getConfigValue(GraphParameterKeys.EMPLOYMENT_JITTER_BETWEEN_EMPLOYMENTS,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_JITTER_BETWEEN_EMPLOYMENTS);

		// Project
		minPeriodProjectAssignment = graphConfig.getConfigValue(GraphParameterKeys.PROJECT_MIN_PERIOD_ASSIGNMENT,
				DefaultGraphParameters.PROJECT_DEFAULT_MIN_PERIOD_ASSIGNMENT);
		maxPeriodProjectAssignment = graphConfig.getConfigValue(GraphParameterKeys.PROJECT_MAX_PERIOD_ASSIGNMENT,
				DefaultGraphParameters.PROJECT_DEFAULT_MAX_PERIOD_ASSIGNMENT);
		maxRolesProject = graphConfig.getConfigValue(GraphParameterKeys.PROJECT_MAX_ROLES, DefaultGraphParameters.PROJECT_DEFAULT_MAX_ROLES);

		// Certificate
		certificateEveryNumberOfYears = graphConfig.getConfigValue(GraphParameterKeys.CERTIFICATE_NUMBER_YEARS_PER_CERTIFICATE,
				DefaultGraphParameters.CERTIFICATE_DEFAULT_NUMBER_YEARS_PER_CERTIFICATE);

		// Course
		trainingDaysPerYear = graphConfig.getConfigValue(GraphParameterKeys.COURSE_TRAINING_DAYS_PER_YEAR,
				DefaultGraphParameters.COURSE_DEFAULT_TRAINING_DAYS_PER_YEAR);
	}

	public int getNumberOfEmployees() {
		return numberOfEmployees;
	}

	public int getNumberOfCompanies() {
		return numberOfCompanies;
	}

	public int getNumberOfCertificates() {
		return numberOfCertificates;
	}

	public int getNumberOfTechnologies() {
		return numberOfTechnologies;
	}

	public int getNumberOfMethodologies() {
		return numberOfMethodologies;
	}

	public int getNumberOfCourses() {
		return numberOfCourses;
	}

	public int getNumberOfProjects() {
		return numberOfProjects;
	}

	public Period getAverageEmploymentPeriod() {
		return averageEmploymentPeriod;
	}

	public Period getJitterAverageEmploymentPeriod() {
		return jitterAverageEmploymentPeriod;
	}

	public Period getFirstEmploymentAfter() {
		return firstEmploymentAfter;
	}

	public Period getJitterFirstEmployment() {
		return jitterFirstEmployment;
	}

	public Period getJitterBetweenEmployments() {
		return jitterBetweenEmployments;
	}

	public Period getMinPeriodProjectAssignment() {
		return minPeriodProjectAssignment;
	}

	public Period getMaxPeriodProjectAssignment() {
		return maxPeriodProjectAssignment;
	}

	public int getMaxRolesProject() {
		return maxRolesProject;
	}

	public int getCertifcateEveryNumberOfYears() {
		return certificateEveryNumberOfYears;
	}

	public int getTrainingDaysPerYear() {
		return trainingDaysPerYear;
	}
}

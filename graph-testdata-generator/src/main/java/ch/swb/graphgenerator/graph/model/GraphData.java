package ch.swb.graphgenerator.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Technology;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Methodology;

public class GraphData {

	private final List<Employee> employees = new ArrayList<>();
	private final List<Company> companies = new ArrayList<>();
	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Technology> technologies = new ArrayList<>();
	private final List<Methodology> methodologies = new ArrayList<>();
	private final List<Course> courses = new ArrayList<>();
	private final List<Project> projects = new ArrayList<>();

	public List<Employee> getEmployees() {
		return Collections.unmodifiableList(employees);
	}

	public void addEmployee(Employee employee) {
		this.employees.add(employee);
	}

	public void addEmployees(List<Employee> employees) {
		this.employees.addAll(employees);
	}

	public List<Company> getCompanies() {
		return Collections.unmodifiableList(companies);
	}

	public void addCompany(Company company) {
		this.companies.add(company);
	}

	public void addCompanies(List<Company> companies) {
		this.companies.addAll(companies);
	}

	public List<Certificate> getCertificates() {
		return Collections.unmodifiableList(certificates);
	}

	public void addCertificate(Certificate certificate) {
		this.certificates.add(certificate);
	}

	public void addCertificates(List<Certificate> certificates) {
		this.certificates.addAll(certificates);
	}

	public List<Technology> getTechnologies() {
		return Collections.unmodifiableList(technologies);
	}

	public void addTechnology(Technology technology) {
		this.technologies.add(technology);
	}

	public void addTechnologies(List<Technology> technologies) {
		this.technologies.addAll(technologies);
	}

	public List<Methodology> getMethodologies() {
		return Collections.unmodifiableList(methodologies);
	}

	public void addMethodology(Methodology methodology) {
		this.methodologies.add(methodology);
	}

	public void addMethodologies(List<Methodology> methodologies) {
		this.methodologies.addAll(methodologies);
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	public void addCourse(Course course) {
		this.courses.add(course);
	}

	public void addCourses(List<Course> courses) {
		this.courses.addAll(courses);
	}

	public List<Project> getProjects() {
		return Collections.unmodifiableList(projects);
	}

	public void addProject(Project project) {
		this.projects.add(project);
	}

	public void addProjects(List<Project> projects) {
		this.projects.addAll(projects);
	}

}

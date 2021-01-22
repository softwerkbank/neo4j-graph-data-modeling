package ch.swb.graphgenerator.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphData {

	private final List<Employee> employees = new ArrayList<>();
	private final List<Company> companies = new ArrayList<>();
	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Knowledge> knowledges = new ArrayList<>();
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

	public List<Knowledge> getKnowledges() {
		return Collections.unmodifiableList(knowledges);
	}

	public void addKnowledge(Knowledge knowledge) {
		this.knowledges.add(knowledge);
	}

	public void addKnowledges(List<Knowledge> knowledges) {
		this.knowledges.addAll(knowledges);
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

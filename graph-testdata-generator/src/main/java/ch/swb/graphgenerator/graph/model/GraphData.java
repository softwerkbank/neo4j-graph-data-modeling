package ch.swb.graphgenerator.graph.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GraphData {

	private final List<Employee> employees = new ArrayList<>();
	private final List<Company> companies = new ArrayList<>();
	private final List<Certificate> certificates = new ArrayList<>();

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

}

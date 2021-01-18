package ch.swb.graphgenerator.graph;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import com.github.javafaker.Faker;

import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Employee;
import ch.swb.graphgenerator.graph.model.Employment;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.Project;
import ch.swb.graphgenerator.graph.nodegenerator.EmployeeNodeGenerator;
import ch.swb.graphgenerator.graph.nodegenerator.EmploymentNodeGenerator;
import ch.swb.graphgenerator.graph.nodegenerator.SpecialNodeProvider;

public class GraphGenerator {
	private final GraphParameters parameters;
	private final GraphData graph;
	private final Faker faker;
	private final SpecialNodeProvider specialNodeProvider;

	public GraphGenerator(GraphParameters parameters) {
		this.parameters = parameters;
		this.graph = new GraphData();
		this.faker = new Faker(Locale.GERMANY);
		this.specialNodeProvider = SpecialNodeProvider.getInstance();
	}

	public GraphData generateGraph() {
		generateCompanies();
		generateCertificates();
		generateProjects();
		generateEmployeeNodes();
		generateEmployments();
		return graph;
	}

	private void generateEmployeeNodes() {
		EmployeeNodeGenerator employeeGenerator = new EmployeeNodeGenerator();
		List<Employee> employees = employeeGenerator.generateNodes(parameters.getNumberOfEmployees());
		graph.addEmployees(employees);
	}

	private void generateCompanies() {
		for (int i = 0; i < parameters.getNumberOfCompanies() - 1; i++) {
			Company company = new Company(UUID.randomUUID(), faker.company().name(), faker.company().industry());
			graph.addCompany(company);
		}
		graph.addCompany(specialNodeProvider.getCompanyForLastEmployment());
	}

//	private void generateCustomers() {
//		List<Customer> customers = new ArrayList<>();
//		for (int i = 0; i < Constants.NUMBER_OF_CUSTOMERS; i++) {
//			Customer customer = new Customer(UUID.randomUUID(), faker.company().name(), faker.company().industry());
//			customers.add(customer);
//		}
//	}

	private void generateProjects() {
		for (int i = 0; i < parameters.getNumberOfProjects(); i++) {
			Project project = new Project(UUID.randomUUID(), faker.superhero().name(), faker.lorem().characters(2000, 4000), i % 2 == 0 ? "german" : "english");
			graph.addProject(project);
		}
	}

	private void generateEmployments() {
		for (Employee employee : graph.getEmployees()) {
			EmploymentNodeGenerator employmentGenerator = new EmploymentNodeGenerator(employee.getDateOfBirth(),
					graph.getCompanies(),
					parameters.getAverageEmploymentPeriod(),
					parameters.getFirstEmploymentAfter(),
					parameters.getJitterFirstEmployment());
			List<Employment> employments = employmentGenerator.generateEmploymentsForEmployee();
			employee.addEmployments(employments);
		}
	}

	private void generateCertificates() {
		graph.addCertificates(specialNodeProvider.getCertificates().subList(0, parameters.getNumberOfCertificates()));
	}
}

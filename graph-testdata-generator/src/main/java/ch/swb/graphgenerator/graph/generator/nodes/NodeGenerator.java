package ch.swb.graphgenerator.graph.generator.nodes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.javafaker.Faker;

import ch.swb.graphgenerator.graph.configuration.GraphParameters;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import ch.swb.graphgenerator.graph.model.nodes.Methodology;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Technology;
import jakarta.inject.Inject;

public class NodeGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeGenerator.class);

	private final EmploymentNodeGenerator employmentGenerator;
	private final ProjectNodeGenerator projectGenerator;
	private final FixedNodeProvider fixedNodeProvider;
	private final GraphParameters parameters;

	private final Faker faker;
	private final EasyRandom easyRandom;

	@Inject
	public NodeGenerator(EmploymentNodeGenerator employmentGenerator, ProjectNodeGenerator projectGenerator, FixedNodeProvider fixedNodeProvider,
			GraphParameters parameters) {
		this.employmentGenerator = employmentGenerator;
		this.projectGenerator = projectGenerator;
		this.fixedNodeProvider = fixedNodeProvider;
		this.parameters = parameters;

		EasyRandomParameters randomParams = new EasyRandomParameters()
				.seed(System.currentTimeMillis())
				.objectPoolSize(100)
				.randomizationDepth(3)
				.dateRange(LocalDate.of(1960, 1, 1), LocalDate.of(1990, 12, 31))
				.stringLengthRange(8, 50)
				.collectionSizeRange(1, 10)
				.scanClasspathForConcreteTypes(true)
				.overrideDefaultInitialization(false)
				.ignoreRandomizationErrors(true);

		this.easyRandom = new EasyRandom(randomParams);
		this.faker = new Faker(Locale.GERMANY);
	}

	public GraphData generateGraphWithNodes() {
		GraphData graph = new GraphData();

		graph.addCompanies(generateCompanies());
		graph.addCertificates(generateCertificates());
		graph.addTechnologies(generateTechnologies());
		graph.addMethodologies(generateMethodologies());
		graph.addCourses(generateCourses());
		graph.addProjects(generateProjects());
		graph.addEmployees(generateEmployees());
		addEmploymentsToEmployees(graph);

		return graph;
	}

	private List<Company> generateCompanies() {
		List<Company> companies = new ArrayList<>();
		for (int i = 0; i < parameters.getNumberOfCompanies() - 1; i++) {
			Company company = new Company(UUID.randomUUID(), faker.company().name(), faker.company().industry());
			companies.add(company);
		}
		companies.add(fixedNodeProvider.getCompanyForLastEmployment());
		LOGGER.info("Generated {} companies", companies.size());
		return companies;
	}

	private List<Certificate> generateCertificates() {
		int toIndex = Math.min(parameters.getNumberOfCertificates(), fixedNodeProvider.getCertificates().size());
		List<Certificate> certificates = fixedNodeProvider.getCertificates().subList(0, toIndex);
		LOGGER.info("Generated {} certificates", certificates.size());
		return certificates;
	}

	private List<Technology> generateTechnologies() {
		int toIndex = Math.min(parameters.getNumberOfTechnologies(), fixedNodeProvider.getTechnologies().size());
		List<Technology> knowledges = fixedNodeProvider.getTechnologies().subList(0, toIndex);
		LOGGER.info("Generated {} technologies", knowledges.size());
		return knowledges;
	}

	private List<Methodology> generateMethodologies() {
		int toIndex = Math.min(parameters.getNumberOfMethodologies(), fixedNodeProvider.getMethodologies().size());
		List<Methodology> skills = fixedNodeProvider.getMethodologies().subList(0, toIndex);
		LOGGER.info("Generated {} methodologies", skills.size());
		return skills;
	}

	private List<Course> generateCourses() {
		int toIndex = Math.min(parameters.getNumberOfCourses(), fixedNodeProvider.getCourses().size());
		List<Course> courses = fixedNodeProvider.getCourses().subList(0, toIndex);
		LOGGER.info("Generated {} courses", courses.size());
		return courses;
	}

	private List<Project> generateProjects() {
		List<Project> projects = projectGenerator.generateProjects(parameters.getNumberOfProjects(), parameters.getMaxUsedTechnologiesProject(),
				parameters.getMaxUsedMethodologiesProject());
		LOGGER.info("Generated {} projects", projects.size());
		return projects;
	}

	private List<Employee> generateEmployees() {
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < parameters.getNumberOfEmployees(); i++) {
			Employee employee = new Employee(UUID.randomUUID(),
					faker.name().firstName(),
					faker.name().lastName(),
					easyRandom.nextObject(LocalDate.class));
			employees.add(employee);
		}
		LOGGER.info("Generated {} employees", employees.size());
		return employees;
	}

	private void addEmploymentsToEmployees(GraphData graph) {
		for (Employee employee : graph.getEmployees()) {
			List<Employment> employments = employmentGenerator.generateEmploymentsForEmployee(employee.getDateOfBirth(),
					graph.getCompanies(),
					parameters.getAverageEmploymentPeriod(),
					parameters.getFirstEmploymentAfter(),
					parameters.getJitterFirstEmployment());
			employee.addEmployments(employments);
			LOGGER.info("Generated {} employments for employee {}", employee.getEmployments().size(), employee.getLoginname());
		}
	}

}

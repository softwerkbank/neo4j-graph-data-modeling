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
import ch.swb.graphgenerator.graph.model.nodes.Knowledge;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Skill;
import jakarta.inject.Inject;

public class NodeGenerator {
	private static final Logger LOGGER = LoggerFactory.getLogger(NodeGenerator.class);

	private final EmploymentNodeGenerator employmentGenerator;
	private final FixedNodeProvider fixedNodeProvider;
	private final GraphParameters parameters;

	private final Faker faker;
	private final EasyRandom easyRandom;

	@Inject
	public NodeGenerator(EmploymentNodeGenerator employmentGenerator, FixedNodeProvider fixedNodeProvider, GraphParameters parameters) {
		this.employmentGenerator = employmentGenerator;
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
		graph.addKnowledges(generateKnowledges());
		graph.addSkills(generateSkills());
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

	private List<Knowledge> generateKnowledges() {
		int toIndex = Math.min(parameters.getNumberOfKnowledges(), fixedNodeProvider.getKnowledges().size());
		List<Knowledge> knowledges = fixedNodeProvider.getKnowledges().subList(0, toIndex);
		LOGGER.info("Generated {} knowledges", knowledges.size());
		return knowledges;
	}

	private List<Skill> generateSkills() {
		int toIndex = Math.min(parameters.getNumberOfSkills(), fixedNodeProvider.getSkills().size());
		List<Skill> skills = fixedNodeProvider.getSkills().subList(0, toIndex);
		LOGGER.info("Generated {} skills", skills.size());
		return skills;
	}

	private List<Course> generateCourses() {
		int toIndex = Math.min(parameters.getNumberOfKnowledges(), fixedNodeProvider.getCourses().size());
		List<Course> courses = fixedNodeProvider.getCourses().subList(0, toIndex);
		LOGGER.info("Generated {} courses", courses.size());
		return courses;
	}

	private List<Project> generateProjects() {
		List<Project> projects = new ArrayList<>();
		for (int i = 0; i < parameters.getNumberOfProjects(); i++) {
			Project project = new Project(UUID.randomUUID(), faker.superhero().name(), faker.lorem().characters(2000, 4000), i % 2 == 0 ? "german" : "english");
			projects.add(project);
		}
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

package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.configuration.GraphParameters;
import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Technology;
import ch.swb.graphgenerator.graph.model.nodes.Position;
import ch.swb.graphgenerator.graph.model.nodes.Role;
import ch.swb.graphgenerator.graph.model.nodes.Methodology;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class FixedNodeProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedNodeProvider.class);

	private final GraphParameters graphParameters;

	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Role> roles = new ArrayList<>();
	private final List<Position> positions = new ArrayList<>();
	private final List<Technology> knowledges = new ArrayList<>();
	private final List<Methodology> skills = new ArrayList<>();
	private final List<Course> courses = new ArrayList<>();
	private Company companyForLastEmployment;
	private EasyRandom random;

	@Inject
	public FixedNodeProvider(GraphParameters graphParameters) {
		this.graphParameters = graphParameters;

		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		random = new EasyRandom(parameters);
		initCertificates("src/main/resources/data/certificates.yaml");
		initRoles("src/main/resources/data/roles.yaml");
		initPositions("src/main/resources/data/positions.yaml");
		initTechnologies("src/main/resources/data/technologies.yaml");
		initMethodologies("src/main/resources/data/methodologies.yaml");
		initCourses("src/main/resources/data/courses.yaml");
		this.companyForLastEmployment = new Company(UUID.randomUUID(), "Skillsight Consulting AG", "IT Dienstleistungen");
	}

	private void initCertificates(String yaml) {
		try {
			this.certificates.addAll(YAMLUtil.getListOfObjects(new File(yaml), Certificate.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	public void initRoles(String yaml) {
		try {
			this.roles.addAll(YAMLUtil.getListOfObjects(new File(yaml), Role.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	public void initPositions(String yaml) {
		try {
			this.positions.addAll(YAMLUtil.getListOfObjects(new File(yaml), Position.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	private void initTechnologies(String yaml) {
		try {
			this.knowledges.addAll(YAMLUtil.getListOfObjects(new File(yaml), Technology.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}

	}

	private void initMethodologies(String yaml) {
		try {
			this.skills.addAll(YAMLUtil.getListOfObjects(new File(yaml), Methodology.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}

	}

	private void initCourses(String yaml) {
		try {
			this.courses.addAll(YAMLUtil.getListOfObjects(new File(yaml), Course.class));
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}

	}

	public Certificate getRandomCertificate() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfCertificates());
		return certificates.get(randomIndex);
	}

	public List<Certificate> getCertificates() {
		return Collections.unmodifiableList(certificates);
	}

	public Role getRandomRole() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(roles.size());
		return roles.get(randomIndex);
	}

	public List<Role> getRoles() {
		return Collections.unmodifiableList(roles);
	}

	public Position getRandomPosition() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(positions.size());
		return positions.get(randomIndex);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}

	public Technology getRandomKnowledge() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfTechnologies());
		return knowledges.get(randomIndex);
	}

	public List<Technology> getTechnologies() {
		return Collections.unmodifiableList(knowledges);
	}

	public Methodology getRandomSkill() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfMethodologies());
		return skills.get(randomIndex);
	}

	public List<Methodology> getMethodologies() {
		return Collections.unmodifiableList(skills);
	}

	public Course getRandomCourse() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfCourses());
		return courses.get(randomIndex);
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	public Company getCompanyForLastEmployment() {
		return companyForLastEmployment;
	}

}

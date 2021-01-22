package ch.swb.graphgenerator.graph.generator.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.model.Certificate;
import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Course;
import ch.swb.graphgenerator.graph.model.Knowledge;
import ch.swb.graphgenerator.graph.model.Position;
import ch.swb.graphgenerator.graph.model.Role;

public class SpecialNodeProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpecialNodeProvider.class);

	private static SpecialNodeProvider instance;
	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Role> roles = new ArrayList<>();
	private final List<Position> positions = new ArrayList<>();
	private final List<Knowledge> knowledges = new ArrayList<>();
	private final List<Course> courses = new ArrayList<>();
	private final Company companyForLastEmployment;

	private SpecialNodeProvider() {
		initCertificates("src/main/resources/data/certificates.yaml");
		initRoles("src/main/resources/data/roles.yaml");
		initPositions("src/main/resources/data/positions.yaml");
		initKnowledges("src/main/resources/data/knowledges.yaml");
		initCourses("src/main/resources/data/courses.yaml");
		this.companyForLastEmployment = new Company(UUID.randomUUID(), "Skillsight Consulting AG", "IT Dienstleistungen");
	}

	public static SpecialNodeProvider getInstance() {
		if (instance == null) {
			instance = new SpecialNodeProvider();
		}
		return instance;
	}

	private void initCertificates(String yaml) {
		try {
			CertificateNodeGenerator certificateGenerator = new CertificateNodeGenerator(yaml);
			this.certificates.addAll(certificateGenerator.generateNodes());
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	public void initRoles(String yaml) {
		try {
			RoleNodeGenerator roleGenerator = new RoleNodeGenerator(yaml);
			this.roles.addAll(roleGenerator.generateNodes());
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	public void initPositions(String yaml) {
		try {
			PositionNodeGenerator positionGenerator = new PositionNodeGenerator(yaml);
			this.positions.addAll(positionGenerator.generateNodes());
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}
	}

	private void initKnowledges(String yaml) {
		try {
			KnowledgeNodeGenerator knowlegdeGenerator = new KnowledgeNodeGenerator(yaml);
			this.knowledges.addAll(knowlegdeGenerator.generateNodes());
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}

	}

	private void initCourses(String yaml) {
		try {
			CourseNodeGenerator courseGenerator = new CourseNodeGenerator(yaml);
			this.courses.addAll(courseGenerator.generateNodes());
		} catch (Exception e) {
			LOGGER.error("Error while reading certificates from YAML file: {}", yaml, e);
		}

	}

	public Certificate getRandomCertificate() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(certificates.size());
		return certificates.get(randomIndex);
	}

	public List<Certificate> getCertificates() {
		return Collections.unmodifiableList(certificates);
	}

	public Role getRandomRole() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(roles.size());
		return roles.get(randomIndex);
	}

	public List<Role> getRoles() {
		return Collections.unmodifiableList(roles);
	}

	public Position getRandomPosition() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(positions.size());
		return positions.get(randomIndex);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}

	public Knowledge getRandomKnowledge() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(knowledges.size());
		return knowledges.get(randomIndex);
	}

	public List<Knowledge> getKnowledges() {
		return Collections.unmodifiableList(knowledges);
	}

	public Course getRandomCourse() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(courses.size());
		return courses.get(randomIndex);
	}

	public List<Course> getCourses() {
		return Collections.unmodifiableList(courses);
	}

	public Company getCompanyForLastEmployment() {
		return companyForLastEmployment;
	}

}

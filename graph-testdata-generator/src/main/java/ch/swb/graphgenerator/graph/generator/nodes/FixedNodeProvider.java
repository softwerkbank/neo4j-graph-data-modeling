package ch.swb.graphgenerator.graph.generator.nodes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.GraphParameters;
import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Knowledge;
import ch.swb.graphgenerator.graph.model.nodes.Position;
import ch.swb.graphgenerator.graph.model.nodes.Role;
import ch.swb.graphgenerator.graph.model.nodes.Skill;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class FixedNodeProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(FixedNodeProvider.class);

	private final GraphParameters graphParameters;

	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Role> roles = new ArrayList<>();
	private final List<Position> positions = new ArrayList<>();
	private final List<Knowledge> knowledges = new ArrayList<>();
	private final List<Skill> skills = new ArrayList<>();
	private final List<Course> courses = new ArrayList<>();
	private Company companyForLastEmployment;
	private EasyRandom random;

	@Inject
	public FixedNodeProvider(GraphParameters graphParameters) {
		this.graphParameters = graphParameters;
	}

	@PostConstruct
	private void init() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.nanoTime());
		random = new EasyRandom(parameters);
		initCertificates("src/main/resources/data/certificates.yaml");
		initRoles("src/main/resources/data/roles.yaml");
		initPositions("src/main/resources/data/positions.yaml");
		initKnowledges("src/main/resources/data/knowledges.yaml");
		initSkills("src/main/resources/data/skills.yaml");
		initCourses("src/main/resources/data/courses.yaml");
		this.companyForLastEmployment = new Company(UUID.randomUUID(), "Skillsight Consulting AG", "IT Dienstleistungen");
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

	private void initSkills(String yaml) {
		try {
			SkillNodeGenerator skillGenerator = new SkillNodeGenerator(yaml);
			this.skills.addAll(skillGenerator.generateNodes());
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

	public Knowledge getRandomKnowledge() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfKnowledges());
		return knowledges.get(randomIndex);
	}

	public List<Knowledge> getKnowledges() {
		return Collections.unmodifiableList(knowledges);
	}

	public Skill getRandomSkill() {
		random.setSeed(System.nanoTime());
		int randomIndex = random.nextInt(graphParameters.getNumberOfSkills());
		return skills.get(randomIndex);
	}

	public List<Skill> getSkills() {
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

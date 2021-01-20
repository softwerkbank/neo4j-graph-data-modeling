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
import ch.swb.graphgenerator.graph.model.Position;
import ch.swb.graphgenerator.graph.model.Role;

public class SpecialNodeProvider {
	private static final Logger LOGGER = LoggerFactory.getLogger(SpecialNodeProvider.class);

	private static SpecialNodeProvider instance;
	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Role> roles = new ArrayList<>();
	private final List<Position> positions = new ArrayList<>();
	private final Company companyForLastEmployment;

	private SpecialNodeProvider() {
		initCertificates("src/main/resources/data/certificates.yaml");
		initRoles();
		initPositions();
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

	public void initRoles() {
		roles.add(new Role(UUID.randomUUID(), "Frontend Developer"));
		roles.add(new Role(UUID.randomUUID(), "Backend Developer"));
		roles.add(new Role(UUID.randomUUID(), "Fullstack Developer"));
		roles.add(new Role(UUID.randomUUID(), "Software Architect"));
		roles.add(new Role(UUID.randomUUID(), "Business Analyst"));
		roles.add(new Role(UUID.randomUUID(), "Requirements Engineer"));
		roles.add(new Role(UUID.randomUUID(), "Product Owner"));
		roles.add(new Role(UUID.randomUUID(), "Scrum Master"));
		roles.add(new Role(UUID.randomUUID(), "Project Manager"));
		roles.add(new Role(UUID.randomUUID(), "Test Manager"));
		roles.add(new Role(UUID.randomUUID(), "Software Tester"));
		roles.add(new Role(UUID.randomUUID(), "Test Automation Engineer"));
	}

	public void initPositions() {
		positions.add(new Position(UUID.randomUUID(), "Business Analyst"));
		positions.add(new Position(UUID.randomUUID(), "Software Engineer"));
		positions.add(new Position(UUID.randomUUID(), "Software Architect"));
		positions.add(new Position(UUID.randomUUID(), "Department Manager"));
		positions.add(new Position(UUID.randomUUID(), "Project Manager"));
		positions.add(new Position(UUID.randomUUID(), "Test Manager"));
		positions.add(new Position(UUID.randomUUID(), "CEO"));
	}

	public Certificate getRandomCertificate() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(certificates.size());
		return certificates.get(randomIndex);
	}

	public List<Certificate> getCertificates() {
		return Collections.unmodifiableList(certificates);
	}

	public Role getRandomRole() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(roles.size());
		return roles.get(randomIndex);
	}

	public List<Role> getRoles() {
		return Collections.unmodifiableList(roles);
	}

	public Position getRandomPosition() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(positions.size());
		return positions.get(randomIndex);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}

	public Company getCompanyForLastEmployment() {
		return companyForLastEmployment;
	}

}

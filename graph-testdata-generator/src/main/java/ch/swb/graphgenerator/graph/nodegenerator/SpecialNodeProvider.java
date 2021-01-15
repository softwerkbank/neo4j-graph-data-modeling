package ch.swb.graphgenerator.graph.nodegenerator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.model.Certificate;
import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Position;
import ch.swb.graphgenerator.graph.model.Role;

public class SpecialNodeProvider {

	private static SpecialNodeProvider instance;
	private final List<Certificate> certificates = new ArrayList<>();
	private final List<Role> roles = new ArrayList<>();
	private final List<Position> positions = new ArrayList<>();
	private final Company companyForLastEmployment;

	private SpecialNodeProvider() {
		initCertificates();
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

	private void initCertificates() {
		// https://de.wikipedia.org/wiki/Liste_von_IT-Zertifikaten
		certificates.add(new Certificate(UUID.randomUUID(), "Neo4j Certified Professional", "Neo4j"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Agilist", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Program Consultant", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Practitioner", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Scrum Master", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Advanced Scrum Master", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Release Train Engineer", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Product Owner / Product Manager", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® DevOps Practitioner", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Government Practitioner", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Architect", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Agile Software Engineer", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Agile Product Manager", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(), "Certified SAFe® Lean Portfolio Manager", "SAFe"));
		certificates.add(new Certificate(UUID.randomUUID(),
				"iSAQB® Certified Professional for Software Architecture - Foundation Level (CPSA-F)", "iSAQB"));
		certificates.add(new Certificate(UUID.randomUUID(),
				"iSAQB® Certified Professional for Software Architecture - Advanced Level (CPSA-A)", "iSAQB"));
		certificates.add(new Certificate(UUID.randomUUID(), "Oracle Certified Associate, Java SE 8 Programmer", "Oracle"));
		certificates.add(new Certificate(UUID.randomUUID(), "Oracle Certified Professional, Java SE 8 Programmer", "Oracle"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Master I (PSM I)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Master II (PSM II)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Master III (PSM III)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Product Owner I (PSPO I)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Product Owner II (PSPO II)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Product Owner III (PSPO III)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "Professional Scrum Developer I (PSD I)", "Scrum.org"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Foundation", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Managing Professional", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Specialist Create, Deliver and Support", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Specialist Drive Stakeholder Value", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Specialist High-velocity IT", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Strategist Direct, Plan and Improve", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Strategic Leader", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Strategist Direct, Plan and Improve", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL 4 Leader Digital and IT Strategy", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "ITIL Master", "ITIL"));
		certificates.add(new Certificate(UUID.randomUUID(), "LPIC-1 – Junior Level Linux Professional", "Linux Professional Institute"));
		certificates.add(new Certificate(UUID.randomUUID(), "LPIC-2 – Advanced Level Linux Professional", "Linux Professional Institute"));
		certificates.add(new Certificate(UUID.randomUUID(), "LPIC-3 – Senior Level Linux Professional", "Linux Professional Institute"));
		certificates.add(new Certificate(UUID.randomUUID(), "LFCS – Linux Foundation Certified Sysadmin", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "LFCE – Linux Foundation Certified Engineer", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "COA – Certified OpenStack Administrator", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "CFCD – Cloud Foundry Certified Developer", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "CHFA – Certified Hyperledger Fabric Administrator", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "CHSA – Certified Hyperledger Sawtooth Administrator", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "CKA – Certified Kubernetes Administrator", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "CKAD – Certified Kubernetes Application Developer", "Linux Foundation"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Cloud Practitioner", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Solutions Architect – Associate", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Solutions Architect – Professional", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified SysOps Administrator – Associate", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Developer – Associate", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified DevOps Engineer – Professional", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Machine Learning – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Security – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Advanced Networking – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Data Analytics – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Alexa Skill Builder – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "AWS Certified Database – Specialty", "Amazon Web Services"));
		certificates.add(new Certificate(UUID.randomUUID(), "Hermes Foundation Level", "Hermes"));
		certificates.add(new Certificate(UUID.randomUUID(), "Hermes Advanced Level", "Hermes"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Fundamentals", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure AI Fundamentals", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Data Fundamentals", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Developer Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure AI Engineer Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Data Engineer Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Data Scientist Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Administrator Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Security Engineer Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Database Administrator Associate", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure Solutions Architect Expert", "Microsoft"));
		certificates.add(new Certificate(UUID.randomUUID(), "Azure DevOps Engineer Expert", "Microsoft"));
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

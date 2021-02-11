package ch.swb.graphgenerator.graph.generator.nodes;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import com.github.javafaker.Faker;

import ch.swb.graphgenerator.graph.model.nodes.Methodology;
import ch.swb.graphgenerator.graph.model.nodes.Project;
import ch.swb.graphgenerator.graph.model.nodes.Technology;
import jakarta.inject.Inject;

public class ProjectNodeGenerator {

	private final FixedNodeProvider fixedNodeProvider;
	private final Set<UUID> technologyIds;
	private final Set<UUID> methodologyIds;
	private Faker faker;

	@Inject
	public ProjectNodeGenerator(FixedNodeProvider fixedNodeProvider) {
		this.fixedNodeProvider = fixedNodeProvider;
		this.technologyIds = new HashSet<>();
		this.methodologyIds = new HashSet<>();
		this.faker = new Faker(Locale.GERMANY);
	}

	public List<Project> generateProjects(int numberOfProjects, int maxUsedTechnologies, int maxUsedMethodologies) {
		List<Project> projects = new ArrayList<>();
		for (int i = 0; i < numberOfProjects; i++) {
			Project project = new Project(UUID.randomUUID(), faker.superhero().name(), faker.lorem().characters(2000, 4000), i % 2 == 0 ? "german" : "english");
			project.addUsedTechnologies(createUsedTechnologies(maxUsedTechnologies));
			project.addUsedMethodologies(createUsedMethodologies(maxUsedMethodologies));
			projects.add(project);
		}
		return projects;
	}

	private List<Technology> createUsedTechnologies(int maxUsedTechnologies) {
		List<Technology> usedTechnologies = new ArrayList<>();
		technologyIds.clear();
		int numberOfTechnologies = ThreadLocalRandom.current().nextInt(1, maxUsedTechnologies + 1);
		for (int counter = 0; counter < numberOfTechnologies; counter++) {
			Technology technology = getRandomTechnologyWithoutDuplicates();
			usedTechnologies.add(technology);
		}
		return usedTechnologies;
	}

	private Technology getRandomTechnologyWithoutDuplicates() {
		Technology technology = null;
		do {
			technology = fixedNodeProvider.getRandomTechnology();
		} while (technologyIds.contains(technology.getId()));
		technologyIds.add(technology.getId());

		return technology;
	}

	private List<Methodology> createUsedMethodologies(int maxUsedMethodologies) {
		List<Methodology> usedMethodologies = new ArrayList<>();
		methodologyIds.clear();
		int numberOfMethodologies = ThreadLocalRandom.current().nextInt(1, maxUsedMethodologies + 1);
		for (int counter = 0; counter < numberOfMethodologies; counter++) {
			Methodology technology = getRandomMethodologyWithoutDuplicates();
			usedMethodologies.add(technology);
		}
		return usedMethodologies;
	}

	private Methodology getRandomMethodologyWithoutDuplicates() {
		Methodology methodology = null;
		do {
			methodology = fixedNodeProvider.getRandomMethodology();
		} while (methodologyIds.contains(methodology.getId()));
		methodologyIds.add(methodology.getId());

		return methodology;
	}
}

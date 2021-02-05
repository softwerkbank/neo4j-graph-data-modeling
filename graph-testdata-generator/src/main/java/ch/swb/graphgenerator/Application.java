package ch.swb.graphgenerator;

import ch.swb.graphgenerator.graph.GraphDataRepository;
import ch.swb.graphgenerator.graph.GraphGenerator;
import ch.swb.graphgenerator.graph.model.GraphData;
import jakarta.inject.Inject;

public class Application {

	private final GraphGenerator generator;
	private final GraphDataRepository graphRepository;

	@Inject
	public Application(GraphGenerator generator, GraphDataRepository graphRepository) {
		this.generator = generator;
		this.graphRepository = graphRepository;
	}

	public void run() {
		GraphData graph = generator.generateGraph();
		graphRepository.persist(graph);

	}
}

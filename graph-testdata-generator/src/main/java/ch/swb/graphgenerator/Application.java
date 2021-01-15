package ch.swb.graphgenerator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.GraphDataRepository;
import ch.swb.graphgenerator.graph.GraphGenerator;
import ch.swb.graphgenerator.graph.GraphParameters;
import ch.swb.graphgenerator.graph.model.GraphData;

public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String... args) {
		GraphParameters parameters = new GraphParameters();
		GraphGenerator generator = new GraphGenerator(parameters);
		GraphData graph = generator.generateGraph();
		GraphDataRepository graphRepository = new GraphDataRepository();
		graphRepository.persist(graph);
	}
}

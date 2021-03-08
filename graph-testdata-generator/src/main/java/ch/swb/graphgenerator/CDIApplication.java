package ch.swb.graphgenerator;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.swb.graphgenerator.graph.configuration.GraphConfiguration;
import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class CDIApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(CDIApplication.class);

	public static void main(String... args) {

		try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			// Load configuration from file
			GraphConfiguration graphConfiguration = container.select(GraphConfiguration.class).get();
			graphConfiguration.loadConfiguration("config.properties");
			graphConfiguration.loadConfiguration("db.properties");

			Application application = container.select(Application.class).get();
			application.run();
		} catch (ConfigurationException e) {
			LOGGER.error("Error while reading the configuration", e);
		}
	}
}

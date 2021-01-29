package ch.swb.graphgenerator;

import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class CDIApplication {
	public static void main(String... args) {
		try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
			Application application = container.select(Application.class).get();
			application.run();
		}
	}
}

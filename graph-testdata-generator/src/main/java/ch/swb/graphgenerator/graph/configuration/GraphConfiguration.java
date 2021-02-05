package ch.swb.graphgenerator.graph.configuration;

import java.io.File;
import java.time.Period;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Singleton;

@Singleton
public class GraphConfiguration {
	private static final Logger LOGGER = LoggerFactory.getLogger(GraphConfiguration.class);

	private PropertiesConfiguration config;

	public GraphConfiguration() {
		this.config = new PropertiesConfiguration();
	}

	public void loadGraphConfiguration(String path) throws ConfigurationException {
		Configurations configs = new Configurations();
		config = configs.properties(new File(path));
		LOGGER.info("Read {} properties from file {}", config.size(), path);
	}

	public String getConfigValue(String key, String defaultValue) {
		return config.getString(key, defaultValue);
	}

	public int getConfigValue(String key, int defaultValue) {
		return config.getInt(key, defaultValue);
	}

	public Period getConfigValue(String key, Period defaultValue) {
		String periodString = config.getString(key);
		if (periodString != null) {
			return Period.parse(periodString);
		}
		return defaultValue;
	}

}

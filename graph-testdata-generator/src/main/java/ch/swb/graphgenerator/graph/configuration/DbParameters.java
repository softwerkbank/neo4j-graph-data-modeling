package ch.swb.graphgenerator.graph.configuration;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class DbParameters {

	private final String host;
	private final int port;
	private final String name;
	private final boolean useAuthentication;
	private final String user;
	private final String password;

	@Inject
	public DbParameters(GraphConfiguration graphConfig) {
		this.host = graphConfig.getConfigValue(DbParameterKeys.DB_HOST, "localhost");
		this.port = graphConfig.getConfigValue(DbParameterKeys.DB_PORT, 7688);
		this.name = graphConfig.getConfigValue(DbParameterKeys.DB_NAME, "neo4j");
		this.useAuthentication = graphConfig.getConfigValue(DbParameterKeys.DB_USE_AUTHENTICATION, false);
		this.user = graphConfig.getConfigValue(DbParameterKeys.DB_USER, "neo4j");
		this.password = graphConfig.getConfigValue(DbParameterKeys.DB_PASSWORD, "neo4j");
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getName() {
		return name;
	}

	public boolean useAuthentication() {
		return useAuthentication;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

}

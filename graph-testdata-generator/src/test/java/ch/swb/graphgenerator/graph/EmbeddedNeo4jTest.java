package ch.swb.graphgenerator.graph;

import java.nio.file.Path;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.neo4j.configuration.GraphDatabaseSettings;
import org.neo4j.configuration.connectors.BoltConnector;
import org.neo4j.configuration.helpers.SocketAddress;
import org.neo4j.dbms.api.DatabaseManagementService;
import org.neo4j.dbms.api.DatabaseManagementServiceBuilder;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class EmbeddedNeo4jTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedNeo4jTest.class);

	private static DatabaseManagementService managementService;
	protected static GraphDatabaseService graphDB;

	@BeforeAll
	protected static void setUpBeforeClass() throws Exception {
		Path homeDirectory = Path.of("target/test");
		managementService = new DatabaseManagementServiceBuilder(homeDirectory)
				.setConfig(BoltConnector.enabled, true)
				.setConfig(BoltConnector.listen_address, new SocketAddress("localhost", 7688))
				.build();
		graphDB = managementService.database(GraphDatabaseSettings.DEFAULT_DATABASE_NAME);
		registerShutdownHook();
	}

	@AfterAll
	protected static void tearDownAfterClass() throws Exception {
		managementService.shutdown();
	}

	private static void registerShutdownHook() {
		// Registers a shutdown hook for the Neo4j instance so that it
		// shuts down nicely when the VM exits (even if you "Ctrl-C" the
		// running application).
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				managementService.shutdown();
			}
		});
	}

	protected static void clearDatabase() {
		LOGGER.info("Clearing the database...");
		try (Transaction tx = graphDB.beginTx()) {
			tx.execute("MATCH (n) DETACH DELETE n");
			tx.commit();
		}
	}

}
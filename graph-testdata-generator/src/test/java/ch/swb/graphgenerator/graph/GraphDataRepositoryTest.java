package ch.swb.graphgenerator.graph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Transaction;

import ch.swb.graphgenerator.graph.configuration.DbParameters;
import ch.swb.graphgenerator.graph.configuration.GraphConfiguration;
import ch.swb.graphgenerator.graph.model.GraphData;
import ch.swb.graphgenerator.graph.model.nodes.Company;

public class GraphDataRepositoryTest extends EmbeddedNeo4jTest {

	private GraphDataRepository testee;

	@BeforeEach
	void setUp() {
		clearDatabase();
		DbParameters dbParams = new DbParameters(new GraphConfiguration());
		testee = new GraphDataRepository(dbParams);
	}

	@Test
	@DisplayName("When persist is called, then the GraphData is persisted with all entities")
	public void when_persist_then_GraphDataIsPersisted() throws Exception {
		// arrange
		GraphData graphData = new GraphData();
		graphData.addCompany(new Company(UUID.randomUUID(), "Skillsight Consulting", "IT"));

		// act
		testee.persist(graphData);

		// assert
		try (Transaction tx = graphDB.beginTx()) {
			Node company = tx.findNode(Label.label(Company.LABEL), Company.KEY_NAME, "Skillsight Consulting");
			assertNotNull(company);
			assertEquals("Skillsight Consulting", company.getProperty(Company.KEY_NAME));
		}
	}

}

package ch.swb.graphgenerator.graph;

import java.time.format.DateTimeFormatter;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.Transaction;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;

import com.steelbridgelabs.oss.neo4j.structure.Neo4JGraph;
import com.steelbridgelabs.oss.neo4j.structure.providers.Neo4JNativeElementIdProvider;

import ch.swb.graphgenerator.graph.model.Certificate;
import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Employee;
import ch.swb.graphgenerator.graph.model.Employment;
import ch.swb.graphgenerator.graph.model.GraphData;

public class GraphDataRepository {

	public void persist(GraphData graph) {
		Neo4JNativeElementIdProvider idProvider = new Neo4JNativeElementIdProvider();
		try (Driver driver = GraphDatabase.driver("bolt://localhost", AuthTokens.basic("neo4j", "Neo4jAdmin4711"));
				Neo4JGraph neo4jGraph = new Neo4JGraph(driver, "skillsight", idProvider, idProvider)) {

			persistCompanies(neo4jGraph, graph);
			persistCertificates(neo4jGraph, graph);

			for (Employee employee : graph.getEmployees()) {
				try (Transaction transaction = neo4jGraph.tx()) {
					GraphTraversalSource g = neo4jGraph.traversal();

					Vertex employeeNode = g.addV(Employee.LABEL)
							.property(Employee.KEY_ID, employee.getId().toString())
							.property(Employee.KEY_FIRSTNAME, employee.getFirstname())
							.property(Employee.KEY_LASTNAME, employee.getLastname())
							.property(Employee.KEY_LOGINNAME, employee.getLoginname())
							.property(Employee.KEY_BIRTHDAY, employee.getDateOfBirth().format(DateTimeFormatter.ISO_DATE))
							.next();

					for (Employment employment : employee.getEmployments()) {
						Vertex employmentNode = g.addV(Employment.LABEL)
								.property(Employment.KEY_START, employment.getStart().format(DateTimeFormatter.ISO_DATE))
								.property(Employment.KEY_END, employment.getEnd() != null ? employment.getEnd().format(DateTimeFormatter.ISO_DATE) : null)
								.property(Employment.KEY_POSITION, employment.getPosition())
								.next();

						g.addE(Constants.EMPLOYEE_EMPLOYMENT_LABEL).from(employeeNode).to(employmentNode).next();

						Company company = employment.getCompany();
						Vertex companyNode = g.V().has(Company.LABEL, Company.KEY_ID, company.getId().toString()).next();
						g.addE(Constants.EMPLOYMENT_COMPANY_LABEL).from(employmentNode).to(companyNode).next();

					}
					transaction.commit();
				}
			}
		}
	}

	private void persistCompanies(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Company company : graph.getCompanies()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				g.addV(Company.LABEL)
						.property(Company.KEY_ID, company.getId().toString())
						.property(Company.KEY_NAME, company.getName())
						.property(Company.KEY_INDUSTRY, company.getIndustry())
						.next();

				transaction.commit();
			}
		}
	}

	private void persistCertificates(Neo4JGraph neo4jGraph, GraphData graph) {
		for (Certificate certificate : graph.getCertificates()) {
			try (Transaction transaction = neo4jGraph.tx()) {
				GraphTraversalSource g = neo4jGraph.traversal();

				g.addV(Certificate.LABEL)
						.property(Certificate.KEY_ID, certificate.getId().toString())
						.property(Certificate.KEY_NAME, certificate.getName())
						.property(Certificate.KEY_AUTHORITY, certificate.getAuthority())
						.next();

				transaction.commit();
			}
		}
	}
}

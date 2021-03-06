package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.configuration.DefaultGraphParameters;
import ch.swb.graphgenerator.graph.configuration.GraphConfiguration;
import ch.swb.graphgenerator.graph.configuration.GraphParameters;
import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Employment;

class EmploymentNodeGeneratorTest {

	private static List<Company> companies = new ArrayList<>();

	static {
		companies.add(new Company(UUID.randomUUID(), "Company 1", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 2", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 3", "Industry 3"));
		companies.add(new Company(UUID.randomUUID(), "Company 4", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 5", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 6", "Industry 3"));
		companies.add(new Company(UUID.randomUUID(), "Company 7", "Industry 4"));
		companies.add(new Company(UUID.randomUUID(), "Company 8", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 9", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 10", "Industry 4"));
		companies.add(new Company(UUID.randomUUID(), "Company 11", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 12", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 13", "Industry 3"));
		companies.add(new Company(UUID.randomUUID(), "Company 14", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 15", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 16", "Industry 3"));
		companies.add(new Company(UUID.randomUUID(), "Company 17", "Industry 4"));
		companies.add(new Company(UUID.randomUUID(), "Company 18", "Industry 1"));
		companies.add(new Company(UUID.randomUUID(), "Company 19", "Industry 2"));
		companies.add(new Company(UUID.randomUUID(), "Company 20", "Industry 4"));
	}

	private FixedNodeProvider fixedNodeProvider;
	private EmploymentNodeGenerator testee;
	private LocalDate today;

	@BeforeEach
	void setup() {
		fixedNodeProvider = new FixedNodeProvider(new GraphParameters(new GraphConfiguration()));
		testee = new EmploymentNodeGenerator(fixedNodeProvider);
		today = LocalDate.now();
	}

	@Test
	void when_generateEmploymentsForEmployee_then_allEmploymentsAreChronologicalAndLastEmploymentHasNoEnd() {
		List<Employment> employments = testee.generateEmploymentsForEmployee(LocalDate.of(1973, 5, 24),
				companies,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_AVERAGE_PERIOD,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_FIRST_AFTER_YEARS,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_JITTER_FIRST_EMPLOYMENT);
		assertThat(employments).hasSize(9);
		assertThat(employments).allSatisfy(employment -> {
			assertThat(employment.getStart()).isBefore(today);
		});

		for (int index = 0; index < employments.size(); index++) {
			if (index + 1 == employments.size()) {
				break;
			}
			Employment employment = employments.get(index);
			Employment nextEmployment = employments.get(index + 1);
			assertThat(employment.getEnd()).isBefore(nextEmployment.getStart());
			assertThat(employment.getCompany()).isNotEqualTo(nextEmployment.getCompany());
		}

		Employment lastEmployment = employments.get(employments.size() - 1);
		assertThat(lastEmployment.getCompany()).isEqualTo(fixedNodeProvider.getCompanyForLastEmployment());
		assertThat(lastEmployment.getEnd()).isNull();
	}

}

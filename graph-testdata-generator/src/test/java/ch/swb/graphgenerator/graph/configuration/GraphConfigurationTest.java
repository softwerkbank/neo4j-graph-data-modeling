package ch.swb.graphgenerator.graph.configuration;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Period;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing the GraphConfiguration")
class GraphConfigurationTest {

	private GraphConfiguration testee;

	@BeforeEach
	void setup() {
		testee = new GraphConfiguration();
	}

	@Test
	@DisplayName("When initGraphConfiguration is called, then only values of listed properties are used, otherwise default values are used")
	void when_initGraphConfiguration_then_onlyListedPropertiesAreUsedOtherwiseDefaultValues() throws ConfigurationException {
		// act
		testee.loadConfiguration("src/test/resources/config.properties");

		// assert
		// value from properties file
		int numberEmployees = testee.getConfigValue(GraphParameterKeys.NUMBER_EMPLOYEES, DefaultGraphParameters.DEFAULT_NUMBER_EMPLOYEES);
		assertThat(numberEmployees).isEqualTo(5);

		// value from properties file
		Period averageEmploymentPeriod = testee.getConfigValue(GraphParameterKeys.EMPLOYMENT_AVERAGE_PERIOD,
				DefaultGraphParameters.EMPLOYMENT_DEFAULT_AVERAGE_PERIOD);
		assertThat(averageEmploymentPeriod).isEqualTo(Period.ofYears(5));

		// default value
		int trainingDaysPerYear = testee.getConfigValue(GraphParameterKeys.COURSE_TRAINING_DAYS_PER_YEAR,
				DefaultGraphParameters.COURSE_DEFAULT_TRAINING_DAYS_PER_YEAR);
		assertThat(trainingDaysPerYear).isEqualTo(5);
	}

}

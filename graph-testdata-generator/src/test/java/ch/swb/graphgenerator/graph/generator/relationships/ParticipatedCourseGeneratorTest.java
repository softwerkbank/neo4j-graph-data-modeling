package ch.swb.graphgenerator.graph.generator.relationships;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.GraphParameters;
import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;

public class ParticipatedCourseGeneratorTest {

	private ParticipatedCourseGenerator testee;

	@BeforeEach
	void setupTestcase() {
		testee = new ParticipatedCourseGenerator(new SpecialNodeProvider(new GraphParameters()));
	}

	@Test
	@DisplayName("When generating \"PARTICIPATED_COURSE\" relationships for an employee with 5 years experience and 5 training days per year then minimum 5 relationships are created")
	public void when_generatingParticipatedCourse_then_relationshipsBasedOnTrainingDaysPerYearAreCreated() {
		// arrange
		Employee employee = new Employee(UUID.randomUUID(), "John", "Doe", LocalDate.of(1995, 5, 23));
		LocalDate startOfFirstEmployment = LocalDate.of(2015, 4, 1);

		// act
		List<ParticipatedCourse> participatedCourses = testee.generateParticipatedCourses(employee, startOfFirstEmployment, 5);

		// assert
		assertThat(participatedCourses).hasSizeGreaterThanOrEqualTo(5);
		assertThat(participatedCourses).allSatisfy(pc -> {
			assertThat(pc.getStartDate()).isNotNull();
			assertThat(pc.getEndDate()).isNotNull();
		});

		// Every year all training days are used
		Map<Integer, List<ParticipatedCourse>> participatedCoursesByYear = participatedCourses.stream()
				.collect(Collectors.groupingBy(pc -> pc.getStartDate().getYear()));
		assertThat(participatedCoursesByYear).allSatisfy((year, pcs) -> {
			Long sumOfDays = pcs.stream()
					.map(pc -> Duration.between(pc.getStartDate().atStartOfDay(), pc.getEndDate().atStartOfDay()).toDays())
					.reduce(0L, Long::sum);
			assertThat(sumOfDays).isEqualTo(5L);
		});

	}

}

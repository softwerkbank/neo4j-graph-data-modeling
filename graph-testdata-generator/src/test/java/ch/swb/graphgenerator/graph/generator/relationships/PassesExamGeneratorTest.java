package ch.swb.graphgenerator.graph.generator.relationships;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.relationships.PassesExamGenerator;
import ch.swb.graphgenerator.graph.model.Employee;
import ch.swb.graphgenerator.graph.model.relationships.PassesExam;

@DisplayName("Testing the generation of \"PASSES_EXAM\" relationships")
class PassesExamGeneratorTest {

	private PassesExamGenerator testee;

	@Test
	@DisplayName("When generating \"PASSES_EXAM\" relationships for an employee with 20 years experience and 1 certificate every 3 years then minimum 6 relationships are created")
	void when_generatingPassesExamForEmployeeWithTwentyYearsWorkExperienceAndCertificateEveryThreeYears_then_SixRelationshipsAreCreated() {
		// arrange
		Employee employee = new Employee(UUID.randomUUID(), "John", "Doe", LocalDate.of(1980, 5, 23));
		LocalDate startOfFirstEmployment = LocalDate.of(2001, 4, 1);
		testee = new PassesExamGenerator(employee, startOfFirstEmployment, 3);

		// act
		List<PassesExam> passedExams = testee.generatePassedExams();

		// assert
		assertThat(passedExams).hasSizeGreaterThanOrEqualTo(6);
		assertThat(passedExams).allSatisfy(pe -> {
			assertThat(pe.getExaminationDate()).isAfterOrEqualTo(startOfFirstEmployment);
			assertThat(pe.getExaminationDate()).isBefore(LocalDate.now());
			assertThat(pe.getExam()).isNotBlank();
			assertThat(pe.getExaminationInstitute()).isNotBlank();
		});

	}

}

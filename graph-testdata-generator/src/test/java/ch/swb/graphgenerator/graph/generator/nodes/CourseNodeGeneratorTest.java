package ch.swb.graphgenerator.graph.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.CourseNodeGenerator;
import ch.swb.graphgenerator.graph.model.Course;

public class CourseNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allCoursesFromYAMLAreReturned() throws Exception {
		CourseNodeGenerator courseGenerator = new CourseNodeGenerator("src/test/resources/data/courses.yaml");
		List<Course> courses = courseGenerator.generateNodes();

		assertThat(courses).hasSize(3);
		assertThat(courses).allSatisfy(course -> {
			assertThat(course.getName()).isNotBlank();
		});
		Condition<Course> containsKnowledges = new Condition<>(c -> c.getKnowledges() != null && c.getKnowledges().size() == 2, "courses transfers knowledge");
		assertThat(courses).areExactly(3, containsKnowledges);
	}
}

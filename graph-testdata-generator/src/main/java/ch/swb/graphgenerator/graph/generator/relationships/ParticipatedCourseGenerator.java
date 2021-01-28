package ch.swb.graphgenerator.graph.generator.relationships;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.model.nodes.Course;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.relationships.ParticipatedCourse;
import ch.swb.graphgenerator.graph.model.relationships.RelationshipNode;

public class ParticipatedCourseGenerator {

	private final Employee employee;
	private final LocalDate startOfFirstEmployment;
	private final int trainingDaysPerYear;
	private final Set<UUID> participatedCourses;

	private int trainingDaysInCurrentYear = 0;

	public ParticipatedCourseGenerator(Employee employee, LocalDate startOfFirstEmployment, int trainingDaysPerYear) {
		this.employee = employee;
		this.startOfFirstEmployment = startOfFirstEmployment;
		this.trainingDaysPerYear = trainingDaysPerYear;
		this.participatedCourses = new HashSet<>();
	}

	public List<ParticipatedCourse> generateParticipatedCourses() {
		List<ParticipatedCourse> participatedCourses = new ArrayList<>();

		RelationshipNode from = new RelationshipNode(Employee.LABEL, employee.getId());
		int year = startOfFirstEmployment.getYear();
		int currentYear = LocalDate.now().getYear();
		while (year <= currentYear) {
			Course course = getRandomCourse();
			RelationshipNode to = new RelationshipNode(Course.LABEL, course.getId());
			LocalDate startDate = getRandomLocalDate(year);
			participatedCourses.add(new ParticipatedCourse(from, to, startDate, startDate.plus(Period.parse(course.getDuration()))));
			if (trainingDaysInCurrentYear >= trainingDaysPerYear) {
				year += 1;
				trainingDaysInCurrentYear = 0;
			}
		}

		return participatedCourses;
	}

	private LocalDate getRandomLocalDate(int year) {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis())
				.objectPoolSize(100)
				.randomizationDepth(3)
				.dateRange(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));

		return new EasyRandom(parameters).nextObject(LocalDate.class);
	}

	private Course getRandomCourse() {
		Course course = null;
		Period courseDuration = null;
		do {
			course = SpecialNodeProvider.getInstance().getRandomCourse();
			courseDuration = Period.parse(course.getDuration());
		} while (participatedCourses.contains(course.getId()) || trainingDaysInCurrentYear + courseDuration.getDays() > trainingDaysPerYear);
		trainingDaysInCurrentYear += courseDuration.getDays();
		return course;
	}
}

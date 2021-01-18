package ch.swb.graphgenerator.graph.generator.nodes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.model.Company;
import ch.swb.graphgenerator.graph.model.Employment;

public class EmploymentNodeGenerator {

	private final LocalDate today;
	private final LocalDate employeeBirthday;
	private final int averageEmploymentPeriod;
	private final int firstEmploymentAfterYears;
	private final int boundForFirstEmploymentAfterYears;
	private final SpecialNodeProvider specialNodeProvider = SpecialNodeProvider.getInstance();
	private final List<Company> companies;

	public EmploymentNodeGenerator(LocalDate employeeBirthday, List<Company> companies, Period averageEmploymentPeriod, Period firstEmploymentAfter,
			Period jitterFirstEmployment) {
		this.today = LocalDate.now();
		this.employeeBirthday = employeeBirthday;
		this.companies = companies;
		this.averageEmploymentPeriod = averageEmploymentPeriod.getYears();
		this.firstEmploymentAfterYears = firstEmploymentAfter.getYears();
		this.boundForFirstEmploymentAfterYears = firstEmploymentAfter.plus(jitterFirstEmployment).getYears();
	}

	public List<Employment> generateEmploymentsForEmployee() {
		List<Employment> employments = new ArrayList<>();

		LocalDate lastEndDate = null;

		Period workingPeriod = Period.between(employeeBirthday.plusYears(firstEmploymentAfterYears), today);
		int number = workingPeriod.getYears() / averageEmploymentPeriod;

		for (int i = 0; i < number; i++) {
			if (i == number - 1) {
				employments.add(generateEmploymentWithoutEnd(lastEndDate));
			} else {
				Employment employment = generateEmploymentWithEnd(lastEndDate);
				lastEndDate = employment.getEnd();
				employments.add(employment);
			}
		}
		return employments;
	}

	public Employment generateEmploymentWithoutEnd(LocalDate lastEndDate) {
		LocalDate start = calculateStartDate(lastEndDate);
		return new Employment(UUID.randomUUID(), start, null, specialNodeProvider.getRandomPosition().getName(),
				specialNodeProvider.getCompanyForLastEmployment());
	}

	public Employment generateEmploymentWithEnd(LocalDate lastEndDate) {
		LocalDate start = calculateStartDate(lastEndDate);
		LocalDate end;
		if (averageEmploymentPeriod > 0) {
			end = calculateEndDate(start, start.getYear() + averageEmploymentPeriod);
		} else {
			end = calculateEndDate(start, today.getYear() + 1);
		}
		// lastEndDate = end;
		return new Employment(UUID.randomUUID(), start, end, specialNodeProvider.getRandomPosition().getName(), getRandomCompany());
	}

	private Company getRandomCompany() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis());
		EasyRandom random = new EasyRandom(parameters);
		int randomIndex = random.nextInt(companies.size());
		return companies.get(randomIndex);
	}

	private LocalDate calculateStartDate(LocalDate lastEndDate) {
		if (lastEndDate != null) {
			return calculateStartDateBasedOnLastEnddate(lastEndDate);
		} else {
			return calculateStartDateOfFirstEmployment();
		}
	}

	private LocalDate calculateStartDateBasedOnLastEnddate(LocalDate lastEndDate) {
		int startYear = lastEndDate.getYear();
		int startMonth = lastEndDate.getMonthValue();

		return LocalDate.of(startYear, startMonth, 1).plusMonths(1);
	}

	private LocalDate calculateStartDateOfFirstEmployment() {
		int originStartYear = employeeBirthday.getYear() + firstEmploymentAfterYears;
		int boundStartYear = employeeBirthday.getYear() + boundForFirstEmploymentAfterYears;
		int startYear = ThreadLocalRandom.current().nextInt(originStartYear, boundStartYear);
		int maxValueForStartMonth = startYear == today.getYear() ? today.getMonthValue() : 12;
		int startMonth = ThreadLocalRandom.current().nextInt(maxValueForStartMonth) + 1;

		return LocalDate.of(startYear, startMonth, 1);
	}

	private LocalDate calculateEndDate(LocalDate start, int boundEndYear) {
		int endYear = ThreadLocalRandom.current().nextInt(start.getYear() + 1, boundEndYear);
		int endMonth = ThreadLocalRandom.current().nextInt(12) + 1;
		LocalDate initial = LocalDate.of(endYear, endMonth, 1);
		return LocalDate.of(endYear, endMonth, initial.lengthOfMonth());
	}
}

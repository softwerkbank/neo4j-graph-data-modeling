package ch.swb.graphgenerator.graph.generator.nodes;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.model.nodes.Company;
import ch.swb.graphgenerator.graph.model.nodes.Employment;
import jakarta.inject.Inject;

public class EmploymentNodeGenerator {

	private final SpecialNodeProvider specialNodeProvider;
	private final LocalDate today;
	private UUID lastCompany = null;

	private LocalDate employeeBirthday;
	private List<Company> companies;
	private int averageEmploymentPeriod;
	private int firstEmploymentAfterYears;
	private int boundForFirstEmploymentAfterYears;

	@Inject
	public EmploymentNodeGenerator(SpecialNodeProvider specialNodeProvider) {
		this.specialNodeProvider = specialNodeProvider;
		today = LocalDate.now();
	}

	public List<Employment> generateEmploymentsForEmployee(LocalDate employeeBirthday, List<Company> companies, Period averageEmploymentPeriod,
			Period firstEmploymentAfter,
			Period jitterFirstEmployment) {
		this.employeeBirthday = employeeBirthday;
		this.companies = companies;
		this.averageEmploymentPeriod = averageEmploymentPeriod.getYears();
		this.firstEmploymentAfterYears = firstEmploymentAfter.getYears();
		this.boundForFirstEmploymentAfterYears = firstEmploymentAfter.plus(jitterFirstEmployment).getYears();

		List<Employment> employments = new ArrayList<>();

		LocalDate lastEndDate = null;

		Period workingPeriod = Period.between(employeeBirthday.plusYears(firstEmploymentAfter.getYears()), today);
		int number = workingPeriod.getYears() / averageEmploymentPeriod.getYears();

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

	private Employment generateEmploymentWithoutEnd(LocalDate lastEndDate) {
		LocalDate start = calculateStartDate(lastEndDate);
		return new Employment(UUID.randomUUID(), start, null, specialNodeProvider.getRandomPosition().getName(),
				specialNodeProvider.getCompanyForLastEmployment());
	}

	private Employment generateEmploymentWithEnd(LocalDate lastEndDate) {
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
				.seed(System.nanoTime());
		EasyRandom random = new EasyRandom(parameters);
		Company company = null;
		do {
			int randomIndex = random.nextInt(companies.size());
			company = companies.get(randomIndex);
		} while (company.getId().equals(lastCompany));
		lastCompany = company.getId();
		return company;
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

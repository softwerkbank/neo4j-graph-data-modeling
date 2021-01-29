package ch.swb.graphgenerator.graph.generator.relationships;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.model.nodes.Certificate;
import ch.swb.graphgenerator.graph.model.nodes.Employee;
import ch.swb.graphgenerator.graph.model.relationships.PassedExam;
import ch.swb.graphgenerator.graph.model.relationships.RelationshipNode;
import jakarta.inject.Inject;

public class PassedExamGenerator {

	private final SpecialNodeProvider specialNodeProvider;

	@Inject
	public PassedExamGenerator(SpecialNodeProvider specialNodeProvider) {
		this.specialNodeProvider = specialNodeProvider;
	}

	public List<PassedExam> generatePassedExams(Employee employee, LocalDate startOfFirstEmployment, int oneCertificateEveryYears) {
		List<PassedExam> passedExams = new ArrayList<>();

		RelationshipNode from = new RelationshipNode(Employee.LABEL, employee.getId());
		long numberOfCertificates = startOfFirstEmployment.until(LocalDate.now()).toTotalMonths() / 12 / oneCertificateEveryYears;
		for (int counter = 0; counter < numberOfCertificates; counter++) {
			Certificate certificate = specialNodeProvider.getRandomCertificate();
			RelationshipNode to = new RelationshipNode(Certificate.LABEL, certificate.getId());

			passedExams.add(
					new PassedExam(from, to, getRandomLocalDate(startOfFirstEmployment), certificate.getName().concat(" Exam"), certificate.getAuthority()));
		}

		return passedExams;
	}

	private LocalDate getRandomLocalDate(LocalDate startOfFirstEmployment) {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis())
				.objectPoolSize(100)
				.randomizationDepth(3)
				.dateRange(startOfFirstEmployment, LocalDate.now());

		return new EasyRandom(parameters).nextObject(LocalDate.class);
	}
}

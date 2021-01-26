package ch.swb.graphgenerator.graph.generator.relationships;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import ch.swb.graphgenerator.graph.generator.nodes.SpecialNodeProvider;
import ch.swb.graphgenerator.graph.model.Certificate;
import ch.swb.graphgenerator.graph.model.Employee;
import ch.swb.graphgenerator.graph.model.relationships.PassesExam;
import ch.swb.graphgenerator.graph.model.relationships.RelationshipNode;

public class PassesExamGenerator {

	private final Employee employee;
	private final LocalDate startOfFirstEmployment;
	private final int oneCertificateEveryYears;

	public PassesExamGenerator(Employee employee, LocalDate startOfFirstEmployment, int oneCertificateEveryYears) {
		this.employee = employee;
		this.startOfFirstEmployment = startOfFirstEmployment;
		this.oneCertificateEveryYears = oneCertificateEveryYears;
	}

	public List<PassesExam> generatePassedExams() {
		List<PassesExam> passedExams = new ArrayList<>();

		RelationshipNode from = new RelationshipNode(Employee.LABEL, employee.getId());
		long numberOfCertificates = startOfFirstEmployment.until(LocalDate.now()).toTotalMonths() / 12 / oneCertificateEveryYears;
		for (int counter = 0; counter < numberOfCertificates; counter++) {
			Certificate certificate = SpecialNodeProvider.getInstance().getRandomCertificate();
			RelationshipNode to = new RelationshipNode(Certificate.LABEL, certificate.getId());

			passedExams.add(new PassesExam(from, to, getRandomLocalDate(), certificate.getName().concat(" Exam"), certificate.getAuthority()));
		}

		return passedExams;
	}

	private LocalDate getRandomLocalDate() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis())
				.objectPoolSize(100)
				.randomizationDepth(3)
				.dateRange(startOfFirstEmployment, LocalDate.now());

		return new EasyRandom(parameters).nextObject(LocalDate.class);
	}
}

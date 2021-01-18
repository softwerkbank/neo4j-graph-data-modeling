package ch.swb.graphgenerator.graph.generator.nodes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ch.swb.graphgenerator.graph.model.Employee;

public class EmployeeNodeGenerator extends AbstractNodeGenerator<Employee> {

	public EmployeeNodeGenerator() {
		super();
	}

	@Override
	public Employee generateNode() {
		Employee employee = new Employee(UUID.randomUUID(),
				faker.name().firstName(),
				faker.name().lastName(),
				easyRandom.nextObject(LocalDate.class));
		return employee;
	}

	@Override
	public List<Employee> generateNodes(int number) {
		List<Employee> employees = new ArrayList<>();
		for (int i = 0; i < number; i++) {
			employees.add(generateNode());
		}

		return employees;
	}
}

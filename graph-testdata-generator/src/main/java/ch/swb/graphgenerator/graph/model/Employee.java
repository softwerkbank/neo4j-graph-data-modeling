package ch.swb.graphgenerator.graph.model;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Employee extends Entity {
	public static final String LABEL = "Employee";
	public static final String KEY_ID = "id";
	public static final String KEY_FIRSTNAME = "firstname";
	public static final String KEY_LASTNAME = "lastname";
	public static final String KEY_LOGINNAME = "loginname";
	public static final String KEY_BIRTHDAY = "birthday";

	private final String firstname;
	private final String lastname;
	private final LocalDate dateOfBirth;
	private final String loginname;
	private final Set<Employment> employments;

	public Employee(UUID id, String firstname, String lastname, LocalDate dateOfBirth) {
		super(id);
		this.firstname = firstname;
		this.lastname = lastname;
		this.dateOfBirth = dateOfBirth;
		this.loginname = computeLoginname();
		this.employments = new HashSet<>();
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getLoginname() {
		return loginname;
	}

	public Set<Employment> getEmployments() {
		return Collections.unmodifiableSet(employments);
	}

	public void addEmployment(Employment employment) {
		employments.add(employment);
	}

	public void addEmployments(Collection<Employment> employments) {
		this.employments.addAll(employments);
	}

	@Override
	public String toString() {
		return String.format("Employee [id=%s, firstname=%s, lastname=%s, dateOfBirth=%s, loginname=%s]", id, firstname, lastname,
				dateOfBirth, loginname);
	}

	private String computeLoginname() {
		return lastname.length() > 3 ? lastname.substring(0, 4).concat(firstname.substring(0, 2)).toLowerCase()
				: lastname.concat(firstname.substring(0, 2)).toLowerCase();
	}

}

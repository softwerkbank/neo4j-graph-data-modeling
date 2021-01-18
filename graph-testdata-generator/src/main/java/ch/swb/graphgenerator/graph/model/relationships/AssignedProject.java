package ch.swb.graphgenerator.graph.model.relationships;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ch.swb.graphgenerator.graph.model.Role;

public class AssignedProject extends Relationship {

	public static final String LABEL = "ASSIGNED_PROJECT";
	public static final String KEY_START = "start_date";
	public static final String KEY_END = "end_date";
	public static final String KEY_WORKLOAD = "workload";
	public static final String KEY_ROLES = "roles";

	private final LocalDate start;
	private final LocalDate end;
	private final int workload;
	private final List<Role> roles;

	public AssignedProject(RelationshipNode from, RelationshipNode to, LocalDate start, LocalDate end, int workload, Role... roles) {
		super(from, to);
		this.start = start;
		this.end = end;
		this.workload = workload;
		this.roles = Arrays.asList(roles);
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	public List<Role> getRoles() {
		return Collections.unmodifiableList(roles);
	}

	public int getWorkload() {
		return workload;
	}

}

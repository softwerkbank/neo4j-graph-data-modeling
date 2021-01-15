package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class CourseConfirmation extends Entity {
	private final String name;
	private final String version;
	private final String authority;

	public CourseConfirmation(UUID id, String name, String version, String authority) {
		super(id);
		this.name = name;
		this.version = version;
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public String getAuthority() {
		return authority;
	}

	public String getVersion() {
		return version;
	}

	@Override
	public String toString() {
		return String.format("CourseConfirmation [id=%s, coursename=%s, version=%s, authority=%s]", id, name, version, authority);
	}

}

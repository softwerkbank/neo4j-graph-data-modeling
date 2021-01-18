package ch.swb.graphgenerator.graph.model;

import java.util.UUID;

public class Certificate extends Entity {
	public static final String LABEL = "Certificate";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_AUTHORITY = "authority";

	private final String name;
	private final String authority;

	public Certificate(UUID id, String name, String authority) {
		super(id);
		this.name = name;
		this.authority = authority;
	}

	public String getName() {
		return name;
	}

	public String getAuthority() {
		return authority;
	}

	@Override
	public String toString() {
		return String.format("Certificate [id=%s, name=%s, authority=%s]", id, name, authority);
	}

}

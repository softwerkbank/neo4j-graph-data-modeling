package ch.swb.graphgenerator.graph.model.nodes;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Certificate extends Entity {
	public static final String LABEL = "Certificate";
	public static final String KEY_ID = "id";
	public static final String KEY_NAME = "name";
	public static final String KEY_AUTHORITY = "authority";
	public static final String KEY_VALIDITY = "validity";

	private final String name;
	private final String authority;
	private final String validity;

	public Certificate(@JsonProperty("name") String name, @JsonProperty("authority") String authority, @JsonProperty("validity") String validity) {
		this(UUID.randomUUID(), name, authority, validity);
	}

	public Certificate(UUID id, String name, String authority, String validity) {
		super(id);
		this.name = name;
		this.authority = authority;
		this.validity = validity;
	}

	public String getName() {
		return name;
	}

	public String getAuthority() {
		return authority;
	}

	public String getValidity() {
		return validity;
	}

	@Override
	public String toString() {
		return String.format("Certificate [name=%s, authority=%s, validity=%s, id=%s]", name, authority, validity, id);
	}

}

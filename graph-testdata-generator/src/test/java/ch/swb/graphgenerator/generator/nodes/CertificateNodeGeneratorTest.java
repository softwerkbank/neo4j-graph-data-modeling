package ch.swb.graphgenerator.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.swb.graphgenerator.graph.generator.nodes.CertificateNodeGenerator;
import ch.swb.graphgenerator.graph.model.Certificate;

public class CertificateNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allCertificatesFromYAMLAreReturned() throws JsonParseException, JsonMappingException, IOException {
		CertificateNodeGenerator certificateGenerator = new CertificateNodeGenerator();
		List<Certificate> certificates = certificateGenerator.generateCertificates();

		assertThat(certificates).hasSize(71);
	}
}

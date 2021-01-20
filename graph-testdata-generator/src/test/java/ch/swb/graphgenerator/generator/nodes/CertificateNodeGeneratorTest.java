package ch.swb.graphgenerator.generator.nodes;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import ch.swb.graphgenerator.graph.generator.nodes.CertificateNodeGenerator;
import ch.swb.graphgenerator.graph.model.Certificate;

public class CertificateNodeGeneratorTest {

	@Test
	void when_generateNodes_then_allCertificatesFromYAMLAreReturned() throws Exception {
		CertificateNodeGenerator certificateGenerator = new CertificateNodeGenerator("src/test/resources/data/certificates.yaml");
		List<Certificate> certificates = certificateGenerator.generateNodes();

		assertThat(certificates).hasSize(5);
		assertThat(certificates).allSatisfy(certificate -> {
			assertThat(certificate.getName()).isNotBlank();
			assertThat(certificate.getAuthority()).isNotBlank();
		});
	}
}

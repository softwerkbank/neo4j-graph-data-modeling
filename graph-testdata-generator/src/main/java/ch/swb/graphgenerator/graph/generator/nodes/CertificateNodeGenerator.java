package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Certificate;

public class CertificateNodeGenerator {

	public List<Certificate> generateCertificates() throws JsonParseException, JsonMappingException, IOException {
		List<Certificate> certificates = YAMLUtil.getListOfObjects(new File("src/main/resources/data/certificates.yaml"), Certificate.class);
		return certificates;
	}

}

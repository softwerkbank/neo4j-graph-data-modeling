package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Certificate;

public class CertificateNodeGenerator extends YamlNodeGenerator<Certificate> {

	private final String yaml;

	public CertificateNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Certificate> generateNodes() throws Exception {
		return YAMLUtil.getListOfObjects(new File(yaml), Certificate.class);
	}

}

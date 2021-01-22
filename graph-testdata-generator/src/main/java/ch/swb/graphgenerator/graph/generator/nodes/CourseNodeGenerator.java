package ch.swb.graphgenerator.graph.generator.nodes;

import java.io.File;
import java.util.List;

import ch.swb.graphgenerator.common.YAMLUtil;
import ch.swb.graphgenerator.graph.model.Course;

public class CourseNodeGenerator extends YamlNodeGenerator<Course> {

	private final String yaml;

	public CourseNodeGenerator(String yaml) {
		this.yaml = yaml;
	}

	@Override
	public List<Course> generateNodes() throws Exception {
		return YAMLUtil.getListOfObjects(new File(yaml), Course.class);
	}

}

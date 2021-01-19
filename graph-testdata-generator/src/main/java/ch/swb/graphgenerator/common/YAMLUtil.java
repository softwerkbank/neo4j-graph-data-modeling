package ch.swb.graphgenerator.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YAMLUtil {

	private static ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

	public static <T> T getObject(File yaml, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(yaml, clazz);

	}

	public static <T> List<T> getListOfObjects(File yaml, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		CollectionType listType = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
		List<T> listOfObjects = mapper.readValue(yaml, listType);
		return listOfObjects;
	}

	public static <K, V> Map<K, V> getMapOfObjects(File yaml, Class<K> keyClazz, Class<V> valueClazz)
			throws JsonParseException, JsonMappingException, IOException {
		TypeFactory typeFactory = mapper.getTypeFactory();
		MapType mapType = typeFactory.constructMapType(HashMap.class, keyClazz, valueClazz);
		return mapper.readValue(yaml, mapType);
	}
}

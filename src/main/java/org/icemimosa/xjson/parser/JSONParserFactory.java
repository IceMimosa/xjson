package org.icemimosa.xjson.parser;

import java.lang.reflect.Type;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.deserializer.JSONDeserializer;
import org.icemimosa.xjson.deserializer.JsonObjectDeserializer;
import org.icemimosa.xjson.utils.TypeUtils;

/**
 * 根据对象类型获取相应的parser解析类
 * 
 * @author ChenKai[514793425@qq.com]
 * 
 */
public class JSONParserFactory {

	private static JSONParserFactory factory = new JSONParserFactory();

	private JSONParserFactory() {
	}

	public static JSONParserFactory getInstance() {
		return factory;
	}

	public JSONParser getParser(Object obj, JsonConfig jsonConfig) {
		JSONParser parser = null;
		if (obj == null || TypeUtils.isPrimitive(obj)) {
			parser = new PrimitiveParser(obj, jsonConfig);
		} else if (TypeUtils.isCollectionOrArray(obj)) {
			parser = new CollectionParser(obj, jsonConfig);
		} else if (TypeUtils.isMap(obj)) {
			parser = new MapParser(obj, jsonConfig);
		} else {
			parser = new JavaBeanParser(obj, jsonConfig);
		}
		return parser;
	}

	public JSONDeserializer getDeserializer(String json, Type obj) {
		JSONDeserializer deserializer = null;
		deserializer = new JsonObjectDeserializer(json, obj);
		return deserializer;
	}
}

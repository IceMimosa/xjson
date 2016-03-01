package org.icemimosa.xjson;

import org.icemimosa.xjson.deserializer.JSONDeserializer;
import org.icemimosa.xjson.parser.JSONParser;
import org.icemimosa.xjson.parser.JSONParserFactory;

public class JSON {

	public static String toJsonString(Object obj) {
		return toJsonString(obj, new JsonConfig());
	}

	public static String toJsonString(Object obj, JsonConfig jsonConfig) {
		JSONParser parser = JSONParserFactory.getInstance().getParser(obj, jsonConfig);
		return parser.toJsonString();
	}

	public static JsonObject parseObject(String json) {
		JSONDeserializer deserializer = JSONParserFactory.getInstance().getDeserializer(json, JsonObject.class);
		if (deserializer == null) {
			return new JsonObject();
		}
		return (JsonObject) deserializer.deserialzer();
	}

	public static JsonArray parseArray(String json) {
		JSONDeserializer deserializer = JSONParserFactory.getInstance().getDeserializer(json, JsonObject.class);
		if (deserializer == null) {
			return new JsonArray();
		}
		return (JsonArray) deserializer.deserialzer();
	}
}

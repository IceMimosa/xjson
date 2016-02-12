package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

import org.icemimosa.xjson.JsonObject;

public class JsonObjectDeserializer extends AbstractDeserialzer {
	
	public JsonObjectDeserializer(String json, Type type) {
		super(json, type);
	}

	@Override
	public Object deserialzer() {
		
		JsonObject jsonObject = new JsonObject();
		
		
		return null;
	}
}

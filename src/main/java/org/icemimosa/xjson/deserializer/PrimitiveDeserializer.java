package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

public class PrimitiveDeserializer extends AbstractDeserialzer {

	public PrimitiveDeserializer(String json, Type type) {
		super(json, type);
	}

	@Override
	public Object deserialzer() {
		if (json == null) {
			return null;
		}
		Object retValue = null;
		if (json.startsWith("\"") || json.startsWith("\'")) {
			retValue = json.substring(1, json.length() - 1);
		}else{
			retValue = json;
		}
		return retValue;
	}
}

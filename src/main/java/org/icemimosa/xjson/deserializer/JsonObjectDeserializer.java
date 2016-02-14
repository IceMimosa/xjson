package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

import org.icemimosa.xjson.JsonObject;

public class JsonObjectDeserializer extends AbstractDeserialzer {

	public JsonObjectDeserializer(String json, Type type, JSONAnalyzer analyzer) {
		super(json, type, analyzer);
	}

	@Override
	public Object deserialzer() {

		JsonObject jsonObject = new JsonObject();
		boolean isKey = true;
		String key = null;
		for (;;) {
			char ch = (char) analyzer.nextToken();
			if (ch == '{') {
				continue;
			}
			if (analyzer.getCurrValueState() == JSONAnalyzer.VALUE) {
				if (isKey) {
					key = analyzer.getStringValue();
				} else {
					jsonObject.put(key, analyzer.getStringValue());
					key = null;
					isKey = true;
				}
				continue;
			}
			if (ch == ':') {
				isKey = false;
				if (key == null) {
					key = analyzer.getStringValue();
				}
				continue;
			}
			if (analyzer.getCurrValueState() == JSONAnalyzer.END) {
				break;
			}
		}
		return jsonObject;
	}
}

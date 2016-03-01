package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

import org.icemimosa.xjson.JsonArray;

public class JsonArrayDeserializer extends AbstractDeserialzer {

	public JsonArrayDeserializer(String json, Type type, JSONAnalyzer analyzer) {
		super(json, type, analyzer);
	}

	@Override
	public Object deserialzer() {

		JsonArray jsonArray = new JsonArray();
		// 如果是空对象
		if (analyzer.getCurrValueState() == JSONAnalyzer.END) {
			return jsonArray;
		}

		Object value = null;
		if (analyzer.getCurrValueState() == JSONAnalyzer.VALUE) {
			value = analyzer.getArrayValue();
			jsonArray.add(new PrimitiveDeserializer(value.toString(), type, analyzer).deserialzer());
		}
		if (analyzer.getCurrValueState() == JSONAnalyzer.OBJECT) {
			jsonArray.add(new JsonObjectDeserializer(json, type, analyzer).deserialzer());
		}
		if (analyzer.getCurrValueState() == JSONAnalyzer.ARRAY) {
			jsonArray.add(new JsonArrayDeserializer(json, type, analyzer).deserialzer());
		}
		
		
		for (;;) {
			char ch = (char) analyzer.nextToken();
			// 扫描key
			if (ch == ',') {
				if (analyzer.getCurrValueState() == JSONAnalyzer.VALUE) {
					value = analyzer.getArrayValue();
					jsonArray.add(new PrimitiveDeserializer(value.toString(), type, analyzer).deserialzer());
				}
				if (analyzer.getCurrValueState() == JSONAnalyzer.OBJECT) {
					jsonArray.add(new JsonObjectDeserializer(json, type, analyzer).deserialzer());
				}
				if (analyzer.getCurrValueState() == JSONAnalyzer.ARRAY) {
					jsonArray.add(new JsonArrayDeserializer(json, type, analyzer).deserialzer());
				}
				continue;
			}
			// json串结束
			if (analyzer.getCurrValueState() == JSONAnalyzer.END) {
				break;
			}
		}
		return jsonArray;
	}

}

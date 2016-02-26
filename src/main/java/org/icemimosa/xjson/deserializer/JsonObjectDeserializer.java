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
		// 如果是空对象
		if(analyzer.getCurrValueState() == JSONAnalyzer.END){
			return jsonObject;
		}
		
		String key = analyzer.getKey().toString();
		for (;;) {
			char ch = (char) analyzer.nextToken();
			// 扫描key
			if(ch == ','){
				key = analyzer.getKey().toString();
			}
			
			// 扫描value
			if (ch == ':') {
				if (analyzer.getCurrValueState() == JSONAnalyzer.VALUE) {
					jsonObject.put(key, analyzer.getValue());
				}
				if (analyzer.getCurrValueState() == JSONAnalyzer.OBJECT) {
					jsonObject.put(key, new JsonObjectDeserializer(json, type, analyzer).deserialzer());
				}
				continue;
			}
			
			// json串结束
			if (analyzer.getCurrValueState() == JSONAnalyzer.END) {
				break;
			}
		}
		return jsonObject;
	}
}

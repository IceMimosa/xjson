package org.icemimosa.xjson.parser;

import java.util.Map;
import java.util.Set;

import org.icemimosa.xjson.JsonConfig;

@SuppressWarnings("all")
public class MapParser extends AbstractJSONParser{

	public MapParser(Object obj, JsonConfig jsonConfig) {
		super(obj, jsonConfig);
	}

	@Override
	public String toJsonString() {
		if(obj == null){
			return null;
		}
		StringBuilder sb = new StringBuilder("{");
		Map map = (Map)obj;
		// 获取所有的entry
		Set<Map.Entry> entrySet = map.entrySet();
		for (Map.Entry entry : entrySet) {
			Object key = entry.getKey();
			Object value = entry.getValue();
			JSONParser keyParser = JSONParserFactory.getInstance().getParser(key, this.jsonConfig);
			JSONParser valueParser = JSONParserFactory.getInstance().getParser(value, this.jsonConfig);
			prettyFormat(sb, keyParser, valueParser);
		}
		
		return deleteLastComma(sb.toString()) + "}";
	}

}

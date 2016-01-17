package org.icemimosa.xjson.parser;

import java.util.Map;
import java.util.Set;

public class MapParser extends AbstractJSONParser{

	public MapParser(Object obj) {
		super(obj);
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
			JSONParser keyParser = JSONParserFactory.getInstance().getParser(key);
			JSONParser valueParser = JSONParserFactory.getInstance().getParser(value);
			sb.append(keyParser.toJsonString()).append(":").append(valueParser.toJsonString()).append(",");
		}
		String sbString = sb.toString();
		if(sbString.endsWith(",")){
			sbString = sbString.substring(0, sb.length() - 1);
		}
		return sbString + "}";
	}
	
}

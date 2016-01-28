package org.icemimosa.xjson.parser;

import java.util.Map;
import java.util.Set;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.utils.ConstantManager;

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
			sb.append(ConstantManager.getPrettySymbol()).append(keyParser.toJsonString()).append(":").append(valueParser.toJsonString()).append(",");
		}
		String sbString = sb.toString();
		if(sbString.endsWith(",")){
			sbString = sbString.substring(0, sb.length() - 1);
			if(jsonConfig.isPrettyFormat()){
				sbString += ConstantManager.getEnterSymbol();
			}
		}
		return sbString + "}";
	}
	
}

package org.icemimosa.xjson.parser;

import java.lang.reflect.Array;
import java.util.Collection;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.utils.ConstantManager;

public class CollectionParser extends AbstractJSONParser {

	public CollectionParser(Object obj, JsonConfig jsonConfig) {
		super(obj, jsonConfig);
	}

	@Override
	public String toJsonString() {
		if(obj == null){
			return null;
		}
		StringBuilder sb = new StringBuilder("[");
		// 1. 数组类型解析
		if(obj.getClass().isArray()){
			for (int i = 0; i < Array.getLength(obj); i++) {
				Object objValue = Array.get(obj, i);
				JSONParser parser = JSONParserFactory.getInstance().getParser(objValue, this.jsonConfig);
				sb.append(ConstantManager.getPrettySymbol()).append(parser.toJsonString()).append(",");
			}
		}
		// 2. 集合类型解析
		else if(obj instanceof Collection){
			for (Object objValue : (Collection<?>)obj) {
				JSONParser parser = JSONParserFactory.getInstance().getParser(objValue, this.jsonConfig);
				sb.append(ConstantManager.getPrettySymbol()).append(parser.toJsonString()).append(",");
			}
		}
		String sbString = sb.toString();
		if(sbString.endsWith(",")){
			sbString = sbString.substring(0, sb.length() - 1);
			if(jsonConfig.isPrettyFormat()){
				sbString += ConstantManager.getEnterSymbol();
			}
		}
		return sbString + "]";
	}

}

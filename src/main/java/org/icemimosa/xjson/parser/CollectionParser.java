package org.icemimosa.xjson.parser;

import java.lang.reflect.Array;
import java.util.Collection;

public class CollectionParser extends AbstractJSONParser {

	public CollectionParser(Object obj) {
		super(obj);
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
				JSONParser parser = JSONParserFactory.getInstance().getParser(objValue);
				sb.append(parser.toJsonString()).append(",");
			}
		}
		// 2. 集合类型解析
		else if(obj instanceof Collection){
			for (Object objValue : (Collection<?>)obj) {
				JSONParser parser = JSONParserFactory.getInstance().getParser(objValue);
				sb.append(parser.toJsonString()).append(",");
			}
		}
		String sbString = sb.toString();
		if(sbString.endsWith(",")){
			sbString = sbString.substring(0, sb.length() - 1);
		}
		return sbString + "]";
	}

}

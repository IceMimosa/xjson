package org.icemimosa.xjson.parser;

import java.lang.reflect.Array;
import java.util.Collection;

import org.icemimosa.xjson.JsonConfig;

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
				prettyFormat(sb, parser, null);
			}
		}
		// 2. 集合类型解析
		else if(obj instanceof Collection){
			for (Object objValue : (Collection<?>)obj) {
				JSONParser parser = JSONParserFactory.getInstance().getParser(objValue, this.jsonConfig);
				prettyFormat(sb, parser, null);
			}
		}
		return deleteLastComma(sb.toString()) + "]";
	}

}

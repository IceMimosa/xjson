package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

import org.icemimosa.xjson.utils.Constants;
import org.icemimosa.xjson.utils.StringUtils;

public class PrimitiveDeserializer extends AbstractDeserialzer {

	public PrimitiveDeserializer(String json, Type type, JSONAnalyzer analyzer) {
		super(json, type, analyzer);
	}

	@Override
	public Object deserialzer() {
		if (StringUtils.isBlank(json)) {
			return null;
		}
		Object retValue = null;
		// 字符串
		if (json.startsWith(Constants.DOUBLE_QUOTE) || json.startsWith(Constants.SINGLE_QUOTE)) {
			retValue = json.substring(1, json.length() - 1);
		} 
		// true || false
		else if(json.startsWith("t") || json.startsWith("f")){
			retValue = new Boolean(json);
		}
		// null || undefined
		else if(json.startsWith("n") || json.startsWith("u")){
			retValue = null;
		}
		
		return retValue;
	}
}

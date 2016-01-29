package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.JsonConfig;

public class PrimitiveParser extends AbstractJSONParser {

	public PrimitiveParser(Object obj, JsonConfig jsonConfig) {
		super(obj, jsonConfig);
	}

	@Override
	public String toJsonString() {
		String retValue = null;
		if (obj == null) {
			retValue = null;
		} else if (obj instanceof String) {
			retValue = constantManager.getQuote() + obj.toString() + constantManager.getQuote();
		} else if (obj.getClass() == char.class || obj instanceof Character) {
			retValue = constantManager.getQuote() + obj.toString() + constantManager.getQuote();
		} else {
			retValue = obj.toString();
		}
		return retValue;
	}

}

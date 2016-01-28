package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.utils.ConstantManager;

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
			retValue = ConstantManager.getQuote() + obj.toString() + ConstantManager.getQuote();
		} else if (obj.getClass() == char.class || obj instanceof Character) {
			retValue = ConstantManager.getQuote() + obj.toString() + ConstantManager.getQuote();
		} else {
			retValue = obj.toString();
		}
		return prettyFormatString(retValue);
	}

	private String prettyFormatString(String value) {
		if (jsonConfig.isPrettyFormat()) {
			return " " + value + " ";
		}
		return value;
	}
}

package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.JsonConfig;

public abstract class AbstractJSONParser implements JSONParser {

	protected Object obj;
	protected JsonConfig jsonConfig;

	public AbstractJSONParser(Object obj, JsonConfig jsonConfig) {
		this.obj = obj;
		this.jsonConfig = jsonConfig;
	}

}

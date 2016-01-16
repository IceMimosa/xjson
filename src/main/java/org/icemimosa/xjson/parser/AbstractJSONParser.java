package org.icemimosa.xjson.parser;

public abstract class AbstractJSONParser implements JSONParser {
	
	protected Object obj;

	public AbstractJSONParser(Object obj) {
		this.obj = obj;
	}
	
}

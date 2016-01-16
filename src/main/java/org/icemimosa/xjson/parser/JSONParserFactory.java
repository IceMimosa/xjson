package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.utils.TypeUtils;

public class JSONParserFactory {

	private static JSONParserFactory factory = new JSONParserFactory();

	private JSONParserFactory() {
	}

	public static JSONParserFactory getInstance() {
		return factory;
	}

	public JSONParser getParser(Object obj) {
		JSONParser parser = null;
		if(TypeUtils.isPrimitive(obj)){
			parser = new PrimitiveParser(obj);
		}else{
			parser = new JavaBeanParser(obj);
		}
		return parser;
	}
}

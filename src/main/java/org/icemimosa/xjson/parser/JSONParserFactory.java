package org.icemimosa.xjson.parser;

public class JSONParserFactory {

	private static JSONParserFactory factory = new JSONParserFactory();

	private JSONParserFactory() {
	}

	public static JSONParserFactory getInstance() {
		return factory;
	}

	public JSONParser getParser(Object obj) {
		JSONParser parser = new JavaBeanParser();
		return parser;
	}
}

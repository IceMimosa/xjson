package org.icemimosa.xjson;

import org.icemimosa.xjson.parser.JSONParser;
import org.icemimosa.xjson.parser.JSONParserFactory;

public class JSON {
	
	public static String getString(Object obj){
		JSONParser parser = JSONParserFactory.getInstance().getParser(obj);
		return parser.toJsonString();
	}
}

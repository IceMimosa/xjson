package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

public abstract class AbstractDeserialzer implements JSONDeserializer {

	protected String json;
	protected Type type;
	protected JSONAnalyzer analyzer;

	public AbstractDeserialzer(String json, Type type, JSONAnalyzer analyzer) {
		this.json = json;
		this.type = type;
		this.analyzer = analyzer;
	}

}

package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.utils.TypeUtils;

/**
 * 根据对象类型获取相应的parser解析类
 * @author ChenKai[514793425@qq.com]
 *
 */
public class JSONParserFactory {

	private static JSONParserFactory factory = new JSONParserFactory();

	private JSONParserFactory() {
	}

	public static JSONParserFactory getInstance() {
		return factory;
	}

	public JSONParser getParser(Object obj) {
		JSONParser parser = null;
		if(obj == null || TypeUtils.isPrimitive(obj)){
			parser = new PrimitiveParser(obj);
		}else if(TypeUtils.isCollectionOrArray(obj)){
			parser = new CollectionParser(obj);
		}else if(TypeUtils.isMap(obj)){
			parser = new MapParser(obj);
		}else{
			parser = new JavaBeanParser(obj);
		}
		return parser;
	}
}

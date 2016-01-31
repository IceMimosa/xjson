package org.icemimosa.xjson.parser;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.utils.TypeUtils;

public class JavaBeanParser extends AbstractJSONParser {

	public JavaBeanParser(Object obj, JsonConfig jsonConfig) {
		super(obj, jsonConfig);
	}

	@Override
	public String toJsonString() {
		if(obj == null){
			return null;
		}
		
		// 通过反射获取所有的方法
		Map<String, Method> methodMap = TypeUtils.getObjectMethods(obj, jsonConfig);
		
		// 获取所有的成员变量
		StringBuilder sb = new StringBuilder("{");
		String[] fieldNames = TypeUtils.getObjectFieldNames(obj, jsonConfig);
		for (int i = 0; fieldNames != null && i < fieldNames.length; i++) {
			String fieldName = fieldNames[i];
			Method getMethod = methodMap.get(fieldName);
			if(getMethod != null){
				try {
					getMethod.setAccessible(true);
					// 变量名称的json串
					JSONParser fieldNameParser = JSONParserFactory.getInstance().getParser(fieldName, this.jsonConfig);
					// 值的json串
					Object value = getMethod.invoke(obj);
					JSONParser valueParser = JSONParserFactory.getInstance().getParser(value, this.jsonConfig);
					
					prettyFormat(sb, fieldNameParser, valueParser);
					
					getMethod.setAccessible(false);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		return deleteLastComma(sb.toString()) + "}";
	}
}

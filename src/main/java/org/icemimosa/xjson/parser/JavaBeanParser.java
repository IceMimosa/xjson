package org.icemimosa.xjson.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.icemimosa.xjson.JsonConfig;

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
		Method[] methods = obj.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>();
		for (int i = 0; methods != null && i < methods.length; i++) {
			if(methods[i].getName().substring(0, 3).equalsIgnoreCase("get")){
				methodMap.put(methods[i].getName().substring(3), methods[i]);
			}
		}
		
		// 获取所有的成员变量
		StringBuilder sb = new StringBuilder("{");
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; fields != null && i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String getMethodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = methodMap.get(getMethodName);
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

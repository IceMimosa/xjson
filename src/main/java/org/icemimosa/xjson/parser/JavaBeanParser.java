package org.icemimosa.xjson.parser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JavaBeanParser extends AbstractJSONParser {

	public JavaBeanParser(Object obj) {
		super(obj);
	}

	@Override
	public String toJsonString() {
		Method[] methods = obj.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>();
		for (int i = 0; methods != null && i < methods.length; i++) {
			if(methods[i].getName().substring(0, 3).equalsIgnoreCase("get")){
				methodMap.put(methods[i].getName().substring(3), methods[i]);
			}
		}
		StringBuilder sb = new StringBuilder("{");
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; fields != null && i < fields.length; i++) {
			String fieldName = fields[i].getName();
			String getMethodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			Method getMethod = methodMap.get(getMethodName);
			if(getMethod != null){
				try {
					getMethod.setAccessible(true);
					Object value = getMethod.invoke(obj);
					if(value == null){
						sb.append("\"").append(fieldName).append("\":null,");
					}else{
						JSONParser parser = JSONParserFactory.getInstance().getParser(value);
						sb.append("\"").append(fieldName).append("\":").append(parser.toJsonString()).append(",");
					}
					getMethod.setAccessible(false);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
		String sbString = sb.substring(0, sb.length() - 1);
		return sbString + "}";
	}
}

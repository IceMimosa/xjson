package org.icemimosa.xjson.parser;

import java.lang.reflect.Field;

public class JavaBeanParser implements JSONParser{

	@Override
	public String toJsonString(Object obj) {
		
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; fields != null && i < fields.length; i++) {
			System.out.println(fields[i].getName());
			try {
				fields[i].setAccessible(true);
				System.out.println(fields[i].get(obj));
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}

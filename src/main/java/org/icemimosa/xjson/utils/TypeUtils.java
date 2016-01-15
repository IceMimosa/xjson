package org.icemimosa.xjson.utils;

public class TypeUtils {

	public static boolean isPrimitive(Object obj) {
		Class<?> clazz = obj.getClass();
		return (
			// -- 8个基本类型和包装类
			clazz.equals(Byte.class) || //
			clazz.equals(Short.class) || //
			clazz.equals(Integer.class) || //
			clazz.equals(Long.class) || //
			clazz.equals(Float.class) || //
			clazz.equals(Double.class) || //
			clazz.equals(Character.class) || //
			clazz.equals(Boolean.class) || //
			clazz.isPrimitive() || //
			// -- String 类型
			clazz.equals(String.class) //
			// clazz.equals(BigDecimal.class) || //
			// clazz.equals(BigInteger.class) || //
			// clazz.equals(Date.class) || //
			// clazz.equals(DateTime.class)
		);
	}

}

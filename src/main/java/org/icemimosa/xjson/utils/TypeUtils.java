package org.icemimosa.xjson.utils;

import java.util.Collection;
import java.util.Map;

/**
 * 判断对象类型工具包
 * @author ChenKai[514793425@qq.com]
 *
 */
public class TypeUtils {

	/**
	 * 判断一个对象是否是基本类型
	 * @param obj
	 * @return
	 */
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
	
	/**
	 * 判断一个对象是否是集合Collection或者数组Array
	 * @param obj
	 * @return
	 */
	public static boolean isCollectionOrArray(Object obj){
		return (	//
			obj instanceof Collection || //
			obj.getClass().isArray()	//
		);
	}
	
	/**
	 * 判断一个对象是否是Map
	 * @param obj
	 * @return
	 */
	public static boolean isMap(Object obj){
		return (	//
			obj instanceof Map
		);
	}
}

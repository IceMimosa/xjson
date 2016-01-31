package org.icemimosa.xjson.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icemimosa.xjson.JsonConfig;

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
	
	/**
	 * 获取一个对象的所有公有getter和setter方法
	 * @param obj 对象
	 * @param jsonConfig 其中过滤属性的配置起作用
	 * @return 返回一个Map集合, 键值是属性名, 值是方法
	 */
	public static Map<String, Method> getObjectMethods(Object obj, JsonConfig jsonConfig){
		
		// TODO 取缓存获取所有方法
		
		Method[] methods = obj.getClass().getMethods();
		Map<String, Method> methodMap = new HashMap<String, Method>();
		for (int i = 0; methods != null && i < methods.length; i++) {
			String methodName = methods[i].getName();
			String key = "";
			if(methods[i].getName().substring(0, 3).equalsIgnoreCase("get")){
				key = methodName.substring(3);
			}else if(methods[i].getName().substring(0, 2).equalsIgnoreCase("is")){
				key = methodName.substring(2);
				// 存在 boolean 类型的两种情况: isXxx 和 xXX
				methodMap.put(methodName, methods[i]);
			}
			
			// 存入map中
			if(StringUtils.isNotBlank(key)){
				methodMap.put(key.substring(0,1).toLowerCase() + key.substring(1), methods[i]);
			}
		}
		return methodMap;
	}
	
	/**
	 * 获取一个对象所有字段的名称
	 * @param obj 对象
	 * @param jsonConfig 其中过滤属性的配置起作用
	 * @return 返回字段名称的数组
	 */
	public static String[] getObjectFieldNames(Object obj, JsonConfig jsonConfig){
	
		// TODO 取缓存获取所有字段
		
		// 将过滤的属性存入到一个map中
		String[] propertyFilters = jsonConfig.getPropertyFilters();
		Map<String, String> propertyFilterMap = new HashMap<String,String>();
		for (int i = 0; propertyFilters != null && i < propertyFilters.length; i++) {
			propertyFilterMap.put(propertyFilters[i], propertyFilters[i]);
		}
		
		// 过滤属性
		List<String> retFieldNames = new ArrayList<String>(); 
		Field[] fields = obj.getClass().getDeclaredFields();
		for (int i = 0; fields != null && i < fields.length; i++) {
			if(propertyFilterMap.get(fields[i].getName()) == null){
				retFieldNames.add(fields[i].getName());
			}
		}
		return retFieldNames.toArray(new String[]{});
	}
}

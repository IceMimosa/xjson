package org.icemimosa.xjson.utils;

/**
 * 常量类
 * 
 * @author ChenKai[514793425@qq.com]
 */
public class Constants {

	/** 双引号 */
	public static final String DOUBLE_QUOTE = "\"";
	/** 单引号 */
	public static final String SINGLE_QUOTE = "\'";
	/** 回车符 */
	public static final String ENTER = System.getProperty("line.separator");
	/** 制表符 */
	public static final String TAB = "\t";
	/** 空白符 */
	public static final String BLANK = " ";
	/** 点号 */
	public static final String DOT = ".";
	/** 逗号 */
	public static final String COMMA = ",";
	/** 冒号 */
	public static final String COLON = ":";
	/** 左大括号 */
	public static final String L_BRACE = "{";
	/** 右大括号 */
	public static final String R_BRACE = "}";
	/** 左方括号 */
	public static final String L_SQUARE = "[";
	/** 右方括号 */
	public static final String R_SQUARE = "]";

	/**
	 * JSON串分隔符
	 */
	public class JSONToken {

		/** 字符结束 */
		public static final int EOF = -1;
		/** 双引号 */
		public static final char DOUBLE_QUOTE = '\"';
		/** 单引号 */
		public static final char SINGLE_QUOTE = '\'';
		/** 冒号 */
		public static final char COLON = ':';
		/** 点号 */
		public static final char DOT = '.';
		/** 逗号 */
		public static final char COMMA = ',';
		/** 左大括号 */
		public static final char L_BRACE = '{';
		/** 右大括号 */
		public static final char R_BRACE = '}';
		/** 左方括号 */
		public static final char L_SQUARE = '[';
		/** 右方括号 */
		public static final char R_SQUARE = ']';
	}
}

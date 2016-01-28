package org.icemimosa.xjson.utils;

public class ConstantManager {

	/**
	 * 引号引用，单引号还是双引号
	 */
	private static String quote = Constants.DOUBLE_QUOTE;
	
	/**
	 * 漂亮格式化输出的符号
	 */
	private static String prettySymbol = "";
	
	/**
	 * 获取回车符
	 */
	private static String enterSymbol = Constants.ENTER;

	public static String getQuote() {
		return quote;
	}

	public static void setQuote(String quote) {
		ConstantManager.quote = quote;
	}

	public static String getPrettySymbol() {
		return prettySymbol;
	}

	public static void setPrettySymbol(String prettySymbol) {
		ConstantManager.prettySymbol = prettySymbol;
	}

	public static String getEnterSymbol() {
		return enterSymbol;
	}
	
}

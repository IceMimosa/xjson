package org.icemimosa.xjson.utils;

public class StringUtils {

	/**
	 * null,空白符为true
	 * 
	 * @param cs
	 * @return
	 */
	public static boolean isBlank(final CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	public static boolean isNotBlank(final CharSequence cs) {
		return !isBlank(cs);
	}

	/**
	 * 根据位置找出错误信息
	 * 
	 * @param error
	 *            错误信息原字符串
	 * @param start
	 *            错误字符开始位置
	 * @param end
	 *            错误字符结束位置
	 * 
	 * @return 带有错误标识的字符串
	 */
	public static String getErrorString(String error, int start, int end) {

		int length = error.length();

		if (start > end || start > length || end < 0) {
			return error;
		}
		if (start < 0) {
			start = 0;
		}
		if (end > error.length()) {
			end = error.length();
		}

		StringBuilder sb = new StringBuilder(error);
		sb.insert(start, "ˇ");
		sb.insert(end + 1, "ˇ");

		return sb.toString();
	}
}

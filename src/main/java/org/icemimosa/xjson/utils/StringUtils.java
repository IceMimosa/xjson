package org.icemimosa.xjson.utils;

public class StringUtils {
	
	/**
	 * null,空白符为true
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
	
}

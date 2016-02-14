package org.icemimosa.xjson.deserializer;

import org.icemimosa.xjson.JsonException;
import org.icemimosa.xjson.utils.Constants;
import org.icemimosa.xjson.utils.StringUtils;

public class DefaultJSONAnalyzer implements JSONAnalyzer {
	private String json = "";
	private char[] chars = {};
	private int index;
	private int currValueState;
	private char currValue;
	private Object stringValue;
	private Object keyTemp;

	public DefaultJSONAnalyzer(String json) {
		if (StringUtils.isNotBlank(json)) {
			this.json = json.trim();
			this.chars = this.json.toCharArray();
		}
	}

	@Override
	public int nextToken() {
		char ch;
		for (;;) {
			int ichar = next();
			if (ichar == Constants.JSONToken.EOF) {
				return Constants.JSONToken.EOF;
			}
			ch = (char) ichar;
			currValue = ch;
			if (ch == '{') {
				currValueState = OGJECT;
				break;
			}
			if (ch == '\"' || ch == '\'') {
				currValueState = VALUE;
				scanString();
				break;
			}
			if (ch == '[') {
				currValueState = ARRAY;
				break;
			}
			if (String.valueOf(ch).matches("\\s")) {
				currValueState = OTHER;
				continue;
			}
			if (ch == ':') {
				currValueState = OTHER;
				if (keyTemp == null) {
					StringBuilder sb = new StringBuilder("");
					// 获取冒号前面的非引号字符串
					int pos = index - 2;
					for (; pos >= 0; pos--) {
						if (chars[pos] == ',' || chars[pos] == '{') {
							break;
						}
						sb.append(chars[pos]);
					}
					stringValue = validatekey(sb.reverse().toString(), pos + 1);
				}
				break;
			}
			if (ch == ',') {
				currValueState = OTHER;
				keyTemp = null;
				continue;
			}
			if (ch == '}' || ch == ']') {
				currValueState = END;
				break;
			}
		}
		return ch;
	}

	@Override
	public int next() {
		if (index >= chars.length) {
			return Constants.JSONToken.EOF;
		}
		return chars[index++];
	}

	@Override
	public boolean hasNext() {
		return index < chars.length;
	}

	@Override
	public void scanString() {
		StringBuilder sb = new StringBuilder();
		for (;;) {
			char ch = chars[index++];
			// 如果值在双引号内
			if (currValue == '\"' && ch == '\"') {
				break;
			}
			// 如果值在单引号内
			else if (currValue == '\'' && ch == '\'') {
				break;
			}
			sb.append(ch);
		}
		stringValue = keyTemp = sb.toString();
	}

	@Override
	public String getStringValue() {
		return String.valueOf(stringValue);
	}

	@Override
	public int getCurrValueState() {
		return currValueState;
	}

	/**
	 * 校验非引号key值的正确性
	 * 
	 * @param key
	 * @param pos
	 * @return
	 */
	private String validatekey(String key, int pos) {
		
		// 去除key前后空白符
		int start = 0;
		while (("" + key.charAt(start)).matches("\\s")) {
			start++;
		}
		int end = key.length() - 1;
		while (("" + key.charAt(end)).matches("\\s")) {
			end--;
		}
		key = key.substring(start, end + 1);
		
		pos = pos + start;
		for (int i = 0; i < key.length(); i++) {
			// 开头不能为其他字符
			if(i == 0 && !("" + key.charAt(i)).matches("[a-zA-z_$]")){
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, 位置 : " + (pos + i));
			}
			if(!("" + key.charAt(i)).matches("[\\w_$]")){
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, 位置 : " + (pos + i));
			}
		}
		return key;
	}
}
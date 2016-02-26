package org.icemimosa.xjson.deserializer;

import org.icemimosa.xjson.JsonException;
import org.icemimosa.xjson.utils.Constants;
import org.icemimosa.xjson.utils.StringUtils;

/**
 * 默认JSON串的解析类
 * @author ChenKai[514793425@qq.com]
 */
public class DefaultJSONAnalyzer implements JSONAnalyzer {
	private String json = "";
	private char[] chars = {};
	private int index;
	private char currValue;
	private int currValueState;
	
	private Object key;
	private Object value;

	private String stringValue;

	public DefaultJSONAnalyzer(String json) {
		if (StringUtils.isNotBlank(json)) {
			this.json = json.trim();
			this.chars = this.json.toCharArray();
		}
	}

	@Override
	public int next() {
		if (index >= chars.length) {
			return Constants.JSONToken.EOF;
		}
		return currValue = chars[index++];
	}

	@Override
	public boolean hasNext() {
		return index < chars.length;
	}
	
	@Override
	public int getCurrValueState() {
		return currValueState;
	}

	@Override
	public int nextToken() {
		char ch;
		for (;;) {
			// json串的末尾
			if (next() == Constants.JSONToken.EOF) {
				return Constants.JSONToken.EOF;
			}
			ch = currValue;
			// 跳过空白符
			if (String.valueOf(ch).matches("\\s")) {
				currValueState = OTHER;
				continue;
			}
			// 如果是对象, 扫描一次key
			if (ch == '{') {
				currValueState = OBJECT;
				scanKey();
				break;
			}
			if (ch == '[') {
				currValueState = ARRAY;
				break;
			}
			// 扫描值
			if (ch == ':') {
				currValueState = VALUE;
				while (String.valueOf(next()).matches("\\s")) {
				}
				scanValue();
				break;
			}
			// 扫描下一组, 扫描一次key
			if (ch == ',') {
				currValueState = OTHER;
				scanKey();
				break;
			}
			// 扫描结束 
			if (ch == '}' || ch == ']') {
				currValueState = END;
				break;
			}
		}
		return ch;
	}

	@Override
	public void scanKey() {
		// 取出空白符
		while (String.valueOf(next()).matches("\\s")) {
		}

		// 如果到了结尾
		if (currValue == '}' || currValue == ']') {
			currValueState = END;
			return;
		}
		
		// 如果key是字符串
		if (currValue == '\"' || currValue == '\'') {
			scanString();
			key = stringValue;
		} 
		// 否则是不带引号的串
		else {
			int pos = index - 1;
			StringBuilder sb = new StringBuilder("" + currValue);
			while (next() != ':') {
				sb.append(currValue);
			}
			index--;
			key = validatekey(sb.toString().trim(), pos);
		}
	}

	@Override
	public Object getKey() {
		return key;
	}

	@Override
	public void scanValue() {
		// 如果是字符串
		if (currValue == '\"' || currValue == '\'') {
			scanString();
		}
		// null
		else if(currValue == 'n'){
			scanNULL();
		}
		// undefined
		else if(currValue == 'u'){
			scanUNDEFINED();
		}
		// json对象
		else if(currValue == '{'){
			currValueState = OBJECT;
			scanKey();
		}
	}
	
	@Override
	public Object getValue() {
		return value;
	}

	private void scanString() {
		StringBuilder sb = new StringBuilder();
		for (;;) {
			char ch = (char) next();
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
		value = stringValue = sb.toString();
	}
	
	private void scanNULL() {
		if(next() != 'u'){
			throw new JsonException("符号错误, 期望值为 null, 位置 : " + (index - 1));
		}
		if(next() != 'l'){
			throw new JsonException("符号错误, 期望值为 null, 位置 : " + (index - 1));
		}
		if(next() != 'l'){
			throw new JsonException("符号错误, 期望值为 null, 位置 : " + (index - 1));
		}
		while(next() != ','){
			throw new JsonException("符号错误, 期望值为 null, 位置 : " + (index - 1));
		}
		index --;
		value = null;
	}
	
	private void scanUNDEFINED() {
		if(next() != 'n'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'd'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'e'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'f'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'i'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'n'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'e'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		if(next() != 'd'){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		while(next() != ','){
			throw new JsonException("符号错误, 期望值为 undefined, 位置 : " + (index - 1));
		}
		index --;
		value = null;
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
			if (i == 0 && !("" + key.charAt(i)).matches("[a-zA-z_$]")) {
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, 位置 : " + (pos + i));
			}
			// 不能含有特殊字符
			if (!("" + key.charAt(i)).matches("[\\w_$]")) {
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, 位置 : " + (pos + i));
			}
		}
		return key;
	}

}
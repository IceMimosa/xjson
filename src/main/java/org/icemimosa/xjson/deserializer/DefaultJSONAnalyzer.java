package org.icemimosa.xjson.deserializer;

import org.icemimosa.xjson.JsonException;
import org.icemimosa.xjson.utils.Constants;
import org.icemimosa.xjson.utils.StringUtils;

/**
 * 默认JSON串的解析类
 * 
 * @author ChenKai[514793425@qq.com]
 */
public class DefaultJSONAnalyzer implements JSONAnalyzer {
	private String json = "";
	private char[] chars = {};
	private int index;
	private char currValue;
	private int currValueState;

	private int currOperValueState;

	private Object key;
	private Object value;
	private Object arrayValue;

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
				currOperValueState = OBJECT;
				scanKey();
				break;
			}
			if (ch == '[') {
				currValueState = ARRAY;
				currOperValueState = ARRAY;
				scanArrayValue();
				break;
			}
			// 扫描值
			if (ch == ':') {
				currValueState = VALUE;
				while (String.valueOf((char) next()).matches("\\s")) {
				}
				scanValue();
				break;
			}
			// 扫描下一组, 扫描一次key
			if (ch == ',') {
				currValueState = OTHER;
				if (currOperValueState == OBJECT) {
					scanKey();
				} else if (currOperValueState == ARRAY) {
					scanArrayValue();
				}
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
		while (String.valueOf((char)(next())).matches("\\s")) {
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
			StringBuilder sb = new StringBuilder();
			sb.append(currValue);
			// 这里会出现死循环, 优化
			while (next() != ':') {
				if (currValue == ',') {
					throw new JsonException("符号 [ " + currValue + " ] 错误, 缺少相应的value值" //
							+ "位置 : " + (index - 1) + ", "//
							+ StringUtils.getErrorString(json, index - 1, index));
				}
				if (!("" + currValue).matches("[\\w_$]")) {
					throw new JsonException("符号 [ " + currValue + " ] 错误" //
							+ "位置 : " + (index - 1) + ", "//
							+ StringUtils.getErrorString(json, index - 1, index));
				}
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
		else if (currValue == 'n') {
			// scanNULL();
			scanExpect("null");
		}
		// undefined
		else if (currValue == 'u') {
			// scanUNDEFINED();
			scanExpect("undefined");
		}
		// true
		else if (currValue == 't') {
			// scanTRUE();
			scanExpect("true");
		}
		// false
		else if (currValue == 'f') {
			// scanFALSE();
			scanExpect("false");
		}
		// 数字
		else if (("" + currValue).matches("[0-9.]")) {
			scanNumber();
		}
		// json对象
		else if (currValue == '{') {
			currValueState = OBJECT;
			currOperValueState = OBJECT;
			scanKey();
		} else if (currValue == '[') {
			currValueState = ARRAY;
			currOperValueState = ARRAY;
			scanArrayValue();
		}
		// 非法字符
		else {
			throw new JsonException("符号 [ " + currValue + " ] 错误" //
					+ "位置 : " + (index - 1) + ", "//
					+ StringUtils.getErrorString(json, index - 1, index));
		}
	}

	@Override
	public Object getValue() {
		return value;
	}

	@Override
	public void scanArrayValue() {
		// 取出空白符
		while (String.valueOf((char)(next())).matches("\\s")) {
		}

		// 如果到了结尾
		if (currValue == '}' || currValue == ']') {
			currValueState = END;
			return;
		}

		// 如果是字符串
		if (currValue == '\"' || currValue == '\'') {
			currValueState = VALUE;
			scanString();
		}
		// null
		else if (currValue == 'n') {
			currValueState = VALUE;
			scanExpect("null");
		}
		// undefined
		else if (currValue == 'u') {
			currValueState = VALUE;
			scanExpect("undefined");
		}
		// true
		else if (currValue == 't') {
			currValueState = VALUE;
			scanExpect("true");
		}
		// false
		else if (currValue == 'f') {
			currValueState = VALUE;
			scanExpect("false");
		}
		// 数字
		else if (("" + currValue).matches("[0-9.]")) {
			currValueState = VALUE;
			scanNumber();
		}
		// JsonObject
		else if (currValue == '{') {
			currValueState = OBJECT;
			currOperValueState = OBJECT;
			scanKey();
		}
		arrayValue = value;
	}

	@Override
	public Object getArrayValue() {
		return arrayValue;
	}

	/**
	 * 扫描字符串格式
	 */
	private void scanString() {
		StringBuilder sb = new StringBuilder();
		sb.append(currValue);
		for (;;) {
			char ch = (char) next();
			sb.append(ch);
			// 如果值在双引号内
			if (currValue == '\"' && ch == '\"') {
				break;
			}
			// 如果值在单引号内
			else if (currValue == '\'' && ch == '\'') {
				break;
			}
		}
		value = stringValue = sb.toString();
	}

	/**
	 * 扫面所期望的字符串
	 * 
	 * @param expect
	 *            如null,undefined,true,false
	 */
	private void scanExpect(String expect) {

		if (StringUtils.isBlank(expect)) {
			return;
		}

		// 1. 判断所期望的字符串
		for (int i = 1; i < expect.length(); i++) {
			char nChar = (char) next();
			if (nChar != expect.charAt(i)) {
				throw new JsonException("符号 [ " + nChar + " ] 错误, 期望值为 " + expect + "," //
						+ "位置 : " + (index - 1) + ", "//
						+ StringUtils.getErrorString(json, index - 1, index));
			}
		}

		// 2. 去除空白符
		char nChar = (char) next();
		while (("" + nChar).matches("\\s")) {
			nChar = (char) next();
			continue;
		}

		// 3. 判断是否到达结尾
		if (nChar != ',' && nChar != '}' && nChar != ']') {
			throw new JsonException("符号 [ " + nChar + " ] 错误, 期望值为 " + expect + "," //
					+ "位置 : " + (index - 1) + ", "//
					+ StringUtils.getErrorString(json, index - 1, index));
		}
		index--;
		value = expect;
	}

	/**
	 * 扫描数字格式
	 */
	private void scanNumber() {
		StringBuilder sb = new StringBuilder();
		// 小数点数量
		int dotCount = 0;
		// 空白符是否合法
		int blankIndex = -1;

		while (currValue != ',' && currValue != '}' && currValue != ']') {
			// 1. 跳过空白符
			if (("" + currValue).matches("\\s")) {
				blankIndex = index;
				next();
				continue;
			}
			// 2. 记录小数点
			if (currValue == '.') {
				if (dotCount > 1) {
					throw new JsonException("符号 [ " + currValue + " ] 错误" //
							+ "位置 : " + (index - 1) + ", "//
							+ StringUtils.getErrorString(json, index - 1, index));
				}
				dotCount++;
				sb.append(currValue);
				next();
				continue;
			}
			// 3. 非数字字符验证
			if (!("" + currValue).matches("[0-9]")) {
				throw new JsonException("符号 [ " + currValue + " ] 错误, 期望值为数字" //
						+ "位置 : " + (index - 1) + ", "//
						+ StringUtils.getErrorString(json, index - 1, index));
			}
			// 4. 是否空白符非法
			if (blankIndex != -1) {
				throw new JsonException("符号错误, 期望值为数字" //
						+ "位置 : " + (index - 2) + ", "//
						+ StringUtils.getErrorString(json, index - 2, index - 1));
			}
			sb.append(currValue);
			next();
		}
		index--;
		value = sb.toString();
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
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, " //
						+ "位置 : " + (pos + i) + ", 不支持的 [符号, 对象, 数组] 类型, " //
						+ StringUtils.getErrorString(json, pos + i, pos + i + 1));

			}
			// 不能含有特殊字符
			if (!("" + key.charAt(i)).matches("[\\w_$]")) {
				throw new JsonException("符号 [" + key.charAt(i) + "] 错误, " //
						+ "位置 : " + (pos + i) + ", 不支持的 [符号, 对象, 数组] 类型, " //
						+ StringUtils.getErrorString(json, pos + i, pos + i + 1));
			}
		}
		return key;
	}
}
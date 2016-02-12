package org.icemimosa.xjson.deserializer;

import java.lang.reflect.Type;

import org.icemimosa.xjson.JsonException;
import org.icemimosa.xjson.utils.Constants;
import org.icemimosa.xjson.utils.StringUtils;

public abstract class AbstractDeserialzer implements JSONDeserializer {

	protected String json;
	protected Type type;
	protected JSONAnalyzer analyzer;

	public AbstractDeserialzer(String json, Type type) {
		this.json = json;
		this.type = type;
		analyzer = new DefaultJSONAnalyzer(json);
	}

	class DefaultJSONAnalyzer implements JSONAnalyzer {

		private String json = "";
		private char[] chars = {};
		private int index;
		private int currValueState;
		private Object CurrValue;
		
		public DefaultJSONAnalyzer(String json) {
			if (StringUtils.isNotBlank(json)) {
				this.json = json.trim();
				this.chars = json.trim().toCharArray();
			}
		}

		@Override
		public int nextToken() {
			char ch;
			for (;;) {
				int i = next();
				if (i == Constants.JSONToken.EOF) {
					return Constants.JSONToken.EOF;
				}
				ch = (char) i;
				if (ch == '{') {
					currValueState = OGJECT;
					break;
				}
				if (ch == '\"') {
					currValueState = VALUE;
					scanString();
					break;
				}
				if (ch == '[') {
					currValueState = ARRAY;
					break;
				}
				if (ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r') {
					continue;
				}
				if(ch == ':' || ch == ','){
					continue;
				}
				if(ch == '}' || ch == ']'){
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

		public void scanString() {
			int sp = index;
			StringBuilder sb = new StringBuilder();
			for (;;) {
				char ch = chars[sp++];
				if (ch == '\"') {
					break;
				}
				sb.append(ch);
			}
			CurrValue = sb.toString();
		}
		
		public String getStringValue(){
			return String.valueOf(CurrValue);
		}
	}
}

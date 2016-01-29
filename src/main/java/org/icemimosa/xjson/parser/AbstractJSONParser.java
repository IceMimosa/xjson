package org.icemimosa.xjson.parser;

import org.icemimosa.xjson.JsonConfig;
import org.icemimosa.xjson.utils.ConstantManager;


public abstract class AbstractJSONParser implements JSONParser {

	protected Object obj;
	protected JsonConfig jsonConfig;
	protected ConstantManager constantManager;

	public AbstractJSONParser(Object obj, JsonConfig jsonConfig) {
		this.obj = obj;
		this.jsonConfig = jsonConfig;
		constantManager = jsonConfig.getConstantManager();
	}
	
	/**
	 * 美化输出
	 * @param sb
	 * @param keyParser
	 * @param valueParser
	 */
	protected void prettyFormat(StringBuilder sb, JSONParser keyParser, JSONParser valueParser){
		if(jsonConfig.isPrettyFormat()){
			if(null != keyParser){
				sb.append(constantManager.getPrettySymbol());
				constantManager.addBlankSymbol(constantManager.getPrettyBlankCount());
				sb.append(keyParser.toJsonString());
				if(null != valueParser){
					sb.append(":");
					constantManager.addBlankSymbol(constantManager.getPrettyBlankCount());
					sb.append(valueParser.toJsonString());
					constantManager.addBlankSymbol(-1 * constantManager.getPrettyBlankCount());
				}
				sb.append(",");
				constantManager.addBlankSymbol(-1 * constantManager.getPrettyBlankCount());
			}
		}else{
			if(null != keyParser){
				sb.append(keyParser.toJsonString());
				if(null != valueParser){
					sb.append(":");
					sb.append(valueParser.toJsonString());
				}
				sb.append(",");
			}
		}
	}
	
	/**
	 * 如果最后存在逗号"," 那么去除之
	 * @param str
	 * @return
	 */
	protected String deleteLastComma(String str){
		if(str.endsWith(",")){
			str = str.substring(0, str.length() - 1);
			// 如果是美化输出
			if(jsonConfig.isPrettyFormat()){
				constantManager.addBlankSymbol(-1 * constantManager.getPrettyBlankCount());
				str += constantManager.getPrettySymbol();
				constantManager.addBlankSymbol(constantManager.getPrettyBlankCount());
			}
		}
		return str;
	}
}

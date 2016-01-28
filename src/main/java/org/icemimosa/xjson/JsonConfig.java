package org.icemimosa.xjson;

import org.icemimosa.xjson.utils.ConstantManager;
import org.icemimosa.xjson.utils.Constants;

public class JsonConfig {
	/**
	 * 是否是单引号. true为单引号, false为双引号, 默认false
	 */
	private boolean isSingleQuote = false;
	
	/**
	 * 是否将json串以漂亮的格式输出
	 */
	private boolean isPrettyFormat = false;
	
	
	/**
	 * 设置键和值的引号类型. 
	 * @param isSingleQuote	---- true为单引号, false为双引号, 默认false
	 */
	public void setSingleQuote(boolean isSingleQuote) {
		this.isSingleQuote = isSingleQuote;
		ConstantManager.setQuote(Constants.DOUBLE_QUOTE);
		if(isSingleQuote){
			ConstantManager.setQuote(Constants.SINGLE_QUOTE);
		}
	}
	public boolean isSingleQuote() {
		return isSingleQuote;
	}
	
	
	/**
	 * 设置json的输出格式
	 * 
	 * @param isPrettyFormat ---- true为漂亮输出, false为一行字符串输出, 默认false
	 */
	public void setPrettyFormat(boolean isPrettyFormat) {
		this.setPrettyFormat(isPrettyFormat, 0);
	}
	
	/**
	 * 设置json的输出格式, 以及格式空格的数量
	 * @param isPrettyFormat ---- true为漂亮输出, false为一行字符串输出, 默认false
	 * @param blankCount ---- 等于或小于0为制表符 \t, 否则为空格的数量
	 */
	public void setPrettyFormat(boolean isPrettyFormat, int blankCount) {
		this.isPrettyFormat = isPrettyFormat;
		StringBuilder symbol = new StringBuilder("");
		if(isPrettyFormat){
			symbol.append(Constants.ENTER);
			if(blankCount <= 0){
				symbol.append(Constants.TAB);
			}else{
				for (int i = 0; i < blankCount; i++) {
					symbol.append(" ");
				}
			}
		}
		ConstantManager.setPrettySymbol(symbol.toString());
	}
	
	public boolean isPrettyFormat() {
		return isPrettyFormat;
	}
}

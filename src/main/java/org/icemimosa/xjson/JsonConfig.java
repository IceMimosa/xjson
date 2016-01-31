package org.icemimosa.xjson;

import org.icemimosa.xjson.utils.ConstantManager;
import org.icemimosa.xjson.utils.Constants;

public class JsonConfig {

	public JsonConfig() {
		constantManager = new ConstantManager();
	}

	private ConstantManager constantManager;
	
	public ConstantManager getConstantManager() {
		return constantManager;
	}
	
	/**
	 * 是否是单引号. true为单引号, false为双引号, 默认false
	 */
	private boolean isSingleQuote = false;
	
	/**
	 * 是否将json串以漂亮的格式输出
	 */
	private boolean isPrettyFormat = false;
	
	/**
	 * javabean属性过滤(暂不支持Map等的过滤)
	 */
	private String[] propertyFilters = {};
	
	/**
	 * 设置键和值的引号类型. 
	 * @param isSingleQuote	---- true为单引号, false为双引号, 默认false
	 */
	public void setSingleQuote(boolean isSingleQuote) {
		this.isSingleQuote = isSingleQuote;
		constantManager.setQuote(Constants.DOUBLE_QUOTE);
		if(isSingleQuote){
			constantManager.setQuote(Constants.SINGLE_QUOTE);
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
		this.setPrettyFormat(isPrettyFormat, 4);
	}
	
	/**
	 * 设置json的输出格式, 以及格式空格的数量(默认空格数量为4)
	 * @param isPrettyFormat ---- true为漂亮输出, false为一行字符串输出, 默认false
	 * @param blankCount ---- 小于0为制表符 \t, 否则为空格的数量
	 */
	public void setPrettyFormat(boolean isPrettyFormat, int blankCount) {
		this.isPrettyFormat = isPrettyFormat;
		StringBuilder symbol = new StringBuilder("");
		if(isPrettyFormat){
			symbol.append(Constants.ENTER);
			// 小于0为制表符
			if(blankCount < 0){
				blankCount = -blankCount;
				constantManager.setTabSymbol(true);
				symbol.append(Constants.TAB);
			}else{
				constantManager.setTabSymbol(false);
				for (int i = 0; i < blankCount; i++) {
					symbol.append(Constants.BLANK);
				}
			}
		}
		constantManager.setPrettySymbol(symbol.toString());
		constantManager.setPrettyBlankCount(blankCount);
	}
	
	public boolean isPrettyFormat() {
		return isPrettyFormat;
	}
	
	/**
	 * 设置javabean的属性过滤(暂不支持Map等的过滤)
	 * @param propertyFilters
	 */
	public void setPropertyFilters(String[] propertyFilters) {
		this.propertyFilters = propertyFilters;
	}
	
	public String[] getPropertyFilters() {
		return propertyFilters;
	}
}

package org.icemimosa.xjson.utils;

public class ConstantManager {

	/**
	 * 引号引用，单引号还是双引号
	 */
	private String quote = Constants.DOUBLE_QUOTE;

	/**
	 * 漂亮格式化输出的符号
	 */
	private String prettySymbol = "";
	private int prettyBlankCount; // 空白符数记录
	private boolean isTabSymbol;  // 是否是制表符
	
	/**
	 * 获取回车符
	 */
	private String enterSymbol = Constants.ENTER;

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public String getPrettySymbol() {
		return prettySymbol;
	}

	public void setPrettySymbol(String prettySymbol) {
		this.prettySymbol = prettySymbol;
	}

	public String getEnterSymbol() {
		return enterSymbol;
	}
	
	public int getPrettyBlankCount() {
		return prettyBlankCount;
	}

	public void setPrettyBlankCount(int prettyBlankCount) {
		this.prettyBlankCount = prettyBlankCount;
	}
	
	public boolean isTabSymbol() {
		return isTabSymbol;
	}

	public void setTabSymbol(boolean isTabSymbol) {
		this.isTabSymbol = isTabSymbol;
	}

	/**
	 * 增加或减少对应数量的空白符, 或增加或减少1个制表符
	 * @param blankCount 增加或减少的数量
	 */
	public void addBlankSymbol(int blankCount) {
		StringBuilder sb = new StringBuilder(this.prettySymbol);
		// 如果是制表符
		if(this.isTabSymbol){
			if(blankCount >= 0){
				sb.append(Constants.TAB);
			}else{
				blankCount = Math.abs(blankCount);
				if(blankCount >= this.prettySymbol.length() - Constants.ENTER.length()){
					sb = new StringBuilder(Constants.ENTER);
				}else{
					sb.deleteCharAt(this.prettySymbol.length() - 1);
				}
			}
		}
		// 如果是空白符
		else{
			if(blankCount >= 0){
				for (int i = 0; i < blankCount; i++) {
					sb.append(Constants.BLANK);
				}
			}
			// 减去空白符
			else{
				blankCount = Math.abs(blankCount);
				if(blankCount >= this.prettySymbol.length() - Constants.ENTER.length()){
					sb = new StringBuilder(Constants.ENTER);
				}else{
					for (int i = 0; i < blankCount; i++) {
						sb.deleteCharAt(this.prettySymbol.length() - i - 1);
					}
				}
			}
		}
		this.prettySymbol = sb.toString();
	}
}

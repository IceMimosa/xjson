package org.icemimosa.xjson.parser;

public class PrimitiveParser extends AbstractJSONParser {

	public PrimitiveParser(Object obj) {
		super(obj);
	}

	@Override
	public String toJsonString() {
		String retValue = null;
		if(obj == null){
			retValue = null;
		}else if(obj instanceof String){
			retValue = "\"" + obj.toString() + "\"";
		}else if(obj.getClass() == char.class || obj instanceof Character){
			retValue = "\"" + obj.toString() + "\"";
		}else{
			retValue = obj.toString();
		}
		return retValue;
	}
}

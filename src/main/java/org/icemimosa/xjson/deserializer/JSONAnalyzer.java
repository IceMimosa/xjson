package org.icemimosa.xjson.deserializer;

public interface JSONAnalyzer {

	public final static int OTHER = -1;
	public final static int UNKNOW = 0;
	public final static int OBJECT = 1;
	public final static int VALUE = 2;
	public final static int ARRAY = 3;
	public final static int END = 4;

	public int next();

	public boolean hasNext();

	public int nextToken();
	
	
	// key
	public void scanKey();
	public Object getKey();
	

	public void scanValue();
	public Object getValue();

	public int getCurrValueState();

}

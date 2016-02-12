package org.icemimosa.xjson.deserializer;

public interface JSONAnalyzer {

	
	public final static int UNKNOW = 0;
	public final static int OGJECT = 1;
	public final static int VALUE = 2;
	public final static int ARRAY = 3;
	public final static int END = 4;

	public int next();

	public boolean hasNext();

	public int nextToken();

	
}

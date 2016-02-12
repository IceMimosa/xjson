package org.icemimosa.xjson;

public class TestPrimitive {

	public static void main(String[] args) {

		Person p = new Person();
		p.setName("mimosa");
		p.setByteValue((byte) 1);
		p.setShortValue((short) 2);
		p.setAge(22);
		p.setLongValue(100);
		p.setFloatValue(4.23f);
		p.setDoubleValue(5.234);
		p.setCharValue('a');
		p.setBooleanValue(true);

		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setPrettyFormat(true, 4);
		jsonConfig.setPropertyFilters(new String[]{"isOk","booleanValue","n"});
		System.out.println(JSON.toJsonString(p, jsonConfig));
	}

}

class Person {

	private byte byteValue;
	private short shortValue;
	private int age;
	private long longValue;
	private float floatValue;
	private double doubleValue;
	private char charValue;
	private boolean booleanValue;

	private String name;
	private String n;
	private boolean isOk;

	
	public String getN() {
		return n;
	}

	public void setN(String n) {
		this.n = n;
	}

	public byte getByteValue() {
		return byteValue;
	}

	public void setByteValue(byte byteValue) {
		this.byteValue = byteValue;
	}

	public short getShortValue() {
		return shortValue;
	}

	public void setShortValue(short shortValue) {
		this.shortValue = shortValue;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public long getLongValue() {
		return longValue;
	}

	public void setLongValue(long longValue) {
		this.longValue = longValue;
	}

	public float getFloatValue() {
		return floatValue;
	}

	public void setFloatValue(float floatValue) {
		this.floatValue = floatValue;
	}

	public double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public char getCharValue() {
		return charValue;
	}

	public void setCharValue(char charValue) {
		this.charValue = charValue;
	}

	public boolean isBooleanValue() {
		return booleanValue;
	}

	public void setBooleanValue(boolean booleanValue) {
		this.booleanValue = booleanValue;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isOk() {
		return isOk;
	}

	public void setOk(boolean isOk) {
		this.isOk = isOk;
	}

}
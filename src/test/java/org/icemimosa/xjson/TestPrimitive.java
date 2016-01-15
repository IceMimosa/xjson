package org.icemimosa.xjson;

import org.icemimosa.xjson.utils.TypeUtils;

public class TestPrimitive {

	public static void main(String[] args) {

		Person p = new Person();
		p.setName("mimosa");
		p.setAge(22);
		System.out.println(JSON.getString(p));
		int i = 1;
		//System.out.println(TypeUtils.isPrimitive(p));
	}

}

class Person {

	private String name;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
package org.icemimosa.xjson;

import org.icemimosa.xjson.utils.TypeUtils;

public class TestPrimitive {

	public static void main(String[] args) {

		Person p = new Person();
		Person p1 = new Person();
		p.setName("mimosa");
		p.setAge(22);
		p.setP(p1);
		
		int i = 1;
		System.out.println(JSON.getString(p));
		
		//System.out.println(TypeUtils.isPrimitive(i));
	}

}

class Person {

	private String name;
	private int age;
	private Person p;

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

	public Person getP() {
		return p;
	}

	public void setP(Person p) {
		this.p = p;
	}
}
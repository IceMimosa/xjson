package org.icemimosa.xjson;

import java.util.ArrayList;
import java.util.List;

public class TestCollection {
	public static void main(String[] args) {
		List list = new ArrayList();
		CollectionTest p = new CollectionTest();
		CollectionTest p1 = new CollectionTest();
		CollectionTest p2 = new CollectionTest();
		Person person = new Person();
		list.add(p);
		list.add(p1);
		list.add(p2);
		
		Object[] objs1 = {p,p1,p2};
		Object[][] objs2 = {{p},{p,p1},{p2}};
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setPrettyFormat(true);
		System.out.println(JSON.toJsonString(list,jsonConfig));
		//System.out.println("---------------------------------");
		//System.out.println(JSON.toJsonString(objs1));
		//System.out.println("---------------------------------");
		//System.out.println(JSON.toJsonString(objs2,jsonConfig));
	}
}

class CollectionTest {
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

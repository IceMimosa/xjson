package org.icemimosa.xjson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMap {
	public static void main(String[] args) {
		
		Map map = new HashMap();
		List list = new ArrayList();
		
		MapTest p1 = new MapTest();
		MapTest p2 = new MapTest();
		list.add(p1);
		list.add(p2);
		
		map.put("a", list);
		map.put("b", "b");
		map.put("c", 1);
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setPrettyFormat(true);
		System.out.println(JSON.toJsonString(map,jsonConfig));
	}
}

class MapTest{
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
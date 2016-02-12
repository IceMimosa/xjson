package org.icemimosa.xjson;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ChenKai[514793425@qq.com]
 */
public class JsonObject {

	private final Map<String, Object> map;

	public JsonObject() {
		this(false);
	}

	public JsonObject(Map<String, Object> map) {
		this.map = map;
	}

	public JsonObject(boolean ordered) {
		if (ordered) {
			this.map = new LinkedHashMap<String, Object>();
		} else {
			this.map = new HashMap<String, Object>();
		}
	}

}

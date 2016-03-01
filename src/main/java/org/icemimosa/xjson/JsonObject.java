package org.icemimosa.xjson;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * JsonObject类
 * @author ChenKai[514793425@qq.com]
 */
public class JsonObject implements Map<String, Object>, Serializable {

	private static final long serialVersionUID = -1671008967652901785L;
	
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

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Object get(Object key) {
		return map.get(key);
	}

	@Override
	public Object put(String key, Object value) {
		return map.put(key, value);
	}

	@Override
	public Object remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void putAll(Map<? extends String, ? extends Object> m) {
		map.putAll(m);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Collection<Object> values() {
		return map.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return map.entrySet();
	}

	@Override
	public String toString() {
		return map.toString();
	}
}

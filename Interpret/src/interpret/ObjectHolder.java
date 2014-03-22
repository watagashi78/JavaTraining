package interpret;

import java.util.HashMap;

public class ObjectHolder {
	private HashMap<String, Object> objMap = new HashMap<String, Object>();
	private int count = 1;

	public String addObject(Object obj) {
		String key  = count++ + "." + obj.getClass().getSimpleName();
		objMap.put(key, obj);
		return key;
	}

	public Object getObject(String key) {
		return objMap.get(key);
	}
}
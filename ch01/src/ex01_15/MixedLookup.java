package ex01_15;

import java.util.ArrayList;

public class MixedLookup implements ChangedLookup {
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();

	public MixedLookup() {

	}

	public Object find(String name) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(name))
				return values.get(i);
		}
		return null;
	}

	public void add(String name, Object value) {
		names.add(name);
		values.add(value);
	}

	public void remove(String name) {
		values.remove(names.indexOf(name));
		names.remove(name);
	}
}

package ex01_15;

import java.util.ArrayList;

public class MixedLookup implements ChangedLookup {
	private ArrayList<String> names = new ArrayList<String>();
	private ArrayList<Object> values = new ArrayList<Object>();

	/**
	 * デフォルトコンストラクタ.
	 */
	public MixedLookup() {}

	/**
	 * 引数ありコンストラクタ.
	 * @param name
	 * @param value
	 */
	public MixedLookup(String name, Object value) {
		names.add(name);
		values.add(value);
	}

	/**
	 * リストの中にnameがあればそのnameのvalueを返し、
	 * なければnullを返すメソッド.
	 */
	public Object find(String name) {
		for (int i = 0; i < names.size(); i++) {
			if (names.get(i).equals(name))
				return values.get(i);
		}
		return null;
	}

	/**
	 * nameとvalueのセットを加えるメソッド.
	 */
	public void add(String name, Object value) {
		names.add(name);
		values.add(value);
	}

	/**
	 * namesリストの中でnamesに一致するvaluestとnameを削除するメソッド.
	 */
	public void remove(String name) {
		if (this.find(name) == null) {
			System.out.println(name + "は存在しません");
		} else {
			values.remove(names.indexOf(name));
			names.remove(name);
		}
	}
}

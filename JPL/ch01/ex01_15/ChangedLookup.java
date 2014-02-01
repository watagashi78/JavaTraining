package ex01_15;

interface ChangedLookup extends Lookup {
	/**
	 * Lookupを拡張し,addとremoveメソッドを宣言したインタフェース.
	 * @param name
	 */
	void add(String name, Object value);
	void remove(String name);
}

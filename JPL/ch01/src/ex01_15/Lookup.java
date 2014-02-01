package ex01_15;

interface Lookup {
	/**
	 * nameと関連付けされた値を返す
	 * そのような値がなければnullを返す.
	 */
	Object find(String name);
}

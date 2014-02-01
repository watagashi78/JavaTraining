package ex04_01;

interface EnergySource {

	/**
	 * 燃料残量が空かどうかを返すメソッド.
	 * @return 空ならtrue
	 */
	boolean empty();

	/**
	 * 燃料/バッテリーを補充するメソッド.
	 * @param per 何パーセントまで補充するかを引数でとる
	 */
	void charge(double per);
}

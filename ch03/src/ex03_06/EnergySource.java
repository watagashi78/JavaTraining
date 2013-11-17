package ex03_06;

abstract class EnergySource {

	/**
	 * 燃料残量が空かどうかを返すメソッド.
	 * @return 空ならtrue
	 */
	abstract boolean empty();

	/**
	 * 燃料/バッテリーを補充するメソッド.
	 * @param per 何パーセントまで補充するかを引数でとる
	 */
	abstract void charge(double per);
}

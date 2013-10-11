package ex01_14;

public class Walkman2nd extends Walkman {

	private String terminal2 = "Terminal2";

	/**
	 * 音楽を二曲聴けるように,
	 * スーパークラスのlistenメソッドをオーバーライド.
	 * @param trac 1曲
	 * @param trac2 2曲
	 */
	public void listen(Music trac, Music trac2) {
		System.out.print("Use " + getTerminal() + "1 : ");
		trac.startMusic();
		System.out.print("Use " + terminal2 + " : ");
		trac2.startMusic();
	}
}

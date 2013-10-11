package ex01_14;

public class Walkman2nd extends Walkman {

	private String terminal2 = "Terminal2";

	/**
	 * 音楽を二端子から聴けるように,
	 * スーパークラスのlistenメソッドをオーバーライド.
	 * @param trac 1曲
	 */
	public void listen(Music trac) {
		System.out.println("Use " + getTerminal() + "1 and " + terminal2 + ".");
		trac.startMusic();
	}
}

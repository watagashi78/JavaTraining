package ex01_14;

public class Walkman {

	private String terminal = "Terminal";

	/**
	 * ウォークマンのベースモデル
	 * 端子は一つで一人がテープを聴くことが出来る.
	 * @param args
	 */
	public static void main(String[] args) {
		Music trac1 = new Music("QUEEN", "Don't Stop Me Now.");
		//Music trac2 = new Music();

		Walkman wm1 = new Walkman();
		System.out.println("##### Walkman Base Model ######");
		wm1.listen(trac1);

		Walkman2nd wm2 = new Walkman2nd();
		System.out.println("##### Walkman 2nd Model ######");
		wm2.listen(trac1);

		Walkman3rd wm3 = new Walkman3rd();
		System.out.println("##### Walkman 3rd Model ######");
		wm3.listen(trac1);
		wm3.talk();
	}

	/**
	 * 音楽の再生.
	 * @param trac 曲
	 */
	public void listen(Music trac) {
		System.out.println("Use " + terminal + ".");
		trac.startMusic();
	}

	/**
	 * 端子のゲッター.
	 * @return 端子名
	 */
	public String getTerminal() {
		return terminal;
	}

}
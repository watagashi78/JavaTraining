package src;

import javax.swing.JFrame;

/**
 * AWTのJFrameを使用して、時間を表示するデジタル時計を作成せよ.
 *  ・java.swing.JFrameを使用する
 *  ・Windowsの普通のアプリケーションと同様にタイトルバーの「×」ボタンをクリックすると終了する
 *  ・デジタル時計の描画はpaintComponentメソッド内でGraphicsを使用して行う
 */
public class SwingClock1 extends JFrame {
	private final ClockPanel panel;
	private ClockSettings settings;

	public static void main(String[] args) {
		SwingClock1 clock = new SwingClock1("DigitalClock Swing");
		clock.setVisible(true);
	}

	SwingClock1(String title) {
		this.setTitle(title);
		this.settings = new ClockSettings();
		this.panel = new ClockPanel(settings);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().setPreferredSize(panel.getSize());
		pack();
	}
}

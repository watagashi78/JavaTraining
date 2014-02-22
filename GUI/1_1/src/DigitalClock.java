package src;

import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClock extends Frame implements Runnable {
	private static int hour, min, sec;
	private Thread thread = new Thread(this);

	/**
	 * AWTのFrameを使用して、時間を表示するデジタル時計を作成せよ.
	 *  ・java.awt.Frameを使用する
	 *  ・Windowsの普通のアプリケーションと同様にタイトルバーの「×」ボタンをクリックすると終了する
	 *  ・デジタル時計の描画はpaintメソッド内でGraphicsを使用して行う
	 */
	public static void main(String[] args) {
		DigitalClock clock = new DigitalClock("DigitalClock");
		clock.setSize(600, 200);
		clock.setVisible(true);
	}

	DigitalClock(String title) {
		this.setTitle(title);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				System.exit(0);
			}
		});
		thread.start();
	}

	public void run() {
		while(true) {
			hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			min = Calendar.getInstance().get(Calendar.MINUTE);
			sec = Calendar.getInstance().get(Calendar.SECOND);
			repaint();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				;
			}
		}
	}

	public void paint(Graphics g) {
		Font font = new Font("TimesRoman",Font.BOLD,48);
		g.setFont(font);
		g.drawString(hour + ":" + min + ":" + sec, 220, 120);
	}
}

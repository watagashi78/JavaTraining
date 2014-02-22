package src;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Calendar;

public class DigitalClock2 extends Frame implements Runnable, ActionListener {
	private static int hour, min, sec;
	private Thread thread = new Thread(this);
	private PropertyDialog dlg;
	private boolean propFlag = false;

	private Font tmpFont = new Font("7barP", Font.BOLD, 50);

	private Dimension size;
	private Image imgBuffer;
	private Graphics buffer;

	private final static int MARGIN = 50;

	/**
	 * デジタル時計に次の機能追加を行ってください。
	 * ・メニューをつけてプロパティダイアログを開ける
	 * ・プロパティダイアログでは、以下の項目を設定できる
	 *    1. フォントの指定
	 *    2. フォントサイズの指定
	 *    3. 文字色の指定
	 *    4. 時計の背景色の指定
	 * ・描画に際して、ちらつきをなくすようにダブルバッファリングする
	 * ・フォントとフォントサイズを変更すると、時計を表示すべきフレームの大きさを適切に自動変更して、正しく表示されるようにする
	 */
	public static void main(String[] args) {
		DigitalClock2 clock = new DigitalClock2("DigitalClock");
		clock.setSize(600, 200);
		clock.setVisible(true);
	}

	DigitalClock2(String title) {
		this.setTitle(title);
		this.setLayout(new FlowLayout());
		this.setResizable(false);
		MenuBar menuBar = new MenuBar();
		this.setMenuBar(menuBar);

		// [File]
		Menu menuFile = new Menu("File");
		menuFile.addActionListener(this);
		menuBar.add(menuFile);

		// [File] - [プロパティ]
		MenuItem menuProperty = new MenuItem("プロパティ");
		menuFile.add(menuProperty);

		// [File] - [終了]
		MenuItem menuExit = new MenuItem("終了");
		menuFile.add(menuExit);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				System.exit(0);
			}
		});

		thread.start();
	}

	public void run() {
		while (true) {
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
		size = this.getSize();
		int width = size.width;
		int height = size.height;
		int strWidth, strHeight = 0;
		String time = getStringTime(hour, min, sec);
		imgBuffer = createImage(width, height);
		buffer = imgBuffer.getGraphics();

		if (propFlag) {
			if (dlg.isFlag()) {
				buffer.setFont(dlg.getFont());
				buffer.setColor(dlg.getTextcolor());
				this.setBackground(dlg.getBackground());

				tmpFont = dlg.getFont();
			} else {
				buffer.setFont(tmpFont);
			}
		} else {
			buffer.setFont(tmpFont);
		}

		// フォントサイズによるウィンドウの中央の計算
		FontMetrics fm = buffer.getFontMetrics();
		strWidth = fm.stringWidth(time);
		strHeight = fm.getHeight();

		this.setSize(strWidth + MARGIN * 2, strHeight + MARGIN * 2);

		buffer.drawString(time, (width - strWidth) / 2 , (height + strHeight) / 2 + 15);
		g.drawImage(imgBuffer, 0, 0, this);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "プロパティ":
			dlg = new PropertyDialog(this);
			dlg.show();
			propFlag = true;
			break;
		case "終了":
			System.exit(0);
			break;
		default:
			break;
		}
	}

	public void update(Graphics g) {
		paint(g);
	}

	public String getStringTime(int h, int m, int s) {
		String time = "";
		if (h < 10) {
			time += "0";
		}
		time += Integer.toString(h) + ":";

		if (m < 10) {
			time += "0";
		}
		time += Integer.toString(m) + ":";

		if (s < 10) {
			time += "0";
		}
		time += Integer.toString(s);

		return time;
	}
}

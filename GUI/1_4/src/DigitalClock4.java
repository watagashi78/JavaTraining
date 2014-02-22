package src;

import java.awt.Color;
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
import java.util.prefs.Preferences;

public class DigitalClock4 extends Frame implements Runnable, ActionListener {
	private static int hour, min, sec;
	private Thread thread = new Thread(this);
	private PropertyDialog2 dlg;
	private boolean propFlag = false;
	private Preferences prefs;

	private Color txc, bgc;
	private int windowx, windowy;
	private Font tmpFont;

	private Dimension size;
	private Image imgBuffer;
	private Graphics buffer;

	private final static int MARGIN = 30;

	/**
	 * デジタル時計に次の機能追加を行ってください。
	 * ・レイアウトマネージャはGridBagLayoutを使用する
	 * ・プロパティダイアログは属性名＋リストメニューが縦に並ぶようにする
	 *   ・属性名のラベルは右寄せにして、値の選択リストは左寄せにする
	 * ・ダイアログの下にはOK、キャンセルボタンを右下に寄せて表示し、それぞれのボタンを実装する
	 * ・java.util.prefsパッケージを使用してプロパティダイアログの内容の保存と時計の終了時の位置を保存する
	 */
	public static void main(String[] args) {
		DigitalClock4 clock = new DigitalClock4("DigitalClock");
		clock.setVisible(true);
	}

	DigitalClock4(String title) {
		this.setTitle(title);
		this.setSize(600, 200);
		this.setLayout(new FlowLayout());
		getPreference();
		this.setLocation(windowx, windowy);
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
				save();
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

		if (propFlag && dlg.isFlag()) {
			getPreference();
		}
		buffer.setFont(tmpFont);
		buffer.setColor(txc);
		this.setBackground(bgc);

		// フォントサイズによるウィンドウの中央の計算
		FontMetrics fm = buffer.getFontMetrics();
		strWidth = fm.stringWidth(time);
		strHeight = fm.getAscent();

		this.setSize(strWidth + MARGIN * 2, strHeight + MARGIN * 2);

		buffer.drawString(time, (width - strWidth) / 2, strHeight + MARGIN);
		g.drawImage(imgBuffer, 0, 0, this);
	}

	@SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "プロパティ":
			save();
			dlg = new PropertyDialog2(this);
			dlg.show();
			propFlag = true;
			break;
		case "終了":
			save();
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

	public void save() {
		prefs.putInt("window_x", this.getLocation().x);
		prefs.putInt("window_y", this.getLocation().y);
	}

	public void getPreference() {
		prefs = Preferences.userRoot().node("hasegawaDigitalclock");
		windowx = prefs.getInt("window_x", 0);
		windowy = prefs.getInt("window_y", 0);

		switch (prefs.get("font", "DialogInput")) {
		case "Serif":
			tmpFont = new Font(Font.SERIF, Font.BOLD, prefs.getInt("font-size", 100));
			break;
		case "SansSerif":
			tmpFont = new Font(Font.SANS_SERIF, Font.BOLD, prefs.getInt("font-size", 100));
			break;
		case "Monospaced":
			tmpFont = new Font(Font.MONOSPACED, Font.BOLD, prefs.getInt("font-size", 100));
			break;
		case "Dialog":
			tmpFont = new Font(Font.DIALOG, Font.BOLD, prefs.getInt("font-size", 100));
			break;
		case "DialogInput":
			tmpFont = new Font(Font.DIALOG_INPUT, Font.BOLD, prefs.getInt("font-size", 100));
			break;
		default:
			break;
		}

		switch (prefs.get("text-color", "White")) {
		case "Black":
			txc = Color.BLACK;
			break;
		case "White":
			txc = Color.WHITE;
			break;
		case "Red":
			txc = Color.RED;
			break;
		case "Green":
			txc = Color.GREEN;
			break;
		case "Blue":
			txc = Color.BLUE;
			break;
		default:
			break;
		}
		switch (prefs.get("background-color", "BLUE")) {
		case "Black":
			bgc = Color.BLACK;
			break;
		case "White":
			bgc = Color.WHITE;
			break;
		case "Red":
			bgc = Color.RED;
			break;
		case "Green":
			bgc = Color.GREEN;
			break;
		case "Blue":
			bgc = Color.BLUE;
			break;
		default:
			break;
		}
	}
}
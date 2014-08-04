package src;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
public class SwingClock4 extends JFrame {
	private final ClockPanel4 panel;
	private ClockSettings4 settings;
	private SwingClock4 clock = this;
	private Preferences prefs;

	public static void main(String[] args) {
		SwingClock4 clock = new SwingClock4("DigitalClock Swing4");
		clock.setVisible(true);
	}

	SwingClock4(String title) {
		this.setTitle(title);
		this.settings = new ClockSettings4();
		this.panel = new ClockPanel4(this);
		this.setResizable(false);
		addWindowListener(new ClosingWindowListener());
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().setPreferredSize(panel.getSize());
		setupMenuBar();
		pack();
		setInitWindowLocation();
	}

	public ClockSettings4 getClockSettings() {
		return settings;
	}

	public void setClockSettings(ClockSettings4 newSettings) {
		this.settings = newSettings;
		this.panel.setClockSettings(newSettings);
		getContentPane().setPreferredSize(panel.getSize());
		pack();
	}

	public int getWindowX() {
		return this.getLocation().x;
	}

	public int getWindowY() {
		return this.getLocation().y;
	}

	private void setupMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem property = new JMenuItem("プロパティ");
		JMenuItem exit = new JMenuItem("終了");
		property.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingPropertyDialog2 dialog = new SwingPropertyDialog2(clock);
				dialog.setVisible(true);
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveWindowLocation();
				System.exit(0);
			}
		});
		file.add(property);
		file.add(exit);
		bar.add(file);
		setJMenuBar(bar);
	}

	private class ClosingWindowListener extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
			saveWindowLocation();
			System.exit(0);
		}
	}

	private void setInitWindowLocation() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screenSize = toolkit.getScreenSize();

		prefs = Preferences.userRoot().node("hasegawaSwingclock");
		int main_window_x = prefs.getInt("main_window_x", (screenSize.width - this.getWidth()) / 2);
		int main_window_y = prefs.getInt("main_window_y", (screenSize.height - this.getHeight()) / 2);

		this.setLocation(main_window_x, main_window_y);
	}

	private void saveWindowLocation() {
		settings.getPreferences().putInt("main_window_x", this.getLocation().x);
		settings.getPreferences().putInt("main_window_y", this.getLocation().y);
	}
}

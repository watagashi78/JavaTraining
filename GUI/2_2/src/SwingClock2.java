package src;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
public class SwingClock2 extends JFrame {
	private final ClockPanel2 panel;
	private ClockSettings2 settings;
	private SwingClock2 clock = this;

	public static void main(String[] args) {
		SwingClock2 clock = new SwingClock2("DigitalClock Swing2");
		clock.setVisible(true);
	}

	SwingClock2(String title) {
		this.setTitle(title);
		this.settings = new ClockSettings2();
		this.panel = new ClockPanel2(this);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().setPreferredSize(panel.getSize());
		setupMenuBar();
		pack();
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
	    final Dimension screenSize = toolkit.getScreenSize();
	    final int x = (screenSize.width - this.getWidth()) / 2;
	    final int y = (screenSize.height - this.getHeight()) / 2;
	    this.setLocation(x, y);
	}

	public ClockSettings2 getClockSettings() {
		return settings;
	}

	public void setClockSettings(ClockSettings2 newSettings) {
		this.settings = newSettings;
		this.panel.setClockSettings(newSettings);
		getContentPane().setPreferredSize(panel.getSize());
		pack();
	}

	private void setupMenuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenuItem property = new JMenuItem("プロパティ");
		JMenuItem exit = new JMenuItem("終了");
		property.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				SwingPropertyDialog dialog = new SwingPropertyDialog(clock);
				dialog.setVisible(true);
			}
		});
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(property);
		file.add(exit);
		bar.add(file);
		setJMenuBar(bar);
	}
}

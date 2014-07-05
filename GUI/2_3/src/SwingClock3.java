package src;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JWindow;

/**
 * 課題2-2のデジタル時計を次のように修正してください。
 * ・FrameではなくWindowクラスを使用して、フレーム枠のないデジタル時計にする
 * ・マウスの右クリックでポップアップしてカスケード形式でプロパティを変更できるようにする
 * ・時計内をマウスの左ボタンでクリックしたまま、デスクトップ上でウィンドウを移動できるようにする
 */
public class SwingClock3 extends JWindow implements MouseListener, MouseMotionListener {
	private final ClockPanel3 panel;
	private ClockSettings3 settings;
	private SwingClock3 clock = this;
	private Point dragPoint, position;
	private CascadeMenu3 menu;

	public static void main(String[] args) {
		SwingClock3 clock = new SwingClock3();
		clock.setVisible(true);
	}

	SwingClock3() {
		this.settings = new ClockSettings3();
		this.panel = new ClockPanel3(this);
		getContentPane().setLayout(null);
		getContentPane().add(panel);
		getContentPane().setPreferredSize(panel.getSize());
		menu = new CascadeMenu3(this);
		this.add(menu);
		addMouseListener(this);
		addMouseMotionListener(this);
		pack();
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
	    final Dimension screenSize = toolkit.getScreenSize();
	    final int x = (screenSize.width - this.getWidth()) / 2;
	    final int y = (screenSize.height - this.getHeight()) / 2;
	    this.setLocation(x, y);
	}

	public ClockSettings3 getClockSettings() {
		return settings;
	}

	public void setClockSettings(ClockSettings3 newSettings) {
		this.settings = newSettings;
		this.panel.setClockSettings(newSettings);
		getContentPane().setPreferredSize(panel.getSize());
		pack();
	}

	Point getScreenLocation(MouseEvent e) {
		Point p1 = e.getPoint();
		Point p2 = this.getLocationOnScreen();
		return new Point(p1.x + p2.x, p1.y + p2.y);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Point cursor = getScreenLocation(e);
		int xdiff = cursor.x - dragPoint.x;
		int ydiff = cursor.y - dragPoint.y;
		this.setLocation(position.x + xdiff, position.y + ydiff);
	}
	@Override
	public void mouseMoved(MouseEvent e) {}
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {
		dragPoint = getScreenLocation(e);
		position = this.getLocation();
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
			menu.show(this, e.getX(), e.getY());
		}
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}

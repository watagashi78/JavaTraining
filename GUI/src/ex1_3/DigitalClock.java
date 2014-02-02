package ex1_3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Point;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Calendar;

public class DigitalClock extends Window implements Runnable, ActionListener, MouseListener, MouseMotionListener{

	public DigitalClock(Frame paramFrame) {
		super(paramFrame);
	}

	private static final int MARGIN = 50;
	private static int hour, min, sec;

	private Font tmpFont = new Font(Font.MONOSPACED, Font.BOLD, 50);
	private Thread thread = new Thread(this);
	private Dimension size;
	private Image imgBuffer;
	private Graphics buffer;

	private Point dragPoint, position;

	private CascadeMenu menu = new CascadeMenu();
	private boolean clickFlag = false;

	/**
	 * 課題1-2のデジタル時計次のように修正してください。
	 * ・FrameではなくWindowクラスを使用して、フレーム枠のないデジタル時計にする
	 * ・マウスの右クリックでポップアップしてカスケード形式でプロパティを変更できるようにする
	 * ・時計内をマウスの左ボタンでクリックしたまま、デスクトップ上でウィンドウを移動できるようにする
	 */
	public static void main(String[] args) {
		DigitalClock clock = new DigitalClock(new Frame());
		clock.init();
	}

	public void init() {
		this.setSize(200, 200);
		this.setLocation(200, 200);
		this.setFont(tmpFont);
		this.setBackground(Color.BLUE);
		this.setVisible(true);

		this.add(menu);
		addMouseListener(this);
		addMouseMotionListener(this);
		thread.start();
	}

	public void run() {
		while (true) {
			hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			min = Calendar.getInstance().get(Calendar.MINUTE);
			sec = Calendar.getInstance().get(Calendar.SECOND);

			this.repaint();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.exit(0);
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

		if (clickFlag) {
			buffer.setFont(menu.getFont());
			
		}
		
		buffer.setFont(tmpFont);
		buffer.setColor(Color.WHITE);

		// フォントサイズによるウィンドウの中央の計算
		FontMetrics fm = buffer.getFontMetrics();
		strWidth = fm.stringWidth(time);
		strHeight = fm.getHeight();

		this.setSize(strWidth + MARGIN * 2, strHeight + MARGIN * 2);

		buffer.drawString(time, (width - strWidth) / 2 , (height - strHeight) / 2 + MARGIN);
		g.drawImage(imgBuffer, 0, 0, this);

	}

	public void update(Graphics g) {
		this.paint(g);
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void checkUsageFont() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fs = ge.getAvailableFontFamilyNames();
		for (String name : fs) {
			System.out.println(name);
		}
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
			clickFlag = true;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

}
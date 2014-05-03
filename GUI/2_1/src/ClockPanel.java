package src;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class ClockPanel extends JPanel {
	private static final int MARGIN = 30;
	private ClockSettings settings;

	public ClockPanel(ClockSettings cs) {
		setClockSettings(cs);
		Timer timer = new Timer();
		TimerTask countup = new TimerTask() {
			@Override
			public void run() { repaint(); }
		};
		timer.schedule(countup, 0, 1000);
	}

	public void setClockSettings(ClockSettings cs) {
		this.settings = cs;
		Dimension panelSize = getPanelDimension(settings.getFont());
		setSize(panelSize);
		setPreferredSize(panelSize);
	}

	@Override
	public void paintComponent(Graphics g) {
		String time = getStringTime();
		g.setColor(settings.getBackGround_color());
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setFont(settings.getFont());
		g.setColor(settings.getFont_color());
		Dimension td = getTimeDimension(settings.getFont(), time);
		g.drawString(time, (int)td.getWidth(), (int)td.getHeight());
	}

	private Dimension getTimeDimension(Font font, String str) {
		FontMetrics ft = this.getFontMetrics(font);
		int height = ft.getAscent();
		int width = (getWidth() - ft.stringWidth(str)) / 2;
		return new Dimension(width, height);
	}

	private Dimension getPanelDimension(Font font) {
		FontMetrics ft = this.getFontMetrics(font);
		int height = ft.getAscent() + 2 * MARGIN;
		int width = ft.stringWidth("00:00:00") + 2 * MARGIN;
		return new Dimension(width, height);
	}

	private String getStringTime() {
		Calendar now = Calendar.getInstance();
		int h = now.get(Calendar.HOUR_OF_DAY);
		int m = now.get(Calendar.MINUTE);
		int s = now.get(Calendar.SECOND);
		return String.format("%02d:%02d:%02d", h, m, s);
	}
}

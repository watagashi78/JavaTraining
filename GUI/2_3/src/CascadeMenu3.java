package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class CascadeMenu3 extends JPopupMenu implements ActionListener {
	private SwingClock3 clock;
	private ClockSettings3 currentSettings;
	private ClockSettings3 newSettings;
	private static final String[] FONTS = {
		Font.SANS_SERIF,
		Font.SERIF,
		Font.DIALOG,
		Font.DIALOG_INPUT,
		Font.MONOSPACED
	};
	private static final Color[] COLORS = {
			Color.WHITE,
			Color.LIGHT_GRAY,
			Color.GRAY,
			Color.DARK_GRAY,
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.GREEN,
			Color.YELLOW,
			Color.ORANGE,
			Color.PINK,
			Color.MAGENTA,
			Color.RED,
	};
	private static final String[] COLORS_STRING = {
		"White",
		"Light Gray",
		"Gray",
		"Dark Gray",
		"Black",
		"Blue",
		"Cyan",
		"Green",
		"Yellow",
		"Orange",
		"Pink",
		"Magenta",
		"Red"
};

	public CascadeMenu3(SwingClock3 clock) {
		this.clock = clock;
		this.currentSettings = clock.getClockSettings();
		this.newSettings = currentSettings.clone();
		setFontMenu();
		setFontSizeMenu();
		setFontColorMenu();
		setBackgroundColorMenu();
		JMenuItem exit = new JMenuItem("Exit");
		this.add(exit);
		exit.addActionListener(this);
	}

	private void setFontMenu() {
		JMenu fonts = new JMenu("Font");
		this.add(fonts);
		for(int i = 0; i < FONTS.length; i++) {
			JMenuItem item = new JMenuItem(FONTS[i]);
			item.addActionListener(new FontSelector());
			fonts.add(item);
		}
	}

	private void setFontSizeMenu() {
		JMenu fontSize = new JMenu("Font Size");
		this.add(fontSize);
		for(Integer i = 10; i <= 200; i += 10) {
			JMenuItem item = new JMenuItem(i.toString());
			item.addActionListener(new FontSizeSelector());
			fontSize.add(item);
		}
	}

	private void setFontColorMenu() {
		JMenu textColor = new JMenu("Text Color");
		this.add(textColor);
		for(int i = 0; i < COLORS.length; i++) {
			JMenuItem item = new JMenuItem(COLORS_STRING[i]);
			item.setBackground(COLORS[i]);
			item.addActionListener(new FontColorSelector());
			textColor.add(item);
		}
	}

	private void setBackgroundColorMenu() {
		JMenu background = new JMenu("Background Color");
		this.add(background);
		for(int i = 0; i < COLORS.length; i++) {
			JMenuItem item = new JMenuItem(COLORS_STRING[i]);
			item.setBackground(COLORS[i]);
			item.addActionListener(new BackgroundColorSelector());
			background.add(item);
		}
	}

	class FontSelector implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			newSettings.setFont(e.getActionCommand());
			clock.setClockSettings(newSettings);
		}
	}

	class FontSizeSelector implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			newSettings.setFont_size(Integer.parseInt(e.getActionCommand()));
			clock.setClockSettings(newSettings);
		}
	}

	class FontColorSelector implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < COLORS.length; i++) {
				if (e.getActionCommand().equals(COLORS_STRING[i]))
					newSettings.setFont_color(COLORS[i]);
			}
			clock.setClockSettings(newSettings);
		}
	}

	class BackgroundColorSelector implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < COLORS.length; i++) {
				if (e.getActionCommand().equals(COLORS_STRING[i]))
					newSettings.setBackGround_color(COLORS[i]);
			}
			clock.setClockSettings(newSettings);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Exit")
			System.exit(0);
	}
}

package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CascadeMenu extends PopupMenu implements ActionListener, MouseListener {
	private String font;
	private int size;
	private Color tc;
	private Color bc;

	private ChangeFlag flags;
	private boolean click = false;

	enum ChangeFlag {
		FONT,
		FONT_SIZE,
		TEXT_COLOR,
		BACKGROUND_COLOR
	}

	Menu fonts = new Menu("Font");
	Menu fontSize = new Menu("Font Size");
	Menu textColor = new Menu("Text Color");
	Menu background = new Menu("Background Color");

	public CascadeMenu() {
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		this.add(fonts);
		MenuItem font1 = new MenuItem("7-Segment");
		MenuItem font2 = new MenuItem("Serif");
		MenuItem font3 = new MenuItem("SansSerif");
		MenuItem font4 = new MenuItem("Monospaced");
		MenuItem font5 = new MenuItem("Dialog");
		fonts.add(font1);
		fonts.add(font2);
		fonts.add(font3);
		fonts.add(font4);
		fonts.add(font5);
		font1.addActionListener(this);
		font2.addActionListener(this);
		font3.addActionListener(this);
		font4.addActionListener(this);
		font5.addActionListener(this);

		this.add(fontSize);
		MenuItem size1 = new MenuItem("20");
		MenuItem size2 = new MenuItem("30");
		MenuItem size3 = new MenuItem("40");
		MenuItem size4 = new MenuItem("50");
		MenuItem size5 = new MenuItem("60");
		MenuItem size6 = new MenuItem("70");
		MenuItem size7 = new MenuItem("80");
		MenuItem size8 = new MenuItem("90");
		MenuItem size9 = new MenuItem("100");
		fontSize.add(size1);
		fontSize.add(size2);
		fontSize.add(size3);
		fontSize.add(size4);
		fontSize.add(size5);
		fontSize.add(size6);
		fontSize.add(size7);
		fontSize.add(size8);
		fontSize.add(size9);
		size1.addActionListener(this);
		size2.addActionListener(this);
		size3.addActionListener(this);
		size4.addActionListener(this);
		size5.addActionListener(this);
		size6.addActionListener(this);
		size7.addActionListener(this);
		size8.addActionListener(this);
		size9.addActionListener(this);

		this.add(textColor);
		MenuItem tcolor1 = new MenuItem("Black");
		MenuItem tcolor2 = new MenuItem("White");
		MenuItem tcolor3 = new MenuItem("Red");
		MenuItem tcolor4 = new MenuItem("Green");
		MenuItem tcolor5 = new MenuItem("Blue");
		textColor.add(tcolor1);
		textColor.add(tcolor2);
		textColor.add(tcolor3);
		textColor.add(tcolor4);
		textColor.add(tcolor5);
		tcolor1.addActionListener(this);
		tcolor2.addActionListener(this);
		tcolor3.addActionListener(this);
		tcolor4.addActionListener(this);
		tcolor5.addActionListener(this);

		this.add(background);
		MenuItem bc1 = new MenuItem("Black_BackGround");
		MenuItem bc2 = new MenuItem("White_BackGround");
		MenuItem bc3 = new MenuItem("Red_BackGround");
		MenuItem bc4 = new MenuItem("Green_BackGround");
		MenuItem bc5 = new MenuItem("Blue_BackGround");
		background.add(bc1);
		background.add(bc2);
		background.add(bc3);
		background.add(bc4);
		background.add(bc5);
		bc1.addActionListener(this);
		bc2.addActionListener(this);
		bc3.addActionListener(this);
		bc4.addActionListener(this);
		bc5.addActionListener(this);

		MenuItem exit = new MenuItem("Exit");
		this.add(exit);
		exit.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "7-Segment":
				font = "7barSP";
				flags = ChangeFlag.FONT;
				break;
			case "Serif":
				font = Font.SERIF;
				flags = ChangeFlag.FONT;
				break;
			case "SansSerif":
				font = Font.SANS_SERIF;
				flags = ChangeFlag.FONT;
				break;
			case "Monospaced":
				font = Font.MONOSPACED;
				flags = ChangeFlag.FONT;
				break;
			case "Dialog":
				font = Font.DIALOG;
				flags = ChangeFlag.FONT;
				break;
			default:
				break;
		}

		switch (e.getActionCommand()) {
			case "20":
				size = 20;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "30":
				size = 30;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "40":
				size = 40;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "50":
				size = 50;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "60":
				size = 60;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "70":
				size = 70;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "80":
				size = 80;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "90":
				size = 90;
				flags = ChangeFlag.FONT_SIZE;
				break;
			case "100":
				size = 100;
				flags = ChangeFlag.FONT_SIZE;
				break;
			default:
				break;
		}

		switch (e.getActionCommand()) {
			case "Black":
				tc = Color.BLACK;
				flags = ChangeFlag.TEXT_COLOR;
				break;
			case "White":
				tc = Color.WHITE;
				flags = ChangeFlag.TEXT_COLOR;
				break;
			case "Red":
				tc = Color.RED;
				flags = ChangeFlag.TEXT_COLOR;
				break;
			case "Green":
				tc = Color.GREEN;
				flags = ChangeFlag.TEXT_COLOR;
				break;
			case "Blue":
				tc = Color.BLUE;
				flags = ChangeFlag.TEXT_COLOR;
				break;
			default:
				break;
		}

		switch (e.getActionCommand()) {
			case "Black_BackGround":
				bc = Color.BLACK;
				flags = ChangeFlag.BACKGROUND_COLOR;
				break;
			case "White_BackGround":
				bc = Color.WHITE;
				flags = ChangeFlag.BACKGROUND_COLOR;
				break;
			case "Red_BackGround":
				bc = Color.RED;
				flags = ChangeFlag.BACKGROUND_COLOR;
				break;
			case "Green_BackGround":
				bc = Color.GREEN;
				flags = ChangeFlag.BACKGROUND_COLOR;
				break;
			case "Blue_BackGround":
				bc = Color.BLUE;
				flags = ChangeFlag.BACKGROUND_COLOR;
				break;
			default:
				break;
		}

		if (e.getActionCommand() == "Exit") System.exit(0);
	}

	public String getFontString() {
		return font;
	}
	public int getSize() {
		return size;
	}
	public Color getTc() {
		return tc;
	}
	public Color getBc() {
		return bc;
	}

	public ChangeFlag getFlags() {
		return flags;
	}

	public boolean getClick() {
		return click;
	}

	@Override
	public void mouseClicked(MouseEvent e) {click = true;}
	@Override
	public void mousePressed(MouseEvent e) {click = true;}
	@Override
	public void mouseReleased(MouseEvent e) {click = true;}
	@Override
	public void mouseEntered(MouseEvent e) {click = true;}
	@Override
	public void mouseExited(MouseEvent e) {click = true;}
}

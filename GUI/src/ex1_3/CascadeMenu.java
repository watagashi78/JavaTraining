package ex1_3;

import java.awt.CheckboxMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CascadeMenu extends PopupMenu implements ActionListener {
	Font font;
	int size;
	Color tc;
	Color bc;
	
	int flag;
	enum ChangeFlag {
		FONT,
		SIZE,
		T_COLOR,
		BG_COLOR
	}


	Menu fonts = new Menu("Font");
	Menu fontSize = new Menu("Font Size");
	Menu textColor = new Menu("Text Color");
	Menu background = new Menu("Background Color");

	public CascadeMenu() {
		this.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		this.add(fonts);
		MenuItem font1 = new MenuItem();
		MenuItem font2 = new MenuItem();
		MenuItem font3 = new MenuItem();
		MenuItem font4 = new MenuItem();
		MenuItem font5 = new MenuItem();
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
		CheckboxMenuItem size1 = new CheckboxMenuItem("20");
		CheckboxMenuItem size2 = new CheckboxMenuItem("30");
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
		MenuItem tcolor1 = new MenuItem();
		MenuItem tcolor2 = new MenuItem();
		MenuItem tcolor3 = new MenuItem();
		MenuItem tcolor4 = new MenuItem();
		MenuItem tcolor5 = new MenuItem();
		MenuItem tcolor6 = new MenuItem();
		MenuItem tcolor7 = new MenuItem();
		MenuItem tcolor8 = new MenuItem();
		MenuItem tcolor9 = new MenuItem();
		textColor.add(tcolor1);
		textColor.add(tcolor2);
		textColor.add(tcolor3);
		textColor.add(tcolor4);
		textColor.add(tcolor5);
		textColor.add(tcolor6);
		textColor.add(tcolor7);
		textColor.add(tcolor8);
		textColor.add(tcolor9);
		tcolor1.addActionListener(this);
		tcolor2.addActionListener(this);
		tcolor3.addActionListener(this);
		tcolor4.addActionListener(this);
		tcolor5.addActionListener(this);
		tcolor6.addActionListener(this);
		tcolor7.addActionListener(this);
		tcolor8.addActionListener(this);
		tcolor9.addActionListener(this);

		this.add(background);
		MenuItem bc1 = new MenuItem();
		MenuItem bc2 = new MenuItem();
		MenuItem bc3 = new MenuItem();
		MenuItem bc4 = new MenuItem();
		MenuItem bc5 = new MenuItem();
		MenuItem bc6 = new MenuItem();
		MenuItem bc7 = new MenuItem();
		MenuItem bc8 = new MenuItem();
		MenuItem bc9 = new MenuItem();
		background.add(bc1);
		background.add(bc2);
		background.add(bc3);
		background.add(bc4);
		background.add(bc5);
		background.add(bc6);
		background.add(bc7);
		background.add(bc8);
		background.add(bc9);
		bc1.addActionListener(this);
		bc2.addActionListener(this);
		bc3.addActionListener(this);
		bc4.addActionListener(this);
		bc5.addActionListener(this);
		bc6.addActionListener(this);
		bc7.addActionListener(this);
		bc8.addActionListener(this);
		bc9.addActionListener(this);

		MenuItem exit = new MenuItem("Exit");
		this.add(exit);
		exit.addActionListener(this);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		switch (fonts.getActionCommand()) {
			case "7-Segment":
				font = new Font("7barSP", 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			case "Serif":
				font = new Font(Font.SERIF, 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			case "SansSerif":
				font = new Font(Font.SANS_SERIF, 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			case "Monospaced":
				font = new Font(Font.MONOSPACED, 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			case "Dialog":
				font = new Font(Font.DIALOG, 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			case "DialogInput":
				font = new Font(Font.DIALOG_INPUT, 0, Integer.parseInt(fontSize.getActionCommand()));
				break;
			default:
				break;
		}

		switch (textColor.getActionCommand()) {
			case "Black":
				tc = Color.BLACK;
				break;
			case "White":
				tc = Color.WHITE;
				break;
			case "Red":
				tc = Color.RED;
				break;
			case "Green":
				tc = Color.GREEN;
				break;
			case "Blue":
				tc = Color.BLUE;
				break;
			default:
				break;
		}

		switch (background.getActionCommand()) {
			case "Black":
				bc = Color.BLACK;
				break;
			case "White":
				bc = Color.WHITE;
				break;
			case "Red":
				bc = Color.RED;
				break;
			case "Green":
				bc = Color.GREEN;
				break;
			case "Blue":
				bc = Color.BLUE;
				break;
			default:
				break;
		}

		if (e.getActionCommand() == "Exit") System.exit(0);
	}

	public Font getFont() {
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
}

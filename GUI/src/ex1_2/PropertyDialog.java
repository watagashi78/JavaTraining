package ex1_2;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PropertyDialog extends Dialog implements ActionListener {
	private Font font;
	private Color textcolor;
	private Color background;
	private boolean flag = false;

	private Label l1 = new Label("Font Size", Label.CENTER);
	private Label l2 = new Label("Font", Label.CENTER);
	private Label l3 = new Label("Font Color", Label.CENTER);
	private Label l4 = new Label("Background Color", Label.CENTER);

	private Choice fsize = new Choice();
	private Choice fonts = new Choice();
	private Choice fontcolor = new Choice();
	private Choice bc = new Choice();

	private Button ok = new Button("決定");
	private Button cancel = new Button("キャンセル");

	PropertyDialog(Frame owner) {
		super(owner);
		this.setLayout(new GridLayout(5, 2));
		this.setTitle("プロパティ");
		this.setSize(300, 200);
		this.setResizable(false);

		this.add(l1);

		fsize.add("30");
		fsize.add("36");
		fsize.add("42");
		fsize.add("48");
		fsize.add("56");
		fsize.add("62");
		fsize.add("68");
		fsize.add("74");
		fsize.add("80");
		fsize.add("88");
		fsize.add("96");
		fsize.add("100");
		this.add(fsize);

		this.add(l2);

		fonts.add("Serif");
		fonts.add("SansSerif");
		fonts.add("Monospaced");
		fonts.add("Dialog");
		fonts.add("DialogInput");
		this.add(fonts);

		this.add(l3);

		fontcolor.add("Black");
		fontcolor.add("White");
		fontcolor.add("Red");
		fontcolor.add("Green");
		fontcolor.add("Blue");
		this.add(fontcolor);

		this.add(l4);

		bc.add("White");
		bc.add("Black");
		bc.add("Red");
		bc.add("Green");
		bc.add("Blue");
		this.add(bc);

		this.add(ok);
		ok.addActionListener(this);
		this.add(cancel);
		cancel.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				close.getWindow().dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("決定")) {
			switch (fonts.getSelectedItem()) {
				case "Serif":
					font = new Font(Font.SERIF, 0, Integer.parseInt(fsize.getSelectedItem()));
					break;
				case "SansSerif":
					font = new Font(Font.SANS_SERIF, 0, Integer.parseInt(fsize.getSelectedItem()));
					break;
				case "Monospaced":
					font = new Font(Font.MONOSPACED, 0, Integer.parseInt(fsize.getSelectedItem()));
					break;
				case "Dialog":
					font = new Font(Font.DIALOG, 0, Integer.parseInt(fsize.getSelectedItem()));
					break;
				case "DialogInput":
					font = new Font(Font.DIALOG_INPUT, 0, Integer.parseInt(fsize.getSelectedItem()));
					break;
				default:
					break;
			}

			switch (fontcolor.getSelectedItem()) {
				case "Black":
					textcolor = Color.BLACK;
					break;
				case "White":
					textcolor = Color.WHITE;
					break;
				case "Red":
					textcolor = Color.RED;
					break;
				case "Green":
					textcolor = Color.GREEN;
					break;
				case "Blue":
					textcolor = Color.BLUE;
					break;
				default:
					break;
			}

			switch (bc.getSelectedItem()) {
				case "Black":
					background = Color.BLACK;
					break;
				case "White":
					background = Color.WHITE;
					break;
				case "Red":
					background = Color.RED;
					break;
				case "Green":
					background = Color.GREEN;
					break;
				case "Blue":
					background = Color.BLUE;
					break;
				default:
					break;
			}
			flag = true;
		}
		this.dispose();
	}

	public boolean isFlag() {
		return flag;
	}

	public Font getFont() {
		return font;
	}

	public Color getTextcolor() {
		return textcolor;
	}

	public Color getBackground() {
		return background;
	}

	public int getWindowWidth() {
		return 600;
	}

	public int getWindowHeight() {
		if (Integer.parseInt(fsize.getSelectedItem()) < 40) {
			return 200;
		} else if (Integer.parseInt(fsize.getSelectedItem()) < 50) {
			return 250;
		} else if (Integer.parseInt(fsize.getSelectedItem()) < 60) {
			return 300;
		} else if (Integer.parseInt(fsize.getSelectedItem()) < 70) {
			return 350;
		} else if (Integer.parseInt(fsize.getSelectedItem()) < 80) {
			return 400;
		} else if (Integer.parseInt(fsize.getSelectedItem()) < 90) {
			return 450;
		} else {
			return 500;
		}
	}
}

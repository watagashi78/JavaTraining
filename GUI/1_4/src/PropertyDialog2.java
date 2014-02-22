package src;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.prefs.Preferences;

public class PropertyDialog2 extends Dialog implements ActionListener {
	private Font font;
	private Color textcolor;
	private Color background;
	private boolean flag = false;
	private GridBagLayout gbl = new GridBagLayout();
	private Preferences prefs;

	private Label l1 = new Label("Font Size", Label.RIGHT);
	private Label l2 = new Label("Font", Label.RIGHT);
	private Label l3 = new Label("Font Color", Label.RIGHT);
	private Label l4 = new Label("Background Color", Label.RIGHT);

	private Choice fsize = new Choice();
	private Choice fonts = new Choice();
	private Choice fontcolor = new Choice();
	private Choice bc = new Choice();

	private Button ok = new Button("決定");
	private Button cancel = new Button("キャンセル");

	PropertyDialog2(Frame owner) {
		super(owner);
		this.setLayout(gbl);
		this.setTitle("プロパティ");
		prefs = Preferences.userRoot().node("hasegawaDigitalclock");
		this.setSize(400, 200);
		this.setLocation(prefs.getInt("window_x", 0), prefs.getInt("window_y", 0));
		this.setResizable(false);

		addChoice(fsize, "font-size", 60, 70, 80, 90, 100, 120, 140, 160, 180, 200);
		addChoice(fonts, "font", "Serif", "SansSerif", "Monospaced", "Dialog", "DialogInput");
		addChoice(fontcolor, "text-color", "White", "Red", "Green", "Blue", "Black");
		addChoice(bc, "background-color", "Blue", "White", "Black", "Red", "Green");

		addComponent(l1, 0, 0, 2, 1);
		addComponent(fsize, 2, 0, 2, 1);

		addComponent(l2, 0, 1, 2, 1);
		addComponent(fonts, 2, 1, 2, 1);

		addComponent(l3, 0, 2, 2, 1);
		addComponent(fontcolor, 2, 2, 2, 1);

		addComponent(l4, 0, 3, 2, 1);
		addComponent(bc, 2, 3, 2, 1);

		addComponent(cancel, 1, 4, 2, 1);
		cancel.addActionListener(this);
		addComponent(ok, 3, 4, 2, 1);
		ok.addActionListener(this);


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				close.getWindow().dispose();
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("決定")) {
			prefs.putInt("font-size", Integer.parseInt(fsize.getSelectedItem()));
			prefs.put("font", fonts.getSelectedItem());
			prefs.put("text-color", fontcolor.getSelectedItem());
			prefs.put("background-color", bc.getSelectedItem());
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

	private void addChoice(Choice c, String type, String...strings) {
		ArrayList<String> arr = new ArrayList<String>();
		for (String s: strings) {
			if (s.equals(prefs.get(type, null))) {
				arr.add(0, s);
			} else {
				arr.add(s);
			}
		}
		for (String s: arr) {
			c.add(s);
		}
	}

	private void addChoice(Choice c, String type, int...ints) {
		ArrayList<String> arr = new ArrayList<String>();
		for (Integer index: ints) {
			if (index.equals(prefs.getInt("font-size", 50))) {
				arr.add(0, index.toString());
			} else {
				arr.add(index.toString());
			}
		}
		for (String s: arr) {
			c.add(s);
		}
	}

	private void addComponent(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.insets = new Insets(5, 5, 5, 5);
		gbl.setConstraints(c, gbc);
		this.add(c);
	}
}

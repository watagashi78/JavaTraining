package interpret;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.ScrollPane;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewConstructor extends Dialog implements ActionListener, ItemListener {
	private Model model;
	private List constList = new List();
	private DefaultTableModel selected;
	private ScrollPane spane = new ScrollPane();
	private JTable tbl = new JTable(5, 2);
	private TextField tx = new TextField();
	private Button decide = new Button("Create Constructor");
	private GridBagLayout gbl = new GridBagLayout();

	public ViewConstructor(Frame parent, Model model) {
		super(parent);
		this.model = model;
		addList(model.getConstructors());
		setup();
		setVisible(true);
	}

	public void setup() {
		setLayout(gbl);
		setTitle("Select Constructor");
		setSize(400, 500);
		setLocation(500, 500);
		setResizable(false);


		addComponent(constList, 0, 0, 1, 4);
		constList.addItemListener(this);
		spane.add(tbl);
		addComponent(spane, 0, 5, 1, 3);

		decide.addActionListener(this);
		addComponent(decide, 0, 8, 1, 2);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				close.getWindow().dispose();
			}
		});
	}

	public void reset() {
		spane.remove(tbl);
		spane.add(tbl);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Create Constructor")) {
			System.out.println("create constructor");
		}
		this.dispose();
	}

	private void addList(Constructor<?>[] consts) {
		for (Constructor<?> c : consts) {
			constList.add(strip(c.toString(), "java"));
		}
	}

	// private Utilities
	private String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "(\\w*\\.)*", "");
		else
			result = str;
		return result;
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		selected = model.getTableModel(constList.getSelectedItem());
		tbl = new JTable(selected);
		reset();
	}

	private void addComponent(Component c, int x, int y, int w, int h) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gbc.weightx = 0.1d;
		gbc.weighty = 0.1d;
		gbc.insets = new Insets(10, 10, 10, 10);
		gbl.setConstraints(c, gbc);
		add(c);
	}
}

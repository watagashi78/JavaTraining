package interpret;

import java.awt.Button;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.List;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Constructor;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ViewConstructor extends Dialog implements ActionListener, ItemListener {
	private Model model;
	private List constList = new List();
	private DefaultTableModel selected;
	private JTable tbl = new JTable(5, 2);
	private TextField tx = new TextField("Please input instance name here.");
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

		addComponent(constList, 0, 0, 1, 6);
		addComponent(tx, 0, 7, 1, 1);
		addComponent(tbl, 0, 8, 1, 4);
		addComponent(decide, 0, 12, 1, 1);

		constList.addItemListener(this);
		decide.addActionListener(this);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent close) {
				close.getWindow().dispose();
			}
		});
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
		selected.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				//System.out.println(e.getLastRow() + ", " + e.getColumn());
				//check(e.getLastRow(), e.getColumn());
			}
		});
		tbl.setModel(selected);
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

	private boolean check() {
		boolean result = false;
		String tmpType;
		Object tmp;
		Vector data = selected.getDataVector();
		for (int i = 0; i < selected.getRowCount(); i++) {
			for (int j = 0; j < selected.getColumnCount(); j++) {
				tmpType = ((Vector) data.elementAt(i)).elementAt(0).toString();
				tmp = ((Vector) data.elementAt(i)).elementAt(j).toString();
				try {
					tmp = parse(Class.forName(tmpType), tmp.toString());
					result = true;
				} catch (ClassNotFoundException e) {
					System.out.println("Illegal Type Value");
				}
			}
		}
		return result;
	}

	private boolean check(int x, int y) {
		boolean result = false;
		String tmpType;
		Object tmp;
		Vector data = selected.getDataVector();
		tmpType = ((Vector) data.elementAt(x)).elementAt(0).toString();
		tmp = ((Vector) data.elementAt(x)).elementAt(y).toString();
		try {
			tmp = parse(Class.forName(tmpType), tmp.toString());
			result = true;
		} catch (ClassNotFoundException e) {
			System.out.println("Illegal Type Value");
		}
		return result;
	}

	private Object parse(Class<?> cls, String value) {
		if (cls == byte.class || cls == Byte.class) {
			return Byte.parseByte(value);
		} else if (cls == short.class || cls == Short.class) {
			return Short.parseShort(value);
		} else if (cls == int.class || cls == Integer.class) {
			return Integer.parseInt(value);
		} else if (cls == long.class || cls == Long.class) {
			return Long.parseLong(value);
		} else if (cls == float.class || cls == Float.class) {
			return Float.parseFloat(value);
		} else if (cls == double.class || cls == Double.class) {
			return Double.parseDouble(value);
		} else if (cls == char.class || cls == Character.class) {
			return value.charAt(0);
		} else if (cls == boolean.class || cls == Boolean.class) {
			return Boolean.parseBoolean(value);
		} else if (cls == String.class) {
			return value;
		} else {
			throw new UnsupportedOperationException();
		}
	}
}

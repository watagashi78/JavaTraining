package interpret;

import interpret.Model.RawData;

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

import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

public class ViewConstructor extends Dialog implements ActionListener, ItemListener {
	private Model model;
	private List constList = new List();
	private DefaultTableModel selectedStringModel;
	private RawData[] selectedRawData;
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
		selectedRawData = model.getRawData(constList.getSelectedItem());
		selectedStringModel = model.getStringTableModel(constList.getSelectedItem());
		selectedStringModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(TableModelEvent e) {
				String changeData = selectedStringModel.getValueAt(e.getLastRow(), e.getColumn()).toString();
				Class<?> cls = selectedRawData[e.getLastRow()].getRawClass();
				selectedRawData[e.getLastRow()].setValue(parse(cls, changeData));
			}
		});
		tbl.setModel(selectedStringModel);
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
		} else if (cls == byte[].class || cls == Byte[].class) {
			String[] str = value.split(",");
			Byte[] result = new Byte[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Byte.parseByte(str[i]);
			}
			return result;
		} else if (cls == short[].class || cls == Short[].class) {
			String[] str = value.split(",");
			Short[] result = new Short[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Short.parseShort(str[i]);
			}
			return result;
		} else if (cls == int[].class || cls == Integer[].class) {
			String[] str = value.split(",");
			Integer[] result = new Integer[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Integer.parseInt(str[i]);
			}
			return result;
		} else if (cls == long[].class || cls == Long[].class) {
			String[] str = value.split(",");
			Long[] result = new Long[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Long.parseLong(str[i]);
			}
			return result;
		} else if (cls == float[].class || cls == Float[].class) {
			String[] str = value.split(",");
			Float[] result = new Float[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Float.parseFloat(str[i]);
			}
			return result;
		} else if (cls == double[].class || cls == Double[].class) {
			String[] str = value.split(",");
			Double[] result = new Double[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Double.parseDouble(str[i]);
			}
			return result;
		} else if (cls == char[].class || cls == Character[].class) {
			String[] str = value.split(",");
			Character[] result = new Character[str.length];
			for (int i = 0; i < str.length; i++) {
				for (int j = i; j < str[i].length(); j++) {
					result[j] = str[i].charAt(j);
				}
			}
			return result;
		} else if (cls == boolean[].class || cls == Boolean[].class) {
			String[] str = value.split(",");
			Boolean[] result = new Boolean[str.length];
			for (int i = 0; i < str.length; i++) {
				result[i] = Boolean.parseBoolean(str[i]);
			}
			return result;
		} else if (cls == String[].class) {
			String[] result = value.split(",");
			return result;
		} else {
			System.out.println("Class = " + cls + ", Value = :" + value);
			throw new UnsupportedOperationException();
		}
	}
}

package interpret;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeModel;

public class Model {
	private EventListenerList listenerList = new EventListenerList();

	private DefaultTreeModel tree;
	private Map<Field, DefaultTableModel> fieldMap = new HashMap<Field, DefaultTableModel>();
	private Map<Method, DefaultTableModel> methodMap = new HashMap<Method, DefaultTableModel>();
	private Map<Constructor<?>, DefaultTableModel> constMap = new HashMap<Constructor<?>, DefaultTableModel>();

	private Constructor<?>[] constArr;
	private Field[] fieldArr;
	private Method[] methodArr;
	private Class<?> cls;

	public Model() {
	}

	public Model(String str) {
		try {
			cls = Class.forName(str);
			constArr = cls.getConstructors();
			fieldArr = cls.getFields();
			methodArr = cls.getMethods();
			createConstructorTables(constArr);
			createFieldTables(fieldArr);
			createMethodTables(methodArr);
		} catch (ClassNotFoundException e) {
			System.out.println("Unknown Class: " + str); // result
		}
	}

	// Accessors
	public Constructor<?>[] getConstructors() {
		return constArr;
	}
	public Field[] getFields() {
		return fieldArr;
	}

	public Method[] getMethods() {
		return methodArr;
	}

	public DefaultTableModel getTableModel(String str) {
		for (Field f : fieldArr) {
			if (strip(f.toString(), "java").equals(str)) {
				return fieldMap.get(f);
			}
		}
		for (Method m : methodArr) {
			if (strip(m.toString(), "java").equals(str)) {
				return methodMap.get(m);
			}
		}
		for (Constructor<?> c : constArr) {
			if (strip(c.toString(), "java").equals(str)) {
				return constMap.get(c);
			}
		}
		return null;
	}

	public Constructor<?> getConstructor(String str) {
		for (Constructor<?> c : constArr) {
			if (strip(c.toString(), "java").equals(str)) {
				return c;
			}
		}
		return null;
	}

	public List getAllStringFields() {
		List results = new List();
		for (Field f : fieldArr) {
			results.add(strip(f.toString(), "java"));
		}
		return results;
	}

	public List getAttStringMethods() {
		List results = new List();
		for (Method m : methodArr) {
			results.add(strip(m.toString(), "java"));
		}
		return results;
	}

	// Listeners
	public void addModelListener(ModelListener listener) {
		listenerList.add(ModelListener.class, listener);
	}

	public void removeModelListener(ModelListener listener) {
		listenerList.remove(ModelListener.class, listener);
	}

	// private Model Utilities
	private String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "(\\w*\\.)*", "");
		else
			result = str;
		return result;
	}

	private void createFieldTables(Field[] fields) {
		String[] columnNames = { "Type", "Value" };
		for (Field f : fields) {
			String[][] tbldata = new String[1][2];
			tbldata[0][0] = f.getType().toString();
			try {
				tbldata[0][1] = f.get(f.getType()).toString();
			} catch (IllegalArgumentException e) {

			} catch (IllegalAccessException e) {

			}
			fieldMap.put(f, new DefaultTableModel(tbldata, columnNames) {
				boolean[] columnEditables = new boolean[] {false, true};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}

	private void createMethodTables(Method[] methods) {
		String[] columnNames = { "Type", "Parameter" };
		for (Method m : methods) {
			Class<?>[] classes = m.getParameterTypes();
			String[][] tbldata = new String[classes.length][2];
			for (int i = 0; i < classes.length; i++) {
				tbldata[i][0] = classes[i].toString();
				tbldata[i][1] = "";
			}
			methodMap.put(m, new DefaultTableModel(tbldata, columnNames) {
				boolean[] columnEditables = new boolean[] {false, true};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}

	private void createConstructorTables(Constructor<?>[] consts) {
		String[] columnNames = { "Type", "Parameter" };
		for (Constructor<?> c : consts) {
			Class<?>[] classes = c.getParameterTypes();
			String[][] tbldata = new String[classes.length][2];
			for (int i = 0; i < classes.length; i++) {
				tbldata[i][0] = classes[i].toString();
				tbldata[i][1] = "";
			}
			constMap.put(c, new DefaultTableModel(tbldata, columnNames) {
				boolean[] columnEditables = new boolean[] {false, true};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}

}
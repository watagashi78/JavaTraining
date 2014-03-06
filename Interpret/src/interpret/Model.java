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
	private Map<Field, RawData[]> fieldMap = new HashMap<Field, RawData[]>();
	private Map<Method, RawData[]> methodMap = new HashMap<Method, RawData[]>();
	private Map<Constructor<?>, RawData[]> constMap = new HashMap<Constructor<?>, RawData[]>();
	private Map<Field, DefaultTableModel> fieldStringMap = new HashMap<Field, DefaultTableModel>();
	private Map<Method, DefaultTableModel> methodStringMap = new HashMap<Method, DefaultTableModel>();
	private Map<Constructor<?>, DefaultTableModel> constStringMap = new HashMap<Constructor<?>, DefaultTableModel>();

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

	public RawData[] getRawData(String str) {
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

	public DefaultTableModel getStringTableModel(String str) {
		for (Field f : fieldArr) {
			if (strip(f.toString(), "java").equals(str)) {
				return fieldStringMap.get(f);
			}
		}
		for (Method m : methodArr) {
			if (strip(m.toString(), "java").equals(str)) {
				return methodStringMap.get(m);
			}
		}
		for (Constructor<?> c : constArr) {
			if (strip(c.toString(), "java").equals(str)) {
				return constStringMap.get(c);
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
			RawData[] tbldata = new RawData[1];
			String[][] tblstring = new String[1][2];
			tblstring[0][0] = f.getType().getSimpleName();
			try {
				tbldata[0] = new RawData(f.getType(), f.get(f.getType()));
				tblstring[0][1] = f.get(f.getType()).toString();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			fieldMap.put(f, tbldata);
			fieldStringMap.put(f, new DefaultTableModel(tblstring, columnNames) {
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
			RawData[] tbldata = new RawData[classes.length];
			String[][] tblstring = new String[classes.length][2];
			for (int i = 0; i < classes.length; i++) {
				tbldata[i] = new RawData(classes[i], "");
				tblstring[i][0] = classes[i].getSimpleName();
				tblstring[i][1] = "";
			}
			methodMap.put(m, tbldata);
			methodStringMap.put(m, new DefaultTableModel(tblstring, columnNames) {
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
			RawData[] tbldata = new RawData[classes.length];
			String[][] tblstring = new String[classes.length][2];
			for (int i = 0; i < classes.length; i++) {
				tbldata[i] = new RawData(classes[i], "");
				tblstring[i][0] = classes[i].getSimpleName();
				tblstring[i][1] = "";
			}
			constMap.put(c, tbldata);
			constStringMap.put(c, new DefaultTableModel(tblstring, columnNames) {
				boolean[] columnEditables = new boolean[] {false, true};
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}

	public class RawData {
		Class<?> cls;
		Object obj;
		public RawData(){
			this.cls = Object.class;
			this.obj = new Object();
		}
		public RawData(Class<?> cls, Object obj) {
			this.cls = cls;
			this.obj = obj;
		}

		public void setValue(Object obj) {
			this.obj = obj;
		}
		public Class<?> getRawClass() {
			return cls;
		}
		public Object getRawObject() {
			return obj;
		}

	}

}
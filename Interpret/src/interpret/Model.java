package interpret;

import java.awt.List;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import javax.swing.event.EventListenerList;
import javax.swing.table.DefaultTableModel;

public class Model {
	private EventListenerList listenerList = new EventListenerList();

	private Map<Field, RawData[]> fieldMap = new HashMap<Field, RawData[]>();
	private Map<Method, RawData[]> methodMap = new HashMap<Method, RawData[]>();
	private Map<Constructor<?>, RawData[]> constMap = new HashMap<Constructor<?>, RawData[]>();
	private Map<Field, DefaultTableModel> fieldStringMap = new HashMap<Field, DefaultTableModel>();
	private Map<Method, DefaultTableModel> methodStringMap = new HashMap<Method, DefaultTableModel>();
	private Map<Constructor<?>, DefaultTableModel> constStringMap = new HashMap<Constructor<?>, DefaultTableModel>();

	private Object object = new Object();
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

	public Model(Object obj) {
		this.object = obj;
		cls = obj.getClass();
		constArr = cls.getConstructors();
		fieldArr = getFields(obj);
		methodArr = getMethods(obj);
		createConstructorTables(constArr);
		createFieldTables(fieldArr, obj);
		createMethodTables(methodArr);
	}

	// Accessors
	public Constructor<?>[] getConstructors() {
		return constArr;
	}

	public Field[] getFields() {
		return fieldArr;
	}

	public Field[] getFields(Object obj) {
		HashSet<Field> fieldSet = new HashSet<Field>();
		fieldSet.addAll(Arrays.asList(obj.getClass().getFields()));
		fieldSet.addAll(Arrays.asList(obj.getClass().getDeclaredFields()));
		Field[] fields = new Field[fieldSet.size()];
		fieldSet.toArray(fields);
		Arrays.sort(fields, new MemberComparator()); // ソート
		return fields;
	}

	public Object getFieldValue(Field f, Object obj) throws IllegalAccessException, IllegalArgumentException {
		f.setAccessible(true);
		return f.get(obj);
	}

	public Method[] getMethods() {
		return methodArr;
	}

	public Method[] getMethods(Object obj) {
		HashSet<Method> methodSet = new HashSet<Method>();
		methodSet.addAll(Arrays.asList(obj.getClass().getMethods()));
		methodSet.addAll(Arrays.asList(obj.getClass().getDeclaredMethods()));
		Method[] methods = new Method[methodSet.size()];
		methodSet.toArray(methods);
		Arrays.sort(methods, new MemberComparator()); // ソート
		return methods;
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
		throw new AssertionError("Member not Found.");
	}

	public void setRawFieldData(String str, Object setObj)  {
		RawData[] data = null;
		Field field = null;
		for (Field f : fieldArr) {
			if (strip(f.toString(), "java").equals(str)) {
				data = fieldMap.get(f);
				field = f;
			}
		}
		if (data == null || field == null) throw new AssertionError("Field not Found.");
		if (data[0].obj.getClass() != setObj.getClass()) throw new IllegalArgumentException("引数の型が不正です");
		try {
			field.setAccessible(true);
			data[0].setValue(setObj);
			field.set(this.object, setObj);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public boolean isStaticField(String str) {
		Field field = null;
		for (Field f : fieldArr) {
			if (strip(f.toString(), "java").equals(str)) {
				field = f;
				break;
			}
		}
		if (Modifier.isStatic(field.getModifiers())) {
			return true;
		} else {
			return false;
		}
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
		throw new AssertionError("Member not Found.");
	}

	public Constructor<?> getConstructor(String str) {
		for (Constructor<?> c : constArr) {
			if (strip(c.toString(), "java").equals(str)) {
				return c;
			}
		}
		throw new AssertionError("Constructor not Found.");
	}

	public Class<?> getModelClass() {
		return cls;
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

	public Object getObject() {
		return object;
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
			f.setAccessible(true);
			RawData[] tbldata = new RawData[1];
			String[][] tblstring = new String[1][2];
			tblstring[0][0] = f.getType().getSimpleName();
			try {
				tbldata[0] = new RawData(f.getType(), f.get(f.getType()));
				if (f.get(f.getType()) == null) {
					tblstring[0][1] = "";
				} else {
					tblstring[0][1] = f.get(f.getType()).toString();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			fieldMap.put(f, tbldata);
			fieldStringMap.put(f, new DefaultTableModel(tblstring, columnNames) {
				boolean[] columnEditables = new boolean[] { false, true };
				@Override
				public boolean isCellEditable(int row, int column) {
					return columnEditables[column];
				}
			});
		}
	}

	private void createFieldTables(Field[] fields, Object obj) {
		String[] columnNames = { "Type", "Value" };
		for (Field f : fields) {
			boolean isStatic = Modifier.isStatic(f.getModifiers());
			f.setAccessible(true);
			RawData[] tbldata = new RawData[1];
			String[][] tblstring = new String[1][2];
			tblstring[0][0] = f.getType().getSimpleName();
			try {
				tbldata[0] = new RawData(f.getType(), getFieldValue(f, obj));
				if (getFieldValue(f, obj) == null) {
					tblstring[0][1] = "";
				} else {
					tblstring[0][1] = getFieldValue(f, obj).toString();
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			fieldMap.put(f, tbldata);
			if (isStatic) {
				fieldStringMap.put(f, new DefaultTableModel(tblstring, columnNames) {
					boolean[] columnEditables = new boolean[] { false, false };
					@Override
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			} else {
				fieldStringMap.put(f, new DefaultTableModel(tblstring, columnNames) {
					boolean[] columnEditables = new boolean[] { false, true };
					@Override
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			}
		}
	}

	private void createMethodTables(Method[] methods) {
		String[] columnNames = { "Type", "Parameter" };
		for (Method m : methods) {
			m.setAccessible(true);
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
				boolean[] columnEditables = new boolean[] { false, true };

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
			c.setAccessible(true);
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
				boolean[] columnEditables = new boolean[] { false, true };

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

		public RawData() {
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
	class MemberComparator implements Comparator<Member> {
		@Override
		public int compare(Member m1, Member m2) {
			String s1 = m1.getName();
			String s2 = m2.getName();
			return s1.compareTo(s2);
		}
	}

}
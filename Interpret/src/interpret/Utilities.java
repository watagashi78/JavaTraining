package interpret;

import interpret.Model.RawData;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utilities {

	public static Object createNewObject(Constructor<?> con, RawData[] params) throws Throwable {
		Object newObject;
		Object[] input = new Object[params.length];
		for (int i = 0; i < params.length; i++) {
			input[i] = params[i].getRawObject();
		}
		try {
			newObject = con.newInstance(input);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (InstantiationException e) {
			throw new InstantiationException(e.toString());
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException(e.toString());
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}
		return newObject;
	}

	public static Object invokeMethod(Method m, Object obj, RawData[] params) throws Throwable {
		Object retObj;
		Object[] input = new Object[params.length];
		m.setAccessible(true);
		for (int i = 0; i < params.length; i++) {
			input[i] = params[i].getRawObject();
		}
		try {
			retObj = m.invoke(obj, input);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalAccessException(e.toString());
		} catch (InvocationTargetException e) {
			throw e.getCause();
		}

		return retObj;
	}

	public static Object parse(Class<?> cls, String value) {
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
			throw new UnsupportedOperationException();
		}
	}

	public static String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "(\\w*\\.)*", "");
		else
			result = str;
		return result;
	}
}

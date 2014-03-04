package interpret;

import java.lang.reflect.Field;

public class MyField {
	private final Field field;
	private final Object obj;

	public MyField(Field field, Object obj) {
		this.field = field;
		this.obj = obj;
	}

	public Field getField() {
		return field;
	}

	public String getFieldName() {
		return strip(field.toString(), "java");
	}

	public Object getObject() {
		return obj;
	}

	private String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "(\\w*\\.)*", "");
		else
			result = str;
		return result;
	}
}

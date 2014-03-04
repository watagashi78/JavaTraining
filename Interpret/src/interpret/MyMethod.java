package interpret;

import java.lang.reflect.Method;

public class MyMethod {
	private final Method method;
	private final Object obj;

	public MyMethod(Method method, Object obj) {
		this.method = method;
		this.obj = obj;
	}

	public Method getMethod() {
		return method;
	}

	public String getMethodName() {
		return strip(method.toString(), "java");
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

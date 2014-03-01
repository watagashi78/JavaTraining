package ex16_05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class ClassContents2 {

	public static void main(String[] args) {
		try {
			Class<?> c = Class.forName(args[0]);
			System.out.println(c);
			printMembers(c.getFields());
			printMembers(c.getConstructors());
			printMembers(c.getMethods());
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
	}

	private static void printMembers(Member[] mems) {
		for (Member m : mems) {
			if (m.getDeclaringClass() == Object.class)
				continue;
			Annotation[] annotations;
			if (m.getClass().isAssignableFrom(Field.class))
				annotations = ((Field) m).getAnnotations();
			else if (m.getClass().isAssignableFrom(Constructor.class))
				annotations = ((Constructor<?>) m).getAnnotations();
			else if (m.getClass().isAssignableFrom(Method.class))
				annotations = ((Method) m).getAnnotations();
			else
				return;

			for (Annotation a : annotations) {
				System.out.println(" " + a);
			}
			String decl = m.toString();
			System.out.print(" ");
			System.out.println(strip(decl, "java."));
		}
	}

	private static String strip(String str, String stripChar) {
		String result;
		if (str.contains(stripChar))
			result = str.replaceAll(stripChar + "(\\w*\\.)*", "");
		else
			result = str;
		return result;
	}

}

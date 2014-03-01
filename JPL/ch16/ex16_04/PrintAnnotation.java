package ex16_04;

import java.lang.annotation.Annotation;

public class PrintAnnotation {

	public static void main(String[] args) {
		PrintAnnotation test = new PrintAnnotation();
		test.printAnnotation(ex16_04.SampleClass.class);
	}

	public void printAnnotation(Class<?> c) {
		Annotation[] annotations = c.getAnnotations();
		for (Annotation a : annotations) {
			System.out.println(a);
		}
	}
}

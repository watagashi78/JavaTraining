package ex16_05;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface StringAnnotation{
	String value();
}

public class SampleClass {

	@StringAnnotation("Field")
	public int a,b,c;

	@StringAnnotation("Field")
	private String x,y,z;

	@StringAnnotation("Constructor")
	public SampleClass() {}

	@StringAnnotation("Method")
	public void function(@StringAnnotation("Argument") int param) {
		@StringAnnotation("Local Variable")
		int local = 1;
		System.out.println(local);
	}
}

package ex16_04;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@interface Marker{}

@Retention(RetentionPolicy.RUNTIME)
@interface Hoge{
	String value() default "hoge";
}

@Marker
@Hoge
public class SampleClass {}

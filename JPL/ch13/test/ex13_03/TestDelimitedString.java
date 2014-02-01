package ex13_03;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDelimitedString {
	String case1 = "《test1》, 《test2》";
	String case2 = "《test1《test2》》";
	String case3 = "》《test1》》《test2》";
	char start = '《';
	char end = '》';

	@Test
	public void testAllDelimitedString() {
		DelimitedString test = new DelimitedString();
		assertArrayEquals(new String[]{"test1","test2"}, test.allDelimitedString(case1, start, end));
		assertArrayEquals(new String[]{"test1《test2"}, test.allDelimitedString(case2, start, end));
		assertArrayEquals(new String[]{"test1","test2"}, test.allDelimitedString(case3, start, end));
	}
}

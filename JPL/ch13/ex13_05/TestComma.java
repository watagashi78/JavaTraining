package ex13_05;

import org.junit.Test;

public class TestComma {

	@Test
	public void testAddComma() {
		Comma test = new Comma();
		System.out.println(test.addComma("123456"));
		System.out.println(test.addComma("1234567"));
		System.out.println(test.addComma("12345678"));
		System.out.println(test.addComma("123456789"));
	}

}

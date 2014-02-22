package ex13_05;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestComma {

	@Test
	public void testAddComma() {
		Comma test = new Comma();
		assertTrue("123,456".equals(test.addComma("123456")));
		assertTrue("1,234,567".equals(test.addComma("1234567")));
		assertTrue("12,345,678".equals(test.addComma("12345678")));
		assertTrue("123,456,789".equals(test.addComma("123456789")));
	}

}

package ex13_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCharCount {

	@Test
	public void testCount() {
		CharCount test = new CharCount();
		String str = "Hello World, はろーわーるど";
		char l = 'l';
		char o = 'o';
		char z = 'z';
		char hyphen = 'ー';
		char space = ' ';
		char ha = 'は';
		assertEquals(3, test.count(str, l));
		assertEquals(2, test.count(str, o));
		assertEquals(0, test.count(str, z));
		assertEquals(2, test.count(str, hyphen));
		assertEquals(2, test.count(str, space));
		assertEquals(1, test.count(str, ha));
	}

}

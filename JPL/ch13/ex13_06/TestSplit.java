package ex13_06;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSplit {

	@Test
	public void testSplit() {
		Split test = new Split();
		assertTrue("12,34,56".equals(test.split("123456", ',', 2)));
		assertTrue("1&234&567".equals(test.split("1234567", '&', 3)));
		assertTrue("1,2345,6789,1011,1213".equals(test.split("12345678910111213", ',', 4)));
		assertTrue("1,2,3,4,5,6,7,8,9,1,0,1,1,1,2,1,3".equals(test.split("12345678910111213", ',', 1)));
	}

}

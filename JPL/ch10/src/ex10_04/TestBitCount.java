package ex10_04;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestBitCount {
	@Test
	public void testGetBitCount() {
		BitCount test = new BitCount();
		assertEquals(0, test.getBitCount(0));
		assertEquals(1, test.getBitCount(1));
		assertEquals(2, test.getBitCount(3));
		assertEquals(10,test.getBitCount(1023));
		assertEquals(1, test.getBitCount(1024));
	}

}

package ex13_02;
import static org.junit.Assert.*;

import org.junit.Test;

import ex13_02.StrCount;


public class TestStrCount {

	@Test
	public void testCount() {
		StrCount test = new StrCount();
		String input = "すもももももももものうち";
		String check1 = "もも";
		String check2 = "すもも";
		String check3 = "ゼロ";
		assertEquals(7, test.count(input, check1));
		assertEquals(1, test.count(input, check2));
		assertEquals(0, test.count(input, check3));
	}

}

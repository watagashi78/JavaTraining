package ex20_04;

import static org.junit.Assert.*;

import java.io.CharArrayReader;
import java.io.IOException;

import org.junit.Test;

public class TestLineReader {
	private static final String CRLF = "\n";

	@Test
	public void test() {
		String input = "Test" + CRLF + "Line" + CRLF + "Reader";
		String[] inputArr = input.split(CRLF);
		CharArrayReader car = new CharArrayReader(input.toCharArray());
		LineReader lr = new LineReader(car);
		try {
			for (int i = 0; i < inputArr.length; i++) {
				int[] result = lr.readLine();
				for(int ii = 0; ii < result.length; ii++) {
					assertEquals(inputArr[i].charAt(ii), result[ii]);
				}
			}
		} catch (IOException e) {
			fail();
		}
	}

}

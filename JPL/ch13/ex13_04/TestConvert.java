package ex13_04;

import java.io.FileNotFoundException;

import org.junit.Test;

public class TestConvert {

	@Test
	public void testRead() throws FileNotFoundException {
		Convert test = new Convert();
		test.read("ch13\\ex13_04\\input.txt");
	}

}

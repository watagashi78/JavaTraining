package ex20_06;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

public class TestNameOpValue {

	@Test
	public void test() throws IOException {
		NameOpValue nov = new NameOpValue();
		nov.calc(new StringReader("One = 10\nTwo + 4\nTwo + 7\nThree = 20\nThree - 8"));
		assertEquals(10.0, nov.getResult("One"), 0.0);
		assertEquals(11.0, nov.getResult("Two"), 0.0);
		assertEquals(12.0, nov.getResult("Three"), 0.0);
	}

}

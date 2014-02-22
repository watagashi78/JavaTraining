package ex14_02;

import org.junit.Test;

public class TestPrintServer {

	@Test
	public void testPrintServer() {
		PrintServer test = new PrintServer();
		test.print("test1");
		test.print("test2");
	}

	@Test (expected = IllegalStateException.class)
	public void testPrintServerException() {
		PrintServer test = new PrintServer();
		test.run();
	}

}

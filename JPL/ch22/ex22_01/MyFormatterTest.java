package ex22_01;

import org.junit.Before;
import org.junit.Test;

public class MyFormatterTest {
	private MyFormatter mf;
	private double[] data;

	@Before
	public void setUp() throws Exception {
		data = new double[100];
		for(int i = 0; i < data.length; i++){
			data[i] = Math.random();
		}
		mf = new MyFormatter();
	}

	@Test
	public void test() {
		mf.format(data, 5);
	}

}

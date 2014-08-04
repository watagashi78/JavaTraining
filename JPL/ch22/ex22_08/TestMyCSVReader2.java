package ex22_08;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestMyCSVReader2 extends TestCase {

	private static final String FILE_PATH = "test.csv";
	private static final String ERROR_FILE_PATH = "error_test.csv";

	@Before
	public void setUp() {
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write("test1,test2,test3,test4,test5\n");
			fr.write("\n");
			fr.write("hoge1,hoge2,hoge3,hoge4,hoge5\n");
			fr.write("\n");
			fr.flush();
			fr.close();
			FileWriter fr_error = new FileWriter(ERROR_FILE_PATH);
			fr_error.write("test1,test2,test3,test4,test5,test6\n");
			fr_error.write("hoge1,hoge2,hoge3,hoge4,hoge5\n");
			fr_error.flush();
			fr_error.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadCSVTable() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			List<String[]> list = MyCSVReader2.readCSVTable(reader, 5);
			String[] s1 = list.get(0);
			String[] s2 = list.get(1);
			assertEquals(s1[0], "test1");
			assertEquals(s1[1], "test2");
			assertEquals(s1[2], "test3");
			assertEquals(s1[3], "test4");
			assertEquals(s1[4], "test5");
			assertEquals(s2[0], "hoge1");
			assertEquals(s2[1], "hoge2");
			assertEquals(s2[2], "hoge3");
			assertEquals(s2[3], "hoge4");
			assertEquals(s2[4], "hoge5");
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test(expected = IOException.class)
	public void testCommaOver() {
		try {
			FileReader reader = new FileReader(ERROR_FILE_PATH);
			@SuppressWarnings("unused")
			List<String[]> list = MyCSVReader2.readCSVTable(reader, 5);
			fail();
		} catch (Exception e) {
		}
	}
}
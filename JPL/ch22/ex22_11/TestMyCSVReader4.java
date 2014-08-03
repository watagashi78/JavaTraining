package ex22_11;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestMyCSVReader4 {
	private static final String FILE_PATH = "test.csv";

	@Before
	public void setUp() {
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write("test1,test2,test3,test4,test5\n");
			fr.write("hoge1,hoge2,hoge3,hoge4,hoge5\n");
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadCSVTable() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			List<String[]> list = MyCSVReader4.readCSVTable(reader, 5);
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
}

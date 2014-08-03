package ex22_09;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

public class TestMyCSVReader3 extends TestCase {

	private static final String FILE_PATH_SHORT = "short_test.csv";
	private static final String FILE_PATH_LONG = "long_test.csv";
	private static final String[] PATTERNS = {
		"^(.*),(.*),(.*),(.*)",					// P569
		"([^,*].*),([^,*]*),([^,*]*),([^,*]*)",	// P286
		"^(.+?),(.+?),(.+?),(.+)",				// 最短一致
		"^(.+),(.+),(.+),(.+)"					// 最長一致
	};

	@Before
	public void setUp() {
		try {
			FileWriter fr_short = new FileWriter(FILE_PATH_SHORT);
			fr_short.write(createShortCSVString());
			fr_short.flush();
			fr_short.close();
			FileWriter fr_long = new FileWriter(FILE_PATH_LONG);
			fr_long.write(createLongCSVString());
			fr_long.flush();
			fr_long.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadCSVTable() {
		long test1, test2;
		for (String pattern : PATTERNS) {
			test1 = testReadShortCSVTable(pattern);
			test2 = testReadLongCSVTable(pattern);
			System.out.println("Pattern = [" + pattern + "]");
			System.out.println(" -> test1 = " + test1 + "ms, test2 = " + test2 + "ms");
		}
	}

	public long testReadShortCSVTable(String exp) {
		long start = System.currentTimeMillis();
		try {
			FileReader reader = new FileReader(FILE_PATH_SHORT);
			List<String[]> list = MyCSVReader3.readCSVTable(reader, exp);
			for (int i = 0; i < 100; i++) {
				String[] s = list.get(i);
				assertEquals(s[0], "a");
				assertEquals(s[1], "b");
				assertEquals(s[2], "c");
				assertEquals(s[3], "d");
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		long stop = System.currentTimeMillis();
		return stop - start;
	}

	public long testReadLongCSVTable(String exp) {
		Pattern a = Pattern.compile("a{1,10000}");
		Pattern b = Pattern.compile("b{1,10000}");
		Pattern c = Pattern.compile("c{1,10000}");
		Pattern d = Pattern.compile("d{1,10000}");

		long start = System.currentTimeMillis();
		try {
			FileReader reader = new FileReader(FILE_PATH_LONG);
			List<String[]> list = MyCSVReader3.readCSVTable(reader, exp);
			for (int i = 0; i < 100; i++) {
				String[] s = list.get(i);
				assertTrue(a.matcher(s[0]).find());
				assertTrue(b.matcher(s[1]).find());
				assertTrue(c.matcher(s[2]).find());
				assertTrue(d.matcher(s[3]).find());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			fail();
		}
		long stop = System.currentTimeMillis();
		return stop - start;
	}

	private String createShortCSVString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			sb.append("a,b,c,d\n");
		}
		return sb.toString();
	}

	private String createLongCSVString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 10000; j++) {
				sb.append("a");
			}
			sb.append(",");
			for (int j = 0; j < 10000; j++) {
				sb.append("b");
			}
			sb.append(",");
			for (int j = 0; j < 10000; j++) {
				sb.append("c");
			}
			sb.append(",");
			for (int j = 0; j < 10000; j++) {
				sb.append("d");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
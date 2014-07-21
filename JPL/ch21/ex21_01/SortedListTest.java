package ex21_01;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class SortedListTest {
	private static final String CRLF = System.getProperty("line.separator");
	private static final String FILE_PATH = "ch21/ex21_01/test.txt";
	private static final String SAMPLE_TEXT = "b:1" + CRLF + "d:2" + CRLF + "c:3" + CRLF + "a:4";

	@Before
	public void setUp() throws IOException {
		FileWriter fr = new FileWriter(FILE_PATH);
		fr.write(SAMPLE_TEXT);
		fr.flush();
		fr.close();
	}

	@Test
	public void test() throws IOException {
		SortedStringList list = new SortedStringList();
		int lineCount = SAMPLE_TEXT.split(CRLF).length;
		LineReader lr = new LineReader(new FileReader(FILE_PATH));
		String tmp;
		for (int i = 0; i < lineCount; i++) {
			while((tmp = lr.readLine()) != "") {
				list.add(tmp);
			}
		}
		assertEquals(4, list.size());
		assertEquals("a:4", list.get(0));
		assertEquals("b:1", list.get(1));
		assertEquals("c:3", list.get(2));
		assertEquals("d:2", list.get(3));
		lr.close();
	}

}

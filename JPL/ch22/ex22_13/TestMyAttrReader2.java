package ex22_13;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TestMyAttrReader2 {

	private static final String FILE_PATH = "test.txt";
	private static final String ERROR_FILE_PATH1 = "error_test1.txt";
	private static final String ERROR_FILE_PATH2 = "error_test2.txt";

	@Before
	public void setUp() {
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write("name=Hasegawa\n");
			fr.write("age=26\n");
			fr.write("Location=Kanazawa\n");
			fr.flush();
			fr.close();
			FileWriter er1 = new FileWriter(ERROR_FILE_PATH1);
			er1.write("hoge1==invalid\n");
			er1.flush();
			er1.close();
			FileWriter er2 = new FileWriter(ERROR_FILE_PATH2);
			er2.write("hoge2=invalid=\n");
			er2.flush();
			er2.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAttrTable() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			Attributed attrs = MyAttrReader2.readAttrs(reader);
			Iterator<Attr> it = attrs.attrs();
			Attr attr = it.next();
			assertEquals(attr.getName(), "age");
			assertEquals(attr.getValue(), "26");
			attr = it.next();
			assertEquals(attr.getName(), "name");
			assertEquals(attr.getValue(), "Hasegawa");
			attr = it.next();
			assertEquals(attr.getName(), "Location");
			assertEquals(attr.getValue(), "Kanazawa");
			assertFalse(it.hasNext());
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test(expected = IOException.class)
	public void testException1ReadAttrTable() throws IOException {
		FileReader reader = new FileReader(ERROR_FILE_PATH1);
		@SuppressWarnings("unused")
		Attributed attrs = MyAttrReader2.readAttrs(reader);
		fail();
	}

	@Test(expected = IOException.class)
	public void testException2ReadAttrTable() throws IOException {
		FileReader reader = new FileReader(ERROR_FILE_PATH2);
		@SuppressWarnings("unused")
		Attributed attrs = MyAttrReader2.readAttrs(reader);
		fail();
	}
}

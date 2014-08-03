package ex22_12;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

public class TestMyAttrReader {

	private static final String FILE_PATH = "test.txt";

	@Before
	public void setUp() {
		try {
			FileWriter fr = new FileWriter(FILE_PATH);
			fr.write("name=Hasegawa\n");
			fr.write("age=26\n");
			fr.write("Location=Kanazawa\n");
			fr.flush();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadAttrTable() {
		try {
			FileReader reader = new FileReader(FILE_PATH);
			Attributed attrs = MyAttrReader.readAttrs(reader);
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
}

package ex21_02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.WeakHashMap;

import org.junit.Before;
import org.junit.Test;

public class DataHandlerTest {

	@Before
	public void setUp() {
		File file = new File("ch21/ex21_02/test.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("test text");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void testReadFile() {
		DataHandler dh = new DataHandler();
		WeakHashMap<File, byte[]> whm;
		byte[] b;

		b = dh.readFile(new File("test.txt"));
		assertEquals(new String(b), "test text");

		Runtime.getRuntime().gc();
		whm = getLastFileReference(dh);
		b = whm.get(new File("test.txt"));
		assertEquals(b, null);

		b = dh.readFile(new File("test.txt"));
		assertEquals(new String(b), "test text");
	}

	@SuppressWarnings("unchecked")
	private WeakHashMap<File, byte[]> getLastFileReference(DataHandler dh) {
		Field f = null;
		try {
			f = dh.getClass().getDeclaredField("lastData");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		WeakHashMap<File, byte[]> whm = null;
		try {
			f.setAccessible(true);
			whm = (WeakHashMap<File, byte[]>) f.get(dh);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return whm;
	}
}
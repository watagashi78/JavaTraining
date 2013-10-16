package ex01_15;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Test;

public class MixedLookupTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testFind() {
		MixedLookup testClass = new MixedLookup("hase", 100);
		assertEquals(100, testClass.find("hase"));
		assertEquals(null, testClass.find("hoge"));
	}

	@Test
	public void testAdd() {
		MixedLookup testClass = new MixedLookup();
		testClass.add("hase", 100);
		assertEquals(100, testClass.find("hase"));
	}

	@Test
	public void testRemove() {
		MixedLookup testClass = new MixedLookup("hase", 100);
		testClass.remove("hase");
		assertEquals(null, testClass.find("hase"));
	}

}

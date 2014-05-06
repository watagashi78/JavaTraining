package ex21_04;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class ShortStringsTest {
	private static final int STRING_LENGTH = 3;
	private LinkedList<String> list;
	private ShortStrings ss;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
		list.add("a");
		list.add("bc");
		list.add("def");
		list.add("ghijk");
		list.add("lmn");
		ss = new ShortStrings(list.listIterator(), STRING_LENGTH);
	}

	@Test
	public void test() {
		assertEquals(-1, ss.previousIndex());
		assertEquals(0, ss.nextIndex());
		assertEquals("a", ss.next());
		assertEquals("bc", ss.next());
		assertEquals("def", ss.next());
		assertEquals("lmn", ss.next());
		assertEquals("lmn", ss.previous());
		assertEquals("def", ss.previous());
	}

}

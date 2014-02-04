package ex12_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLinkedList {

	@Test
	public void testAdd() {
		LinkedList<String> test = new LinkedList<String>();
		test.add("first");
		assertEquals("first", test.remove());
	}

	@Test
	public void testRemove() {
		LinkedList<String> test = new LinkedList<String>();
		test.add("first");
		assertEquals("first", test.remove());
	}

	@Test
	public void testFind() throws ObjectNotFoundException {
		LinkedList<String> test = new LinkedList<String>();
		test.add("first");
		test.add("second");
		test.add("third");
		assertEquals("first", test.find("first").getElement());
		assertEquals("second", test.find("second").getElement());
		assertEquals("third", test.find("third").getElement());
	}

	@Test(expected = ObjectNotFoundException.class)
	public void testNotFind() throws ObjectNotFoundException {
		LinkedList<String> test = new LinkedList<String>();
		test.add("first");
		test.add("second");
		test.add("third");
		test.find("four");
	}

}

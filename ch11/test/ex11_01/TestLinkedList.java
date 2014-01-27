package ex11_01;

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

}

package ex02_16;

import static org.junit.Assert.*;

import org.junit.Test;

import ex02_06.LinkedList;

public class LinkedListTest {

	@Test
	public void testGetSize() {
		LinkedList testList = new LinkedList("test");
		assertEquals(1, testList.getSize());
		testList.addLast("test2");
		assertEquals(2, testList.getSize());
	}

	@Test
	public void testAddFirst() {
		LinkedList testList = new LinkedList("test");
		testList.addFirst("test0");
		assertEquals("test0", testList.first.getNextNode().getObj());
		assertEquals("test", testList.first.getNextNode().getNextNode().getObj());
	}

	@Test
	public void testAddLast() {
		LinkedList testList = new LinkedList("test");
		testList.addLast("test2");
		assertEquals("test2", testList.last.getPrevNode().getObj());
		assertEquals("test", testList.last.getPrevNode().getPrevNode().getObj());
	}

}

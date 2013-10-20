package ex02_02;

import junit.framework.TestCase;

public class LinkedListTest extends TestCase {

	protected static void tearDownAfterClass() throws Exception {
	}

	public void testLinkedListObject() {
		LinkedList testList = new LinkedList("test");
		assertEquals("test", testList.getNode(1).getObj());
	}

	public void testGetNode() {
		LinkedList testList = new LinkedList("test");
		assertEquals("Start", testList.getNode(0).getObj());
		assertEquals("test", testList.getNode(1).getObj());
		assertEquals("Last", testList.getNode(2).getObj());
	}

	public void testGetSize() {
		LinkedList testList = new LinkedList("test");
		assertEquals(1, testList.getSize());
	}
}

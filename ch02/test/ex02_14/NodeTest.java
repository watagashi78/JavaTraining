package ex02_14;

import static org.junit.Assert.*;
import ex02_02.Node;

public class NodeTest {

	public void testGetObj() {
		Node testNode = new Node("test");
		assertEquals("test", testNode.getObj());
	}

	public void testSetGetNextNode() {
		Node testNode = new Node("test");
		Node nextNode = new Node("next");
		Node nextNode2 = new Node("next2");
		testNode.setNextNode(nextNode);
		testNode.getNextNode().setNextNode(nextNode2);
		assertEquals("next", testNode.getNextNode().getObj());
		assertEquals("next2", testNode.getNextNode().getNextNode().getObj());
	}

	public void testSetGetPrevNode() {
		Node testNode = new Node("test");
		Node prevNode = new Node("prev");
		testNode.setPrevNode(prevNode);
		assertEquals("prev", testNode.getPrevNode().getObj());
	}

	public void testClone() {
		Node testNode = new Node("test");
		Node prevNode = new Node("prev");
		Node nextNode = new Node("next");
		testNode.setNextNode(nextNode);
		testNode.setPrevNode(prevNode);

		Node cloneNode = testNode.clone();
		assertEquals("test", cloneNode.getObj());
		assertEquals("prev",cloneNode.getPrevNode().getObj());
		assertEquals("next",cloneNode.getNextNode().getObj());
	}

}

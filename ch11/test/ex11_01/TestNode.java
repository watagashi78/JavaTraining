package ex11_01;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestNode {

	@Test
	public void testNodeE() {
		Node<String> test = new Node<String>("Test");
		assertEquals("Test", test.getElement());
	}

	@Test
	public void testNodeENodeOfE() {
		Node<String> test2 = new Node<String>("Test2");
		Node<String> test1 = new Node<String>("Test1", test2);
		assertEquals("Test1", test1.getElement());
		assertEquals("Test2", test2.getElement());
		assertEquals("Test2", test1.getNext().getElement());
	}

	@Test
	public void testSetElement() {
		Node<String> test = new Node<String>("Test");
		test.setElement("Change");
		assertEquals("Change", test.getElement());
	}

	@Test
	public void testGetElement() {
		Node<String> test = new Node<String>("Test");
		assertEquals("Test", test.getElement());
	}

	@Test
	public void testSetNext() {
		Node<String> test1 = new Node<String>("Test1");
		Node<String> test2 = new Node<String>("Test2");
		test1.setNext(test2);
		assertEquals("Test2", test1.getNext().getElement());
	}

	@Test
	public void testGetNext() {
		Node<String> test1 = new Node<String>("Test1");
		Node<String> test2 = new Node<String>("Test2");
		test1.setNext(test2);
		assertEquals("Test2", test1.getNext().getElement());
	}

}

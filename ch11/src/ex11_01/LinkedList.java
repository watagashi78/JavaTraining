package ex11_01;

public class LinkedList<E> {
	private Node<E> head;
	private Node<E> tail;

	public void add(E item) {
		Node<E> node = new Node<E>(item);
		if(tail == null) {
			head = tail = node;
		} else {
			tail.setNext(node);
			tail = node;
		}
	}

	public E remove() {
		if (head == null)
			return null;
		Node<E> node = head;
		head = head.getNext();
		if (head == null)
			tail = null;
		return node.getElement();
	}
}

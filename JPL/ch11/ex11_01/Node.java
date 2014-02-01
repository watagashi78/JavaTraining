package ex11_01;

public class Node<E> {
	private Node<E> next;
	private E element;

	public Node(E element) {
		this.element = element;
	}

	public Node(E element, Node<E> next) {
		this.element = element;
		this.next = next;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public E getElement() {
		return element;
	}

	public void setNext(Node<E> next) {
		this.next = next;
	}

	public Node<E> getNext() {
		return next;
	}
}

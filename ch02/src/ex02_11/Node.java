package ex02_11;


public class Node implements Cloneable {
	private Object obj;
	private Node prevNode;
	private Node nextNode;

	public Node(Object obj) {
		this.obj = obj;
		this.prevNode = null;
		this.nextNode = null;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void setNextNode(Node next) {
		this.nextNode = next;
	}

	public void setPrevNode(Node prev) {
		this.prevNode = prev;
	}

	public Node getNextNode() {
		return nextNode;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public Node clone() {
		Node cloneNode;
		try {
			cloneNode = (Node) super.clone();
		} catch (CloneNotSupportedException ce) {
			throw new RuntimeException();
		}
		cloneNode.setObj(this.obj);
		cloneNode.setNextNode(this.nextNode);
		cloneNode.setPrevNode(this.prevNode);

		return cloneNode;
	}
}

package ex02_02;

public class LinkedList {
	private int size = 0;
	private Node first = new Node("Start");
	private Node last = new Node("Last");

	public LinkedList() {
		first.setPrevNode(null);	// 全てnullで初期化
		first.setNextNode(null);
		last.setPrevNode(null);
		last.setNextNode(null);
	}

	public LinkedList(Object obj) {
		Node node = new Node(obj);

		first.setPrevNode(null);	// 先端、終端のnull
		last.setNextNode(null);

		first.setNextNode(node);	// first→node
		node.setPrevNode(first);

		node.setNextNode(last);		// node→last
		last.setPrevNode(node);

		this.size++;
	}

	/**
	 * index番目のNodeのゲッター.
	 * @param index 0から始まるnodeリストのindex番目
	 * @return
	 */
	public Node getNode(int index) {
		Node currentNode = first.clone();
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.getNextNode().clone();
		}
		return currentNode;
	}

	/**
	 * リストのサイズのゲッター(最小は1).
	 * @return
	 */
	public int getSize() {
		return size;
	}


	public static void main(String[] args) {}
}
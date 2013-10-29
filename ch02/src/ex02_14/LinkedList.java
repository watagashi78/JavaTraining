package ex02_14;

public class LinkedList {
	private int size = 0;
	private Node first = new Node("First");
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
	 * リストのサイズのゲッター(最小は1).
	 * @return
	 */
	public int getSize() {
		return size;
	}

	/**
	 * first→<addNode>→Node.
	 * @param obj
	 */
	public void addFirst(Object obj) {
		Node addNode = new Node(obj);

		addNode.setNextNode(first.getNextNode());	// addNode→Node
		first.getNextNode().setPrevNode(addNode);

		first.setNextNode(addNode);		// first→addNode
		addNode.setPrevNode(first);

		this.size++;
	}

	/**
	 * Node→<addNode>→last.
	 * @param obj
	 */
	public void addLast(Object obj) {
		Node addNode = new Node(obj);

		last.getPrevNode().setNextNode(addNode);	// Node→addNode
		addNode.setPrevNode(last.getPrevNode());

		addNode.setNextNode(last);		// addNode→last
		last.setPrevNode(addNode);

		this.size++;
	}

	/**
	 * ObjectクラスのtoStringをオーバーライド.
	 * @return 文字列配列
	 */
	public String toString() {
		String outputString = "First";
		Node tmpNode = first.clone();
		for (int i = 0; i < size; i++) {
			tmpNode = tmpNode.getNextNode();
			outputString = outputString + "->" + tmpNode.getObj().toString();
		}
		outputString = outputString + "->Last";
		return outputString;
	}
}
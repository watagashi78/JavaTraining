package ex19_01;

/**
 * LinkedList Class.
 *
 * @author Takashi Hasegawa
 */
public class LinkedList {
	private int size = 0;
	private Node first = new Node("First");
	private Node last = new Node("Last");

	/**
	 * Create a new empty LinkedList.
	 *
	 */
	public LinkedList() {
		first.setPrevNode(null);	// 全てnullで初期化
		first.setNextNode(null);
		last.setPrevNode(null);
		last.setNextNode(null);
	}

	/**
	 * Create a new LinkedList with first Node.
	 * @param obj First Node
	 */
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
	 * Get size of LinkedList.
	 * @return Size of LinkedList
	 */
	public int getSize() {
		return size;
	}

	/**
	 * Add node in the head of LinkedList.
	 * first→<addNode>→Node...→last..
	 * @param obj Node Object
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
	 * Add node in the tail of LinkedList.
	 * first→Node→<addNode>→last.
	 * @param obj Node Object
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
	 * Returns Nodes List.
	 * Like: Node1 -> Node2 -> Node3 -> Node4
	 * @return 文字列配列
	 */
	@Override
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
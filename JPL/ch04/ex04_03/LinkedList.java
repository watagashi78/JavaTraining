package ex04_03;

interface LinkedList {

	int getSize();
	void addFirst(Object obj);
	void addLast(Object obj);
	String toString();
	LinkedList clone();
}
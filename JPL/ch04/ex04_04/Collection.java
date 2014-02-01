package ex04_04;

public interface Collection {
	boolean add(Object o);
	void clear();
	boolean contains(Object o);
	boolean equals(Object o);
	int hashCode();
	boolean isEmpty();
	boolean remove(Object o);
	int size();
	Object[] toArray();
}

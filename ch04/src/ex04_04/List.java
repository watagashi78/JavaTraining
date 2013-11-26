package ex04_04;

public interface List extends Collection {
	Object get();
	int indexOf(Object o);
	void set(int index, Object o);
}

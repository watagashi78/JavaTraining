package ex04_04;

public interface Queue extends Collection {
	Object element();
	boolean offer(Object o);
	Object peek();
	Object poll();
	Object remove();
}

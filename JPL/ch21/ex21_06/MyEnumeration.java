package ex21_06;

import java.util.Collection;
import java.util.Enumeration;

public class MyEnumeration<E> implements Enumeration<E> {
	private Object[] array;
	private int offset = 0;

	public MyEnumeration(Collection<E> collection) {
		array = collection.toArray();
	}

	@Override
	public boolean hasMoreElements() {
		return offset < array.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E nextElement() {
		if (!hasMoreElements()) {
			offset = 0;
			return null;
		} else {
			return (E) array[offset++];
		}
	}

}

package ex21_05;

import java.util.AbstractList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class ArrayBunchList<E> extends AbstractList<E> {
	private final E[][] arrays;
	private final int size;

	public ArrayBunchList(E[][] arrays) {
		this.arrays = arrays.clone();
		int s = 0;
		for (E[] array : arrays)
			s += array.length;
		size = s;
	}

	public int size() {
		return size;
	}

	public E get(int index) {
		int off = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (index < off + arrays[i].length)
				return arrays[i][index - off];
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public E set(int index, E value) {
		int off = 0;
		for (int i = 0; i < arrays.length; ++i) {
			if (index < off + arrays[i].length) {
				E ret = arrays[i][index - off];
				arrays[i][index - off] = value;
				return ret;
			}
			off += arrays[i].length;
		}
		throw new ArrayIndexOutOfBoundsException(index);
	}

	public ListIterator<E> listIterator() {
		return new ABLListIterator();
	}

	private class ABLListIterator implements ListIterator<E> {
		protected int off = 0;
		protected int array = 0;
		protected int pos = 0;
		private boolean isSet = false;

		public boolean hasNext() {
			return off + pos < size();
		}

		public boolean hasPrevious() {
			return 0 < pos + off;
		}

		public E next() {
			if (!hasNext())
				throw new NoSuchElementException();
			E ret = arrays[array][pos++];
			while (pos >= arrays[array].length) {
				off += arrays[array++].length;
				pos = 0;
				if (array >= arrays.length)
					break;
			}
			isSet = true;
			return ret;
		}

		public E previous() {
			if (!hasPrevious())
				throw new NoSuchElementException();
			pos--;
			while (pos < 0) {
				off -= arrays[--array].length;
				pos = arrays[array].length - 1;
				if (array < 0)
					break;
			}
			isSet = true;
			E ret = arrays[array][pos];
			return ret;
		}

		public int nextIndex() {
			return hasNext() ? (off + pos) : size();
		}

		public int previousIndex() {
			return hasPrevious() ? (off + pos - 1) : -1;
		}

		public void set(E e) {
			if (!isSet) throw new IllegalStateException();
			arrays[array][pos] = e;
		}

		public void add(E e) {
			throw new UnsupportedOperationException();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}

package ex21_04;

import java.util.ListIterator;
import java.util.NoSuchElementException;

// Iteratorインタフェースの傘下に同じ役割のクラスが無意味に複数できてしまうので,
// スーパークラスのメンバをprotectedにして継承すべきだと思います。
public class ShortStrings extends TextShortStrings implements ListIterator<String> {
	private ListIterator<String> strings;
	private String prevShort;

	public ShortStrings(ListIterator<String> strings, int maxLen) {
		super(strings, maxLen);
		this.strings = strings;
		this.prevShort = null;
	}

	public boolean hasPrevious() {
		if (prevShort != null)
			return true;
		while (strings.hasPrevious()) {
			prevShort = strings.previous();
			if (prevShort.length() <= maxLen)
				return true;
		}
		prevShort = null;
		return false;
	}

	public String previous() {
		if (prevShort == null && !hasPrevious())
			throw new NoSuchElementException();
		String n = prevShort;
		prevShort = null;
		return n;
	}

	public int nextIndex() {
		return strings.nextIndex();
	}

	public int previousIndex() {
		return strings.previousIndex();
	}

	public void add(String arg0) {
		throw new UnsupportedOperationException();
	}

	public void set(String arg0) {
		throw new UnsupportedOperationException();
	}
}
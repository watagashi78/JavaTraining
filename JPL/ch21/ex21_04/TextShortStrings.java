package ex21_04;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TextShortStrings implements Iterator<String> {
	protected Iterator<String> strings;
	protected String nextShort;
	protected final int maxLen;

	public TextShortStrings(Iterator<String> strings, int maxLen) {
		this.strings = strings;
		this.maxLen = maxLen;
		nextShort = null;
	}

	public boolean hasNext() {
		if (nextShort != null)
			return true;
		while (strings.hasNext()) {
			nextShort = strings.next();
			if (nextShort.length() <= maxLen)
				return true;
		}
		nextShort = null;
		return false;
	}

	public String next() {
		if (nextShort == null & !hasNext())
			throw new NoSuchElementException();
		String n = nextShort;
		nextShort = null;
		return n;
	}

	public void remove() {
		throw new UnsupportedOperationException();
	}
}

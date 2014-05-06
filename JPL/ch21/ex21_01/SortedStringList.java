package ex21_01;

import java.util.LinkedList;
import java.util.ListIterator;

public class SortedStringList extends LinkedList<Object>{
	public SortedStringList() {
		super();
	}

	public void add(String str) {
		ListIterator<Object> it = listIterator();
		for (int i = 0; it.hasNext(); i++) {
			String tmp = (String) it.next();
			if(str.compareTo(tmp) < 0) {
				add(i, str);
				return;
			}
		}
		addLast(str);
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		ListIterator<Object> it = listIterator();
		while(it.hasNext()) {
			sb.append(it.next() + "\n");
		}
		return sb.toString();
	}
}

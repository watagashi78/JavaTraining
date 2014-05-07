package ex22_02;

import java.util.HashSet;

public class WhichChars {
	private HashSet<Character> usedSet = new HashSet<Character>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			usedSet.add(str.charAt(i));
		}
	}

	public String toString() {
		String desc = "[";
		for (Character c : usedSet) {
			desc += c;
		}
		return desc + "]";
	}
}

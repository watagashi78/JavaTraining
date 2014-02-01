package ex13_02;

public class StrCount {
	public int count(String input, String check) {
		int count = -1, index = -1;
		do {
			count++;
			index = input.indexOf(check, index + 1);
		}
		while (index != -1);
		return count;
	}
}
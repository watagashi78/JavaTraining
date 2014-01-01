package ex10_05;

public class Between {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		showBetween('A', 'Z');
	}

	public static void showBetween(char a, char b) {
		int first,last;
		if (a < b) {
			first = (int) a;
			last = (int) b;
		} else {
			first = (int) b;
			last = (int) a;
		}

		for (int i = first + 1; i < last; i++) {
			System.out.print((char) i);
		}
	}

}

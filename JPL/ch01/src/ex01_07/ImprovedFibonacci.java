package ex01_07;

public class ImprovedFibonacci {

	static final int MAX_INDEX = 9;
	static final int MIN_INDEX = 1;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の
	 * 最初のほうの要素を表示する
	 * ただし要素番号iを逆順で表示する
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1, hi = 1;
		String mark;

		System.out.println(MAX_INDEX + ": " + lo);
		for (int i = MAX_INDEX - 1; i >= MIN_INDEX; i--) {
			if (hi % 2 == 0)
				mark = " *";
			else
				mark = "";
			System.out.println(i + ": " + hi + mark);
			hi = lo + hi;
			lo = hi - lo;
		}
	}

}

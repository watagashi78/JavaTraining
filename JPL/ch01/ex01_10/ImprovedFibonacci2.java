package ex01_10;

public class ImprovedFibonacci2 {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の最初のほうの要素を表示する
	 * ただし数列の値とその値が偶数かどうかを示すブール値を保持するクラスを作成し,
	 * そのクラスのオブジェクトへの参照を配列として持つようにせよ.
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1, hi = 1;
		String mark;
		CheckFibonacci[] checkFibonacci = new CheckFibonacci[MAX_INDEX];	// CheckFibonacciクラスの配列

		/* フィボナッチ数列の配列への格納 */
		checkFibonacci[0] = new CheckFibonacci(lo);
		for (int i = 1; i < MAX_INDEX; i++) {
			checkFibonacci[i] = new CheckFibonacci(hi);
			hi = lo + hi;
			lo = hi - lo;
		}

		/* 格納した配列の表示（フィボナッチ数列と偶数判定フラグはオブジェクト参照） */
		for (int i = 0; i < MAX_INDEX; i++) {
			if (checkFibonacci[i].even) {
				mark = " *";	// 偶数(true)であれば'*'
			} else {
				mark = "";
			}
			System.out.println((i+1) + ": " + checkFibonacci[i].fib + mark);
		}
	}

}

package ex01_12;

public class ImprovedFibonacci3 {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'*'をつけて、フィボナッチ数列の最初のほうの要素を表示する
	 * ただしprintlnで直接表示するのではなくStringオブジェクトを作成して配列に入れて
	 * 配列を表示せよ.
	 * @param args
	 */
	public static void main(String[] args) {
		int lo = 1, hi = 1;
		String[] aryString = new String[MAX_INDEX];

		/* フィボナッチ数列の出力文字列の配列へ格納 */
		aryString[0] = "1: " + lo;
		for (int i = 1; i < MAX_INDEX; i++) {
			if (hi % 2 == 0)
				aryString[i] = (i+1) + ": " + hi + " *";
			else
				aryString[i] = (i+1) + ": " + hi;
			hi = lo + hi;
			lo = hi - lo;
		}

		/* 格納した配列を表示 */
		for (int i = 0; i < MAX_INDEX; i++) {
			System.out.println(aryString[i]);
		}
	}

}
package ex01_09;


public class ArrayFibonacci {

	static final int MAX_NUM = 50;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** 値がMAX未満のフィボナッチ数列を配列に保存し、表示する */
		int lo = 1, hi = 1;
		int i = 0; // ループカウント用
		int imax = 0; //
		int[] aryFibonacci = new int[10]; // 配列サイズ決めうち

		System.out.println("値が" + MAX_NUM + "未満のフィボナッチ数列(配列)");
		System.out.println(lo);
		while (hi < MAX_NUM) {
			aryFibonacci[i] = hi;
			;
			hi = lo + hi; // 新しいhi
			lo = hi - lo; // 古いlo
			i++;
		}
		imax = i;

		for (i = 0; i < imax; i++) {
			System.out.println(aryFibonacci[i]);
		}
	}
}

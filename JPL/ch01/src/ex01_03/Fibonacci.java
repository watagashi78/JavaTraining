package ex01_03;

public class Fibonacci {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/** 値が50未満のフィボナッチ数列をタイトル付きで表示する */
		System.out.println("値が50未満のフィボナッチ数列");
		int lo = 1, hi = 1;
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しいhi
			lo = hi - lo; // 新しいloは、（合計 - 古いlo）すなわち、古いlo
		}
	}

}
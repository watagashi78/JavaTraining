package ex01_06;

public class ChangedFibonacci {

	/**
	 * @param args
	 */
	static final String TITLE = "値が50未満のフィボナッチ数列";
	public static void main(String[] args) {
		/** 値が50未満のフィボナッチ数列をタイトル付きで表示する */
		System.out.println(TITLE);
		int lo = 1, hi = 1;
		System.out.println(lo);
		while (hi < 50) {
			System.out.println(hi);
			hi = lo + hi; // 新しいhi
			lo = hi - lo; // 新しいloは、（合計 - 古いlo）すなわち、古いlo
		}
	}

}

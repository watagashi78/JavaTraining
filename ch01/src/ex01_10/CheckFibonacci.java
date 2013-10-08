package ex01_10;

public class CheckFibonacci {

	public int fib;			// フィボナッチ数
	public boolean even;	// 偶数ならtrue,奇数ならfalse

	/**
	 * デフォルトコンストラクタは認めないのでprotectedで禁止.
	 */
	protected CheckFibonacci() {}

	/**
	 * CheckFibonacciコンストラクタ.
	 * @param num フィボナッチ数
	 */
	public CheckFibonacci(int num) {
		this.fib = num;
		if (num % 2 == 0) {
			this.even = true;
		} else {
			this.even = false;
		}
	}
}

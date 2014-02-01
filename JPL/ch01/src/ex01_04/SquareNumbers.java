package ex01_04;

public class SquareNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		/** 50以下の整数の平方数を表示する */
		System.out.println("50以下の整数の平方数");
		int number = 1, square = 0;
		while (number <= 50) {
			square = number * number;
			System.out.println(number + ":" + square);
			number++;
		}

	}

}

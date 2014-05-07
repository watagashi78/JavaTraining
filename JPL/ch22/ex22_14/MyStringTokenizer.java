package ex22_14;

import java.util.StringTokenizer;

public class MyStringTokenizer {
	public static void main(String[] args) {
		String str = "2.8 6.7 2.3 -10.1 5.0 6.7 0.9 2.9 4.1 -10.0";
		System.out.printf("%.1f", getSum(str));
	}

	public static double getSum(String str) {
		double sum = 0;
		StringTokenizer st = new StringTokenizer(str);
		while (st.hasMoreTokens()) {
			sum += Double.parseDouble(st.nextToken());
		}
		return sum;
	}
}

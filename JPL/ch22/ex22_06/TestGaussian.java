package ex22_06;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

public class TestGaussian {
	private static final int TRY_COUNT = 20000;
	private HashMap<Double, String> result = new HashMap<Double, String>();

	public TestGaussian(int count) {
		run_test(count);
	}

	public static void main(String[] args) {
		TestGaussian test = new TestGaussian(TRY_COUNT);
		test.printResult();
	}

	public void printResult() {
		Set<Double> keySet = result.keySet();
		Double[] keys = new Double[keySet.size()];
		int index = 0;
		for (Double d : keySet) {
			keys[index] = d;
			index++;
		}
		Arrays.sort(keys);
		for (int i = 0; i < keys.length; i++) {
			if (keys[i].compareTo(0.0) < 0) {
				System.out.printf("%.2f: %s\n", keys[i], result.get(keys[i]));
			} else {
				System.out.printf(" %.2f: %s\n", keys[i], result.get(keys[i]));
			}
		}
	}

	private void run_test(int count) {
		Random r = new Random();
		for(int i = 0; i < count; i++) {
			BigDecimal bd = new BigDecimal(r.nextGaussian());
			Double d = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			if (result.containsKey(d)) {
				result.put(d, result.get(d) + "*");
			} else {
				result.put(d, "*");
			}
		}
	}
}

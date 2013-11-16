package ex03_05;

public class MethodBenchmark extends Benchmark {

	@Override
	void benchmark() {

	}

	public final long loopTime(int loopcount) {
		long start = System.nanoTime();
		for (int i = 0; i < loopcount; i++);
		return (System.nanoTime() - start);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int count = Integer.parseInt(args[0]);
		long time = new MethodBenchmark().repeat(count);
		System.out.println(count + " Methods in " + time + "[ns]");
		time = new MethodBenchmark().loopTime(count);
		System.out.println(count + " Loops in " + time + "[ns]");
	}

}

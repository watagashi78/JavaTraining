package ex03_12;

public class SortHarness {
	private Object[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * 全ソートするために呼び出される.
	 * @param data
	 * @return
	 */
	public final SortMetrics sort(Object[] data) {
		values = data;
		curMetrics.init();
		doSort();
		return curMetrics.clone();
	}

	protected final int getDataLength() {
		return values.length;
	}

	protected final Object probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * オブジェクトのHashCodeでソート.
	 * @param obj1
	 * @param obj2
	 * @return
	 */
	public int compare(Object obj1, Object obj2) {
		return obj1.hashCode() - obj2.hashCode();
	}

	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	protected void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if(compare(values[i], values[j]) > 0) {
					swap(i, j);
				}
			}
		}
	}


}

package ex04_02;

public class SortObject implements SortHarness {
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

	public final int getDataLength() {
		return values.length;
	}

	public final Object probe(int i) {
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

	public final void swap(int i, int j) {
		curMetrics.swapCnt++;
		Object tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	public void doSort() {
		for (int i = 0; i < getDataLength(); i++) {
			for (int j = i + 1; j < getDataLength(); j++) {
				if(compare(values[i], values[j]) > 0) {
					swap(i, j);
				}
			}
		}
	}
}

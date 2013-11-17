package ex03_11;

public abstract class SortDouble {
	private double[] values;
	private final SortMetrics curMetrics = new SortMetrics();

	/**
	 * 全ソートするために呼び出される.
	 * @param data
	 * @return
	 */
	public final SortMetrics sort(double[] data) {
		values = data;
		curMetrics.init();
		doSort();
		return curMetrics.clone();	// ゲッターを削除し, 直接cloneする
	}

	/*
	 * cloneされたSortMetricsがgetされ、clone先のMetricsの値が改変されることで、
	 * 元のMetricsも同時に改変されるのを防ぐためにゲッターを削除.
	 *
	public final SortMetrics getMetrics() {
		return curMetrics.clone();
	}
	*/

	/**
	 * 拡張したクラスが要素の数を知るため.
	 * @return
	 */
	protected final int getDataLength() {
		return values.length;
	}

	/**
	 * 拡張したクラスが要素を調べるため.
	 * @param i
	 * @return
	 */
	protected final double probe(int i) {
		curMetrics.probeCnt++;
		return values[i];
	}

	/**
	 * 拡張したクラスが要素を比較するため.
	 * @param i
	 * @param j
	 * @return
	 */
	protected final int compare(int i, int j) {
		curMetrics.compareCnt++;
		double d1 = values[i];
		double d2 = values[j];
		if (d1 == d2)
			return 0;
		else
			return (d1 < d2 ? -1 : 1);
	}

	/**
	 * 拡張したクラスが要素を交換するため.
	 * @param i
	 * @param j
	 */
	protected final void swap(int i, int j) {
		curMetrics.swapCnt++;
		double tmp = values[i];
		values[i] = values[j];
		values[j] = tmp;
	}

	/**
	 * 拡張したクラスが実践する -- ソートするのに使用される.
	 */
	protected abstract void doSort();
}

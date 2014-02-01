package ex04_02;

interface SortHarness {

	SortMetrics sort(Object[] data);
	int getDataLength();
	Object probe(int i);
	int compare(Object obj1, Object obj2);
	void swap(int i, int j);
	void doSort();
}

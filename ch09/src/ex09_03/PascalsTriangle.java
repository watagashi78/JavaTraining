package ex09_03;

public class PascalsTriangle {
	private static final int DEPTH = 12;

	public static void main(String[] args) {
		int[][] result;
		PascalsTriangle pascal = new PascalsTriangle();
		result = pascal.calc(DEPTH);
		pascal.showPascal(result);
	}

	public int[][] calc(int depth) {
		int[][] arr = new int[depth][depth];
		for (int i = 0; i < depth; i++) {
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == depth - 1) {
					arr[i][j] = 1;
				} else {
					arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
				}
			}
		}
		return arr;
	}

	public void showPascal(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print("[" + arr[i][j] + "]");
			}
			System.out.println("");
		}
	}

}

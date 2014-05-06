package ex22_01;

import java.util.Formatter;


public class MyFormatter {
	private static final int MAX_ROW_CHAR = 80;
	private static final int MARGIN = 3; // " 0."
	private Formatter formatter;

	public MyFormatter() {
		formatter = new Formatter(System.out);
	}

	public void format(double[] data, int column) {
		String result;
		int width = MAX_ROW_CHAR / column;
		result = "% ."+ (width - MARGIN) + "f";
		for(int i = 0; i < data.length; i++){
			formatter.format(result, data[i]);
			if(i % column == (column - 1)){
				System.out.println();
			}
		}
	}

}

package ex20_04;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

public class LineReader extends FilterReader {
	public LineReader(Reader in) {
		super(in);
	}

	public int[] readLine() throws IOException {
		ArrayList<Integer> input = new ArrayList<Integer>();
		int i;
		while ((i = read()) != -1 && i != '\n' && i != '\r') {
			input.add(i);
		}
		int[] result = new int[input.size()];
		for (int j = 0; j < input.size(); j++) {
			result[j] = input.get(j);
		}
		return result;
	}
}

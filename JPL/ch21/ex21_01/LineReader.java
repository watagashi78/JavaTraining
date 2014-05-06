package ex21_01;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;

public class LineReader extends FilterReader {
	public LineReader(Reader in) {
		super(in);
	}

	public String readLine() throws IOException {
		String input = "";
		int i;
		while ((i = read()) != -1 && i != '\n' && i != '\r') {
			input += (char)i;
		}
		return input;
	}
}

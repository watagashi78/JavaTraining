package ex22_11;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class MyCSVReader4 {
	public static List<String[]> readCSVTable(Reader source, int cell_num) throws IOException {
		StreamTokenizer st = new StreamTokenizer(source);
		st.whitespaceChars(',', ',');	// Set delimiter
		List<String[]> vals = new ArrayList<String[]>();
		String[] cells = new String[cell_num];

		int i = 0;
		int lineNum = 1;
		while ((st.nextToken()) != StreamTokenizer.TT_EOF) {
			if (lineNum != st.lineno()) {		// New Line
				vals.add(cells);
				lineNum = st.lineno();
				cells = new String[cell_num];	// Reset
				cells[0] = st.sval;
				i = 1;
			} else {
				cells[i] = st.sval;
				i++;
			}
		}
		vals.add(cells);
		return vals;
	}
}

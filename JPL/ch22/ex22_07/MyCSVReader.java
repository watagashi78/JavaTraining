package ex22_07;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MyCSVReader {
	private static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r\u2028\u2029\u0085]";
	public static List<String> readCSV(Readable source) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		in.useDelimiter(",|" + LINE_SEPARATOR_PATTERN);
		List<String> vals = new ArrayList<String>();

		while (in.hasNext())
			vals.add(in.next());

		IOException ex = in.ioException();
		if (ex != null)
			throw ex;
		return vals;
	}

	public static List<String[]> readCSVTable(Readable source, int cell_num) throws IOException {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^(.*)";
		for (int i = 1; i < cell_num; i++) {
			exp += ",(.*)";
		}
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[cell_num];
				MatchResult match = in.match();
				for (int i = 0; i < cell_num; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine();
			} else {
				throw new IOException("input format error");
			}
		}
		IOException ex = in.ioException();
		if (ex != null)
			throw ex;
		return vals;
	}

}

package ex22_08;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MyCSVReader2 {
	private static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r\u2028\u2029\u0085]";
	private static final int CELLS = 4;

	public static List<String> readCSV(Readable source) throws IOException {
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
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String exp = "^(.*)";
		for (int i = 1; i < cell_num; i++) {
			exp += ",(.*)";
		}
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line == null) {
				String nextLine = in.nextLine();
				if (nextLine.equals(""))
					continue;
				else
					throw new IOException("input format error");
			} else {
				if (line.split(",").length != cell_num) {
					throw new IOException("input format error");
				}
				String[] cells = new String[cell_num];
				MatchResult match = in.match();
				for (int i = 0; i < cell_num; i++)
					cells[i] = match.group(i + 1);
				vals.add(cells);
				in.nextLine();
			}
		}
		IOException ex = in.ioException();
		if (ex != null)
			throw ex;
		return vals;
	}

}

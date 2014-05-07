package ex22_10;

import java.io.IOException;
import java.util.Scanner;

public class CSVScanner {
	public static void readCSV(Readable source) throws IOException {
		Scanner in = new Scanner(source);
		in.useDelimiter("\\s|#.*");
		while (in.hasNext()) {
			String str = in.next();
			if (!str.equals(""))
				System.out.println(str);
		}
		in.close();
	}
}

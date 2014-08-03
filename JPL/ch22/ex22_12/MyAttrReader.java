package ex22_12;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class MyAttrReader {
	public static Attributed readAttrs(Reader source) throws IOException {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		String exp = "^(.*)\\=(.*)";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);

		while (in.hasNextLine()) {
			in.findInLine(pat);
			MatchResult match = in.match();
			attrs.add(new Attr(match.group(1), match.group(2)));
			in.nextLine();
		}

		return attrs;
	}
}

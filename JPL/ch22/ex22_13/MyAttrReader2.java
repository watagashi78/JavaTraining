package ex22_13;

import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class MyAttrReader2 {
	public static Attributed readAttrs(Reader source) throws IOException {
		Scanner in = new Scanner(source);
		AttributedImpl attrs = new AttributedImpl();
		Attr attr = null;
		in.useDelimiter("=");

		while(in.hasNext()) {
			if (attr != null) {
				String value = in.next();
				if (value.contains("="))
					throw new IOException("mis placed '='");
				attr.setValue(value);
				attrs.add(attr);
				attr = null;
				in.nextLine();
				in.useDelimiter("=");
			} else {
				String name = in.next();
				if (name.contains("="))
					throw new IOException("mis placed '='");
				attr = new Attr(name);
				in.skip("=");
				in.useDelimiter("\n");
			}
		}
		return attrs;
	}
}

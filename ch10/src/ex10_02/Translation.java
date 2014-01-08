package ex10_02;

public class Translation {

	public static void main(String[] args) {
		System.out.println(translate(args[0]));
	}

	public static String translate(String str) {
		String result = "";

		for(int i = 0 ; i < str.length(); i++) {
			switch(str.charAt(i)) {
				case '\n':
					result += "\\n";
					break;
				case '\t':
					result += "\\r";
					break;
				case '\b':
					result += "\\b";
					break;
				case '\r':
					result += "\\r";
					break;
				case '\f':
					result += "\\f";
					break;
				case '\\':
					result += "\\\\";
					break;
				case '\'':
					result += "\\\'";
					break;
				case '\"':
					result += "\\\"";
					break;
				default:
					result += str.charAt(i);
					break;
			}
		}
		return result;
	}
}

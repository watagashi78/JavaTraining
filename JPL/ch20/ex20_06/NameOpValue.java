package ex20_06;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;

public class NameOpValue {
	private Name name;
	private Option op;

	private enum Name {
		ONE, TWO, THREE;

		double result = 0.0;
		void calc(Option op, double value) {
			switch (op) {
			case PLUS:
				result += value;
				break;
			case MINUS:
				result -= value;
				break;
			case EQUAL:
				result = value;
				break;
			default:
				System.out.println("Invalid option: " + op);
				break;
			}
		}
	}
	private enum Option {
		PLUS, MINUS, EQUAL;
	}

	public void calc(Reader source) throws IOException {
		StreamTokenizer in = new StreamTokenizer(source);

		while (in.nextToken() != StreamTokenizer.TT_EOF) {
			if (in.sval.equals("One")) {
				name = Name.ONE;
			} else if (in.sval.equals("Two")) {
				name = Name.TWO;
			} else if (in.sval.equals("Three")) {
				name = Name.THREE;
			} else {
				throw new IllegalArgumentException("Nameは One, Two, Three のいずれかを入力してください");
			}
			in.wordChars('+', '+');
			in.ordinaryChar('-');
			in.wordChars('-', '-');
			in.wordChars('=', '=');
			in.nextToken();
			if (in.sval.equals("+")) {
				op = Option.PLUS;
			} else if (in.sval.equals("-")) {
				op = Option.MINUS;
			} else if (in.sval.equals("=")) {
				op = Option.EQUAL;
			} else {
				throw new IllegalArgumentException("Optionは +, -, = のいずれかを入力してください");
			}

			in.parseNumbers();
			if (in.nextToken() != StreamTokenizer.TT_NUMBER) {
				throw new IllegalArgumentException("Valueは数値を入力してください");
			}
			name.calc(op, in.nval);
		}
	}

	public double getResult(String name) {
		if (name.equals("One")) {
			return Name.ONE.result;
		} else if (name.equals("Two")) {
			return Name.TWO.result;
		} else if (name.equals("Three")) {
			return Name.THREE.result;
		} else {
			throw new IllegalArgumentException("Invalid Name: " + name);
		}
	}
}

package ex20_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TranslateByte {

	public static void main(String[] args) throws IOException {
		byte from = 'a';
		byte to = 'A';
		translate(System.in, System.out, from, to);
	}

	public static void translate(InputStream in, OutputStream out, byte from, byte to)
			throws IOException {
		int b;
		while ((b = System.in.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

}

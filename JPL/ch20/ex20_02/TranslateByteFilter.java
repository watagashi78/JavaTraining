package ex20_02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class TranslateByteFilter extends FilterReader {
	private InputStreamReader reader;

	public TranslateByteFilter(InputStreamReader in) {
		super(in);
		this.reader = in;
	}

	public static void main(String[] args) throws IOException {
		byte from = 'a';
		byte to = 'A';
		InputStreamReader isr = new InputStreamReader(System.in);
		TranslateByteFilter tbf = new TranslateByteFilter(isr);
		tbf.translate(System.out, from, to);
		tbf.close();
	}

	public void translate(OutputStream out, byte from, byte to)
			throws IOException {
		int b;
		while ((b = reader.read()) != -1) {
			out.write(b == from ? to : b);
		}
	}

}

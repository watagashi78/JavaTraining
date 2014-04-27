package ex20_03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class EncryptOutputStream extends FilterOutputStream {
	private static final byte KEY = 85; // 1010101

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}

	@Override
	public void write(int b) throws IOException {
		super.write(b ^ KEY);
	}

	@Override
	public void write(byte[] buf) throws IOException {
		for (byte b : buf) {
			super.write(b ^ KEY);
		}
	}
}

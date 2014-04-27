package ex20_03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream {
	private static final byte KEY = 85; // 1010101

	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	@Override
	public int read() throws IOException {
		int b = super.read();
		return b ^ KEY;
	}

	@Override
	public int read(byte[] bytes) throws IOException {
		return read(bytes, 0, bytes.length);
	}

	@Override
    public int read(byte[] bytes, int offset, int count) throws IOException {
        int result = super.read(bytes, offset, count);
        for(int i = offset; i < offset + count; i++) {
            bytes[i] ^= KEY;
        }
        return result;
    }
}

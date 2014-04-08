package ex17_02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;

public class DataHandler {
	private WeakReference<File> lastFile;
	private WeakReference<byte[]> lastData;

	byte[] readFile(File file) {
		byte[] data;

		if (file.equals(lastFile)) {
			data = lastData.get();
			if (data != null)
				return data;
		}
		data = readBytesFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
	}

	public WeakReference<File> getLastFile() {
		return lastFile;
	}

	private byte[] readBytesFromFile(File file) {
		byte[] data = new byte[(int)file.length()];
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			inputStream.read(data);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return data;
	}
}

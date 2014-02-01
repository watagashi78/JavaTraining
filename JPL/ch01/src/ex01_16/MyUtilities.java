package ex01_16;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class MyUtilities {
	static final int MAX_DATASET_SIZE = 10;

	public double[] getDataSet(String setName) throws BadDataSetException {
		String file = setName + ".dset";
		FileInputStream in = null;
		try {
			in = new FileInputStream(file);
			return readDataSet(in);
		} catch (IOException e) {
			throw new BadDataSetException(setName, e);
		} finally {
			try {
				if (in != null)
					in.close();
			} catch (IOException e) {
				; // 無視: データの読み込みは成功しているか、あるいは、
					// BadDataSetExceptionをスローしようとしている
			}
		}
	}

	public double[] readDataSet(FileInputStream in) throws IOException {
		DataInputStream din = new DataInputStream(in);
		double[] dataSet = new double[MAX_DATASET_SIZE];
		try {
			for (int i = 0; din.available() > 0; i++) {
				dataSet[i] = din.readDouble();
			}
			return dataSet;
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (din != null)
					din.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

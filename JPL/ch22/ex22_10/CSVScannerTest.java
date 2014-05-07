package ex22_10;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class CSVScannerTest {
	private static final String FILE_PATH = "test.csv";
	private File file;

	@Before
	public void setUp() throws Exception {
		file = new File(FILE_PATH);
		FileWriter fw = null;
		try {
			fw = new FileWriter(file);
			fw.write("test1 test2\ntest3 test4 test5");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Test
	public void test() {
		try {
			CSVScanner.readCSV(new FileReader(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

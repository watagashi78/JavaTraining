package ex23_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CommandExitExecutor {
	public static final String[] EXIT_STRINGS = {"exit", "quit"};

	public static void main(String[] args) {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			while (true) {
				Process proc = exec(br.readLine());
				proc.waitFor();
			}
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Process exec(String cmd) throws IOException {
		for (String str : EXIT_STRINGS) {
			if (str.equals(cmd)) {
				System.out.println("Bye.");
				System.exit(0);
			}
		}
		Process proc = Runtime.getRuntime().exec(cmd);
		InputStream is = proc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);

		String line;
		int lineCount = 1;
		while ((line = br.readLine()) != null) {
			System.out.println(lineCount++ + ": " + line);
		}
		return proc;
	}

}

package ex23_01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CommandExecutor extends Thread {
	InputStream in;
	OutputStream out;

	public CommandExecutor(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	public static void main(String[] args) {
		try {
			Process proc = userProg("ipconfig");
			proc.waitFor();
		} catch (InterruptedException | IOException e) {
			e.printStackTrace();
		}
	}

	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
	}

	public static void plugTogether(InputStream in, OutputStream out) {
		new CommandExecutor(in, out).start();
	}

	public static void plugTogether(OutputStream out, InputStream in) {
		new CommandExecutor(in, out).start();
	}

	public void run() {
		int data;
		try {
			while ((data = in.read()) != -1) {
				out.write(data);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		super.run();
	}

}

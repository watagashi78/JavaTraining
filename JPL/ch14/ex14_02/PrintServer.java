package ex14_02;

import java.util.LinkedList;

public class PrintServer implements Runnable {
	private final LinkedList<String> requests = new LinkedList<String>();
	private final Thread thread;

	public PrintServer() {
		thread = new Thread(this);
		thread.start();
	}

	public void print(String job) {
		requests.add(job);
	}

	public void run() {
		if (Thread.currentThread().equals(thread)) {
			for(;;)
				if (!requests.isEmpty()) realPrint(requests.remove());
		} else {
			throw new IllegalStateException();
		}
	}

	private void realPrint(String job) {
		System.out.println(job);
	}
}

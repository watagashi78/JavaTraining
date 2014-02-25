package ex14_06;


public class TimeCount {
	private long start = 0;		// 実行開始時の時刻
	private long current = 0;	// 取得した現時刻
	private long exeTime = 0;	// 実行時間

	private void createMessageThread(final String message, final long interval) {
		new Thread() {
			@Override
			public void run() {
				showMessage(message, interval);
			}
		}.start();
	}

	private synchronized void showMessage(String message, long interval) {
		long last = System.currentTimeMillis();	// 前回メッセージが表示されたときの時刻
		for (;;) {
			// 前回メッセージが表示されてからintervalミリ秒経っていないならwait()
			while(current - last < interval)
				try {
					wait();
				} catch (InterruptedException e) {
					return;
				}
			System.out.println(message);
			last = System.currentTimeMillis();
		}
	}

	private void showTime(final long interval) {
		// Main Thread
		for (;;) {
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				return;
			}
			synchronized (this) {
				current = System.currentTimeMillis();
				exeTime = current - start;
				System.out.println(exeTime /1000 + " sec");
				notifyAll();
			}
		}
	}

	public void countStart() {
		start = current = System.currentTimeMillis();
		exeTime = current - start;
		createMessageThread("15sec Message", 15000);
		createMessageThread("7sec Message", 7000);
		showTime(1000);
	}

	public static void main(String[] args) {
		TimeCount test = new TimeCount();
		test.countStart();
	}

}

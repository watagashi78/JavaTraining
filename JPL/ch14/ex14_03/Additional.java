package ex14_03;

public class Additional {
	private int data = 0;

	public Additional() {}

	public synchronized void printAddResult(int num) {
		this.data += num;
		System.out.println(Thread.currentThread().getName() + " = " + this.data);
	}

	public static void main(String[] args) {
		final Additional hoge = new Additional();
		Thread test1 = new Thread() {
			public void run() {
				try {
					for(;;) {
						hoge.printAddResult(1);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		};

		Thread test2 = new Thread() {
			public void run() {
				try {
					for(;;) {
						hoge.printAddResult(2);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		};

		Thread test3 = new Thread() {
			public void run() {
				try {
					for(;;) {
						hoge.printAddResult(3);
						Thread.sleep(1000);
					}
				} catch (InterruptedException e) {
					return;
				}
			}
		};

		test1.start();
		test2.start();
		test3.start();
	}
}

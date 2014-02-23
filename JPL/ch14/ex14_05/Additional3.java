package ex14_05;

public class Additional3 {
	private static int data = 0;

	public Additional3() {}

	public static synchronized void printAddResult(int num) {
		data += num;
		System.out.println(Thread.currentThread().getName() + " = " + data);
	}

	public static void printSubtractResult(int num) {
		synchronized (Additional3.class) {
			data -= num;
		}
		System.out.println(Thread.currentThread().getName() + " = " + data);
	}

	public static void main(String[] args) {
		Thread test1 = new Thread() {
			public void run() {
				try {
					for(;;) {
						Additional3.printAddResult(1);
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
						Additional3.printSubtractResult(1);
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
						Additional3.printAddResult(1);
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
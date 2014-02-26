package ex14_09;

public class ThreadGroupManager extends Thread {
	private static final int THREAD_MARGIN = 4;
	private static final int GROUP_MARGIN = 4;
	private final ThreadGroup rootGroup;

	public ThreadGroupManager(ThreadGroup tg) {
		this.rootGroup = tg;
	}

	public void run() {
		for (;;) {
			try {
				Thread.sleep(5000);
				System.out.println("  *** Print Current Thread List ***");
				System.out.println("RootThreadGroup = [" + rootGroup.getName() + "]");
				printGroupRecursive(rootGroup);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void printGroupRecursive(ThreadGroup tg) {
		int threadNum = tg.activeCount();
		int threadGroupNum = tg.activeGroupCount();
		System.out.println("ThreadGroup = [" + tg.getName() + "], ActiveThread = " + threadNum);

		ThreadGroup[] threadGroupList = new ThreadGroup[threadGroupNum + GROUP_MARGIN];
		Thread[] threadList = new Thread[threadNum + THREAD_MARGIN];
		tg.enumerate(threadList, false);
		for (Thread th : threadList) {
			if (th != null)
				System.out.println("Thread = [" + th.getName() + "]");
		}
		tg.enumerate(threadGroupList, false);
		for (ThreadGroup child : threadGroupList) {
			if (child != null) {
				printGroupRecursive(child);
			}
		}
	}
}

package ex14_10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to
 * be exectued by a thread.
 *
 * [Instruction]
 *  Implement one constructor and three methods.
 *  Don't forget to write a Test program to test this class.
 *  Pay attention to @throws tags in the javadoc.
 *  If needed, you can put "synchronized" keyword to methods.
 *  All classes for implementation must be private inside this class.
 *  Don't use java.util.concurrent package.
 */
public class ThreadPool {
	private final WorkerThread[] pool;
	private final Queue<Runnable> queue;
	private final int queueSize;

	enum MyState {
		INIT, START, STOP;
	}

	private MyState state = MyState.INIT;

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize the max size of queue
	 * @param numberOfThreads the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException if either queueSize or numberOfThreads
	 *         is less than 1
	 */
	public ThreadPool(int queueSize, int numberOfThreads) {
		if (queueSize < 1 || numberOfThreads < 1)
			throw new IllegalArgumentException();
		this.queueSize = queueSize;
		this.pool = new WorkerThread[numberOfThreads];
		this.queue = new LinkedList<Runnable>();

		for (int i = 0; i < pool.length; i++) {
			this.pool[i] = new WorkerThread();
		}

	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException if threads has been already started.
	 */
	public void start() {
		if (state == MyState.START)
			throw new IllegalStateException();
		for (int i = 0; i < pool.length; i++) {
			try {
				this.pool[i] = new WorkerThread();
				this.pool[i].start();
			} catch (IllegalStateException e) {
				throw new IllegalStateException(e);
			}
		}
		state = MyState.START;
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException if threads has not been started.
	 */
	public void stop() {
		if (state != MyState.START) {
			throw new IllegalStateException();
		}
		state = MyState.STOP;
		for (int i = 0; i < pool.length; i++) {
			pool[i].stopRequest();
			try {
				pool[i].join();
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool.
	 * run() method will be invoked in the thread. If the queue is full, then
	 * this method invocation will be blocked until the queue is not full.
	 *
	 * @param runnable Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException if runnable is null.
	 * @throws IllegalStateException if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		if (runnable == null)
			throw new NullPointerException();
		if (state != MyState.START)
			throw new IllegalStateException();
		synchronized (queue) {
			if (queue.size() > queueSize) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					System.out.println(e);
				}
			}
			queue.add(runnable);
			queue.notifyAll();
		}

	}

	private class WorkerThread extends Thread {
		public void stopRequest() {
			synchronized (queue) {
				queue.notifyAll();
			}
		}

		@Override
		public void run() {
			Runnable runnable;
			while (state != MyState.STOP) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						if (state == MyState.START) {
							try {
								queue.wait();
							} catch (InterruptedException e) {
								System.out.println(e);
							}
						}
						if (state == MyState.STOP && queue.isEmpty())
							return;
					}
					runnable = queue.remove();
					queue.notifyAll();
				}
				runnable.run();
			}
		}
	}
}
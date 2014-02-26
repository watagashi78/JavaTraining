package ex14_09;

import org.junit.Test;

public class TestThreadGroupManager {

	@Test
	public void testPrintGroupRecursive() throws InterruptedException {
		for (;;) {
			ThreadGroup a = new ThreadGroup("A");
			ThreadGroup b1 = new ThreadGroup(a, "B1");
			ThreadGroup b2 = new ThreadGroup(a, "B2");
			ThreadGroup b3 = new ThreadGroup(a, "B3");
			ThreadGroup c1 = new ThreadGroup(b1, "C1");
			ThreadGroup c2 = new ThreadGroup(b1, "C2");
			ThreadGroupManager tgm = new ThreadGroupManager(a);
			tgm.start();
			new Thread(a, "a1") { public void run() {try {
				sleep(3000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(a, "a2") { public void run() {try {
				sleep(10000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(b1, "b11") { public void run() {try {
				sleep(14000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(b2, "b21") { public void run() {try {
				sleep(6000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(b2, "b22") { public void run() {try {
				sleep(4000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(b3, "b31") { public void run() {try {
				sleep(8000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(b3, "b32") { public void run() {try {
				sleep(20000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(c1, "c11") { public void run() {try {
				sleep(7000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(c2, "c21") { public void run() {try {
				sleep(25000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(c2, "c22") { public void run() {try {
				sleep(16000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			new Thread(c2, "c23") { public void run() {try {
				sleep(11000);
			} catch (InterruptedException e) {
				;
			}}}.start();
			Thread.sleep(30000);
			System.out.println("********** RETURN FIRST **********");
		}
	}

}

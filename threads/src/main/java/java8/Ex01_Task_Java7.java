package java8;

public class Ex01_Task_Java7 {
	
	static class MyRunnable implements Runnable {
		
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello Runnable " + threadName);
		}
		
	}
	
	static class MyThread extends Thread {
		public void run() {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello Thread " + threadName);
		}
	}
	
	public static void main(String[] args) {

		Runnable task = new MyRunnable();

		Thread thread = new Thread(task);
		thread.start();
		
		new MyThread().start();

		System.out.println("Done!");
	}
}

package java8;

public class Ex01_Task {
	public static void main(String[] args) {

		Runnable task = () -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		};

		task.run(); // hello main

		Thread thread = new Thread(task);
		thread.start(); // hello Thread-0

		System.out.println("Done!");
	}
}

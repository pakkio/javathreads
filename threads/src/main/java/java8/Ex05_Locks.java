package java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class Ex05_Locks {
	
	static ReentrantLock lock = new ReentrantLock();
	static int count = 0;

	static void increment() {
	    lock.lock();
	    try {
	        count++;
	    } finally {
	        lock.unlock();
	    }
	}
	public static void main(String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 10000)
		    .forEach(i -> executor.submit(Ex05_Locks::increment));

		stop(executor);

		System.out.println(count);  
		
		
	}
	
	public static void stop(ExecutorService executor) {
		try {
		    System.out.println("attempt to shutdown executor");
		    executor.shutdown();
		    executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally {
		    if (!executor.isTerminated()) {
		        System.err.println("cancel non-finished tasks");
		    }
		    executor.shutdownNow();
		    System.out.println("shutdown finished");
		}
	}

}

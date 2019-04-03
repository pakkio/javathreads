package java8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Ex04_Synchronized {
	static int count = 0;

	static void increment() {
	    count = count + 1;
	}
	
	static synchronized void incrementSync() {
	    count = count + 1;
	}
	public static void main(String[] args) {
		final ExecutorService executor = Executors.newFixedThreadPool(2);

		IntStream.range(0, 10000)
		    .forEach(i -> executor.submit(Ex04_Synchronized::increment));

		stop(executor);

		System.out.println(count);  
		count = 0;
		
		final ExecutorService executor2 = Executors.newFixedThreadPool(2);

		IntStream.range(0, 10000)
		    .forEach(i -> executor2.submit(Ex04_Synchronized::incrementSync));

		stop(executor2);

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

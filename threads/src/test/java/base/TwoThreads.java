package base;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoThreads {
	
	private static Logger log = LoggerFactory.getLogger( TwoThreads.class );

	@Test
	public void test2Threads() throws InterruptedException {
		
		
		float g1 = multiThreading(1);
		float g8 = multiThreading(8);
		float g20 = multiThreading(20);
		float g100 = multiThreading(100);
		float g1000 = multiThreading(1000);
		
		assertTrue(g8 > g1);
		assertTrue(g20 > g8);
		assertTrue(g100 > g20);
		assertTrue(g1000 > g100);
		
		
		
		
	}

	private float multiThreading(int numOfThreads) throws InterruptedException {
		long start = System.currentTimeMillis();
		List<MyRunnable> myRunners = 
				IntStream.range(0, numOfThreads)
				.mapToObj(i -> new MyRunnable())
				.collect(Collectors.toList());
		
		List<Thread> threads = myRunners.stream()
				.map(Thread::new)
				.collect(Collectors.toList());
		threads.forEach(t->t.setDaemon(true));
		
		//ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(100);
		threads.forEach(service::submit);
		
		Thread.sleep(1000);
		
		
		service.shutdownNow();
		
		//myRunners.forEach(r -> System.out.println("This thread counted for "+r.getCounter()));
		Long totalCount = myRunners.stream().mapToLong(MyRunnable::getCounter).sum();
		float ret = (float)totalCount/1024/1024/1024;
		log.info("Total count G: {} using {} ms", ret, (System.currentTimeMillis()-start) );
		return ret;
	}

}

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

public class MultipleThreads {

	private static Logger log = LoggerFactory.getLogger(MultipleThreads.class);

	@Test
	public void testMultiThreads() throws InterruptedException {
		float g1 = multiThreadingProcessingPowerForThreads(1);
		float g8 = multiThreadingProcessingPowerForThreads(8);
		float g20 = multiThreadingProcessingPowerForThreads(20);
		float g100 = multiThreadingProcessingPowerForThreads(100);
		float g1000 = multiThreadingProcessingPowerForThreads(1000);

		assertTrue(g8 > g1);
		assertTrue(g20 > g8);
		assertTrue(g100 > g20);
		assertTrue(g1000 > g100);
	}

	private float multiThreadingProcessingPowerForThreads(int numOfThreads) throws InterruptedException {
		long start = System.currentTimeMillis();

		List<MyRunnable> myRunners = IntStream.range(0, numOfThreads).mapToObj(i -> new MyRunnable())
				.collect(Collectors.toList());

		List<Thread> threads = myRunners.parallelStream().map(Thread::new).collect(Collectors.toList());

		threads.forEach(t -> t.setDaemon(true));

		// ExecutorService service = Executors.newSingleThreadExecutor();
		ExecutorService service = Executors.newFixedThreadPool(8);
		threads.forEach(service::submit);

		Thread.sleep(1000);

		threads.forEach(Thread::interrupt);
		service.shutdownNow();

		// myRunners.forEach(r -> System.out.println("This thread counted for
		// "+r.getCounter()));
		Long totalCount = myRunners.parallelStream().mapToLong(MyRunnable::getCounter).sum();

		float ret = (float) totalCount / 1024 / 1024 / 1024;
		log.info("Total count for {} G: {} using {} ms", numOfThreads, ret, (System.currentTimeMillis() - start));
		return ret;
	}

}

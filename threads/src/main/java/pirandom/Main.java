package pirandom;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class Main {

	public static void main(String[] args) throws InterruptedException {

		final long NTHREADS = 10;
		final long MAXTRIES = 10000000;
		
		long start = System.currentTimeMillis();

		List<PiRandom> runnable = LongStream.range(0, NTHREADS)
					.mapToObj(l -> new PiRandom(MAXTRIES/NTHREADS))
					.collect(Collectors.toList());
		
		ThreadPoolExecutor service = (ThreadPoolExecutor) Executors.newFixedThreadPool(20);
		
		runnable.forEach(r -> service.submit(r));
		
		while(service.getActiveCount()>0) {
			Thread.sleep(1000);
		}
		
		final AtomicLong totCount = new AtomicLong(0L);
		runnable.forEach(r-> {
			totCount.addAndGet(r.inCircleCount);
		});
		double finalValue = 4 * (double)totCount.get()/(MAXTRIES);
		long elapsed = System.currentTimeMillis()-start;
		System.out.println("final value is "+finalValue+ " elapsed "+elapsed);
		
		service.shutdownNow();
		
		
	}
}

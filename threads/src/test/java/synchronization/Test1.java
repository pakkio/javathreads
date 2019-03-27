package synchronization;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


import org.junit.Test;

public class Test1 {
	
	

	@Test
	public void test() throws InterruptedException {
		BankAccount b = new BankAccount("Joe");
		//ExecutorService executor = Executors.newFixedThreadPool(8);
		
		b.addAmount(100);
		b.addAmount(100);
		try {
			b.subtractAmount(300);
			fail("Should not be able to subtract 300");
		} catch (InsufficientFunds e) {
			// this is OK and expected
		}
		assertEquals(200,b.getAmount(),0.01);
	}
	
	@Test
	public void testThreading() throws Exception {
		final int NUM = 10;
		final double VALUE = 100;
		
		ExecutorService executor = Executors.newFixedThreadPool(8);

		BankAccount b = new BankAccount("Jim");
		
		List<Transaction> list = IntStream.range(0,NUM)
				.mapToObj(i -> new Transaction(b,VALUE))
				.collect(Collectors.toList());
		
		List<Thread> threads = list.stream()
			.map(Thread::new)
			.collect(Collectors.toList());
		
		threads.forEach(t -> executor.submit(t));

		Thread.sleep(2000);
		
		assertEquals(NUM*VALUE,b.getAmount(),0.001);
		
		
	}

}

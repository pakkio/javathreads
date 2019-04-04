package synchronization;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import synchronization.Transaction.TransactionType;

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
				.mapToObj(i -> new Transaction(TransactionType.DEPOSIT, b,VALUE))
				.collect(Collectors.toList());
		
		List<Thread> threads = list.stream()
			.map(Thread::new)
			.collect(Collectors.toList());
		
		threads.forEach(t -> executor.submit(t));

		Thread.sleep(10000);
		
		assertEquals(NUM*VALUE,b.getAmount(),0.001);
		
		
	}
	
	@Test
	public void deadLockTest() throws Exception {
		// T1 transfer from 100 euros from A to B and viceversa
		BankAccount a = new BankAccount("A"); a.setAmount(1000);
		BankAccount b = new BankAccount("B"); b.setAmount(100);
		
		Transfer t1 = new Transfer(a,b,100.0);
		Transfer t2 = new Transfer(b,a,100.0);
		
		new Thread(t1).start();
		new Thread(t2).start();
		
		Thread.sleep(1000);
		
		
	}

}

package base;

import static org.junit.Assert.*;

import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SimpleThreads {
	
	private static Logger log = LoggerFactory.getLogger( SimpleThreads.class );
	
	// using a main instead of a test since this might never quit
	public static void main(String[] args) {
		Thread thread = new MyNeverEndingThread();
		//thread.setDaemon(true);
		thread.start();
		log.info("Main ending");
	}

	@Test
	public void t01_shouldHave2ThreadsActive() {
		log.info("\n\nt01_shouldHave2ThreadsActive");
		log.info("Current thread");
		assertEquals(2,Thread.activeCount());
		
	}
	
	@Test
	public void t02_creatingAThreadShouldHaveAnAdditionalActiveThread() throws InterruptedException {
		log.info("\n\nt02_creatingAThreadShouldHaveAnAdditionalActiveThread");
		Thread thread = new MyThread();
		thread.start();
		assertEquals(3,Thread.activeCount());
		thread.join();
		assertEquals(2,Thread.activeCount());
		
	}
	
	@Test
	//public void interruptingAThreadIfWeGiveEnoughTimeWillKillIt() throws InterruptedException {
	public void t03_interrupting_a_thread_if_we_give_enough_time_will_kill_it() throws InterruptedException {
		log.info("\n\nt03_interrupting_a_thread_if_we_give_enough_time_will_kill_it");
		Thread thread = new MyThread();
		thread.start();
		thread.interrupt();
		Thread.sleep(100);
		assertEquals(2,Thread.activeCount());

	}
	
	@Test
	public void t04_testingRunnable() throws Exception {
		log.info("\n\nt04_testingRunnable");
		MyRunnable runnable = new MyRunnable();
		Thread t = new Thread(runnable);
		t.start();
		Thread.sleep(1000);
		t.interrupt();
		Thread.sleep(100);
		log.info("Runnable counted for "+runnable.getCounter());
		assertTrue(runnable.getCounter()>90);
		
	}
	
	
}

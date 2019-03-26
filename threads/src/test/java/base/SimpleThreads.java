package base;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
	public void shouldHave2ThreadsActive() {
		assertEquals(2,Thread.activeCount());
		
	}
	
	@Test
	public void creatingAThreadShouldHaveAnAdditionalActiveThread() throws InterruptedException {
		Thread thread = new MyThread();
		thread.start();
		assertEquals(3,Thread.activeCount());
		thread.join();
		assertEquals(2,Thread.activeCount());
		
	}
	
	@Test
	//public void interruptingAThreadIfWeGiveEnoughTimeWillKillIt() throws InterruptedException {
	public void interrupting_a_thread_if_we_give_enough_time_will_kill_it() throws InterruptedException {
		Thread thread = new MyThread();
		thread.start();
		thread.interrupt();
		Thread.sleep(100);
		assertEquals(2,Thread.activeCount());

	}
	
	@Test
	public void testingRunnable() throws Exception {
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

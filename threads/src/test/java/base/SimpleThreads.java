package base;

import static org.junit.Assert.*;

import org.junit.Test;


public class SimpleThreads {
	
	// using a main instead of a test since this might never quit
	public static void main(String[] args) {
		Thread thread = new MyNeverEndingThread();
		//thread.setDaemon(true);
		thread.start();
		System.out.println("Main ending");
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
		System.out.println("Runnable counted for "+runnable.getCounter());
		assertTrue(runnable.getCounter()>90);
		
	}
	
	
}

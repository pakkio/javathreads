package base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ShowThreads {

	@Test
	public void shouldHave2ThreadsActive() {
		assertEquals(2,Thread.activeCount());
		
	}
	
	@Test
	public void creatingAThread() throws InterruptedException {
		Thread thread = new MyThread();
		thread.start();
		assertEquals(3,Thread.activeCount());
		thread.join();
		assertEquals(2,Thread.activeCount());
		
	}
}

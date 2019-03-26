package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyRunnable implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger( MyRunnable.class );

	private int counter=0;

	@Override
	public void run() {
		log.debug("starting MyRunnable ");
		while(!Thread.interrupted()) {
			counter++;
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				break;
//			}
//			System.out.println("Counter "+getCounter());
		}
		log.debug("stopping myRunnable");
		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}

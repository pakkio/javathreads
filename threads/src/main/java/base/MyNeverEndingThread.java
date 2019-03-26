package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyNeverEndingThread extends Thread {
	
	private static Logger log = LoggerFactory.getLogger( MyNeverEndingThread.class );

	@Override
	public void run() {
		try {
			log.info("neverending thread started");
			Thread.sleep(9999999999L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

package base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyThread extends Thread {
	private static Logger log = LoggerFactory.getLogger( MyThread.class );

	@Override
	public void run() {
		try {
			log.debug("MyThread starting");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

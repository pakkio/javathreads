package pirandom;

import java.util.Random;

public class PiRandom implements Runnable {
	long batchSize = 0;
	long inCircleCount = 0;
	Random random = new Random();
	
	public PiRandom(long batchSize) {
		this.batchSize = batchSize;
	}

	public void run() {
		
		
		for (long i = 0; i < batchSize; i++) {
		    double x = random.nextDouble();
		    double y = random.nextDouble();
		    
			//trialCount++;
		    if (x*x + y*y < 1)
		        inCircleCount++;                        
		}
	}

}

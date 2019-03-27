package deadlock;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransactionTest {
	/** The usual Logger.*/
	private static final Logger log = LoggerFactory.getLogger(TransactionTest.class);
	

	@Test
	public void testDeadlockThisWIllNeverEnd() throws Exception {
		final Account accA = new Account("Acc 1");
        final Account accB = new Account("Acc 2");
        accA.deposit(1000.00);
        accB.deposit(1000.00);

        Transaction t1 = new Transaction("T01", accA, accB, 100.00);
        Transaction t2 = new Transaction("T02", accB, accA, 500.00);

        t1.start();
        t2.start();
        
        try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        log.info("Account 1 is {}",accA.getBalance());
        log.info("Account 2 is {}",accB.getBalance());
        
        t1.join();
        t2.join();
        
        
        
	}

}

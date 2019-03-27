package synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Transaction implements Runnable {
	
	private static Logger log = LoggerFactory.getLogger( Transaction.class );
	private BankAccount account;
	private double value;

	public Transaction(BankAccount b, double value) {
		this.account = b;
		this.value = value;
		log.debug("Prepare to add {} to {}", value, b.getName());
		
	}

	@Override
	public void run() {
		log.debug("Actually adding {} to {}", value, account.getName());
		try {
			account.addAmount(value);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		log.debug("Finished adding");
	}

}

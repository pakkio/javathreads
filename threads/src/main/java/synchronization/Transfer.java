package synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transfer implements Runnable {

	private static Logger log = LoggerFactory.getLogger(Transfer.class);
	private BankAccount from;
	private BankAccount to;
	private double value;
	
	public Transfer(BankAccount a, BankAccount b, Double value) {
		this.from = a;
		this.to = b;
		this.value = value;
		log.debug("Prepare to transfer value {} from {} to {}",
				new Object[] { value, a.getName(), b.getName() });

	}

	@Override
	public void run() {
		try {
			log.info("Withdrawing {} from {}", value, from.getName());
			from.subtractAmount(value);
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("Depositing {} to {}", value, to.getName());
			to.addAmount(value);
			
		} catch (InsufficientFunds e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}

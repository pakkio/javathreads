package synchronization;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction implements Runnable {

	enum TransactionType {
		DEPOSIT, WITHDRAW
	};

	private static Logger log = LoggerFactory.getLogger(Transaction.class);
	private BankAccount account;
	private double value;
	private TransactionType type;

	public Transaction(TransactionType type, BankAccount b, Double value) {
		this.type = type;
		this.account = b;
		this.value = value;
		log.debug("Prepare to do operation {} value {} with {}", new Object[] { type.toString(), value, b.getName() });

	}

	@Override
	public void run() {

		switch (type) {
		case DEPOSIT:

			log.debug("Actually adding {} to {}", value, account.getName());
			
			account.addAmount(value);
			
			log.debug("Finished adding");
			break;
		case WITHDRAW:
			log.debug("Actually withdrawing {} from {}", value, account.getName());
			try {
				account.subtractAmount(value);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}

}

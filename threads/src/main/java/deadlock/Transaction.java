package deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Transaction extends Thread {
	/** The usual Logger.*/
	private static final Logger log = LoggerFactory.getLogger(Transaction.class);
	
    private final String id;
    private final Account from;
    private final Account to;
    private final double amount;

    public Transaction(String id, Account from, Account to, double amount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public void run() {
        // Acquire the lock of Account 'from'
        synchronized (from) {
            from.withdraw(amount);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) { }

            // Acquire the lock of Account 'to'
            synchronized (to) {
                to.deposit(amount);
            }
            // Release the lock of Account 'to'
        }
        // Release the lock of Account 'from'
        log.info(amount + "is transfered from " + from + " to " + to);
    }
}
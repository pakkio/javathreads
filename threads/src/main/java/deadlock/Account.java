package deadlock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Account {
	/** The usual Logger.*/
	private static final Logger log = LoggerFactory.getLogger(Account.class);
	
    private final String name;
    private double balance;

    public Account(String name) {
        this.name = name;
    }

    public void withdraw(double amount) {
    	log.info("withdrawing {} in {}",amount,name);
        this.balance -= amount;
    }

    public void deposit(double amount) {
    	log.info("depositing {} in {}",amount,name);
        this.balance += amount;
    }

    public double getBalance() {
        return this.balance;
    }

    @Override
    public String toString() {
        return name;
    }
}
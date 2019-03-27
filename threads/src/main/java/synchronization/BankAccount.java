package synchronization;

public class BankAccount {

	private double amount = 0;
	private String name;

	public BankAccount(String string) {
		this.name = string;
	}

	public double getAmount() {
		return amount;
	}

	public String getName() {
		return name;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//synchronized
	public void addAmount(double value) throws InterruptedException {
		double original = this.amount;
		Thread.sleep(100);	
		this.amount = original +  value;
	}
	public void subtractAmount(double value) throws InsufficientFunds {
		if(amount - value < 0 ) throw new InsufficientFunds();
		amount -= value;
	}
	

}

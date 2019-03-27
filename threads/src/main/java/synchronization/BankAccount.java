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
	
	synchronized
	public void addAmount(double value) {
		double original = this.amount;
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		this.amount = original +  value;
	}
	synchronized
	public void subtractAmount(double value) throws InsufficientFunds {
		if(amount - value < 0 ) throw new InsufficientFunds();
		amount -= value;
	}
	

}

package base;

public class MyRunnable implements Runnable {

	private int counter=0;

	@Override
	public void run() {
		//System.out.println("starting MyRunnable ");
		while(!Thread.interrupted()) {
			counter++;
//			try {
//				Thread.sleep(10);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				break;
//			}
//			System.out.println("Counter "+getCounter());
		}
		//System.out.println("stopping myRunnable");
		
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}

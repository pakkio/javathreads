package base;

public class MyNeverEndingThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("neverending thread started");
			Thread.sleep(9999999999L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}

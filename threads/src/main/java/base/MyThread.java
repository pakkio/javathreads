package base;

public class MyThread extends Thread {

	@Override
	public void run() {
		try {
			System.out.println("MyThread starting");
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}

package classe;

public class MyRunnable implements Runnable { //

	public void run() {
		System.out.println("[" + Thread.currentThread().getName() + "] I am in the thread");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("[" + Thread.currentThread().getName() + "] I am finishing");

	}

	public static void main(String[] args) throws InterruptedException {

		System.out.println("Numero processori: " + Runtime.getRuntime().availableProcessors());
		MyRunnable r1 = new MyRunnable();
		MyRunnable r2 = new MyRunnable();
		
		Thread t1 = new Thread(r1); //
		Thread t2 = new Thread(r2); //

		// t1.run();
		t1.start();
		t2.start();

//		t1.join();
//		t2.join();
	}

}

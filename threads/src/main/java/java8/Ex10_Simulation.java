package java8;

import java.util.concurrent.ThreadLocalRandom;

public class Ex10_Simulation {

	public static class Person implements Runnable {

		private String name;

		public Person(String name) {
			this.name = name;
		}

		public void run() {
			System.out.println(name+" enters the coffee shop ");
			// do long action for some seconds min 2 secs, max 10 secs
			int timeCoffee = random(2,10);
			System.out.println(name + " drinks a coffee for " + timeCoffee / 1000 + " seconds");
			try {
				Thread.sleep(timeCoffee);
			} catch (InterruptedException ex) {
			}
			
			int timeQueue = random(10,30);
			System.out.println(name+" then queues up to cash desk for " + timeQueue/1000+ " seconds");
			try {
				Thread.sleep(timeQueue);
			} catch (InterruptedException ex) {
			}
			System.out.println(name+" then quits the coffee shop ");
			
		}
	}

	public static void main(String[] args) {
		// create 10 people
		Person p1 = new Person("James");
		Person p2 = new Person("Anna");
		// make them have a coffee
		Thread t1 = new Thread(p1);
		Thread t2 = new Thread(p2);
		
		
		
		t1.start();
		t2.start();
		// wait for them to finish
		// since they are NOT daemons 
		// main thread will wait for all threads to finish

	}

	private static int random(int min, int max) {
		return ThreadLocalRandom.current().nextInt(min * 1000, (max + 1) * 1000);
	}

}

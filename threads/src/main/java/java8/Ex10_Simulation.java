package java8;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

public class Ex10_Simulation {

	public static class Person implements Runnable {

		private String name;

		public Person(String name) {
			this.name = name;
		}

		public void run() {
			System.out.println(name + " enters the coffee shop ");

			int timeCoffee = random(2, 10);
			System.out.println(name + " drinks a coffee for " + timeCoffee / 1000 + " seconds");
			sleep(timeCoffee);

			int timeQueue = random(10, 30);
			System.out.println(name + " then queues up to cash desk for " + timeQueue / 1000 + " seconds");
			sleep(timeQueue);
			System.out.println(name + " then quits the coffee shop ");

		}
	}

	public static void main(String[] args) {
		// create 10 people
		String[] names = new String[] { "James", "Anna", "Tom" };

		// see https://stackoverflow.com/questions/6275785/wrapping-chained-method-calls-on-a-separate-line-in-eclipse-for-java
		// for instruction on how to set up autoformat
		Arrays	.stream(names)
				.map(name -> new Person(name))
				.map(runnable -> new Thread(runnable))
				.forEach(thread -> thread.start());
		
		// note: for duplicating see
		// https://superuser.com/questions/279756/how-to-disable-the-screen-orientation-hotkeys-in-windows-xp
	}

	private static int random(int min, int max) {
		return ThreadLocalRandom.current()
								.nextInt(min * 1000, (max + 1) * 1000);
	}
	private static void sleep(long msecs) {
		try {
			Thread.sleep(msecs);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}

}

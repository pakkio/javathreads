package maven;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Adder {
	private static Logger log = LoggerFactory.getLogger(Adder.class);

	public int add(int a, int b) {
		log.info("Adding {} to {}",a,b);
		int result = a+b;
		log.info("Result is {}",result);

		return result;
	}

}

package maven;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJunit {
	private static Logger log = LoggerFactory.getLogger(TestJunit.class);

	@Test 
	public void testingThat1Plus2Is3() {
		log.info("testing adder");
		// preparazione
		int a=1;
		int b=2;
		
		Adder adder = new Adder();
		
		// si testa qualcosa
		
		int result = adder.add(a,b);
		
		//           expected   under test
		
		assertEquals(4,         result);
		log.info("testing adder finished");
	}

}

package maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExample {
	public static void main(String[] args) {
		Map<String, Double> employeeSalary = new HashMap<>();
		employeeSalary.put("David", 76000.00);
		employeeSalary.put("John", 120000.00);
		employeeSalary.put("Mark", 95000.00);
		employeeSalary.put("Steven", 134000.00);
		
		// this should output 134000
		System.out.println("Steven salary is "+employeeSalary.get("Steven"));

		System.out.println("=== Iterating over a HashMap using Java 8 forEach and lambda ===");
		employeeSalary.forEach((employee, salary) -> {
			System.out.println(employee + " => " + salary);
		});

		
	}
}

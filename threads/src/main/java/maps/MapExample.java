package maps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapExample {
	
	static class MyBean {
		
		public MyBean(String name, String tipo, double salary) {
			this.name = name;
			this.tipo = tipo;
			this.salary = salary;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTipo() {
			return tipo;
		}
		public void setTipo(String tipo) {
			this.tipo = tipo;
		}
		public Double getSalary() {
			return salary;
		}
		public void setSalary(Double salary) {
			this.salary = salary;
		}
		private String name;
		private String tipo;
		private Double salary;
	}
	
	public static void main(String[] args) {
		Map<String, MyBean> employeeSalary = new HashMap<>();
		employeeSalary.put("David", new MyBean("David","impiegato",76000));
//		employeeSalary.put("David", 72000.00);
//		employeeSalary.put("John", 120000.00);
//		employeeSalary.put("Mark", 95000.00);
//		employeeSalary.put("Steven", 134000.00);
		
		
		// this should output 134000
		MyBean david = employeeSalary.get("David");
		System.out.println("David salary is "+employeeSalary.get("David"));

		System.out.println("=== Iterating over a HashMap using Java 8 forEach and lambda ===");
		employeeSalary.forEach((key,value) -> {
			System.out.println(key + " => " + value);
		});

		
	}

	@Override
	public String toString() {
		return "MapExample [getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}
}

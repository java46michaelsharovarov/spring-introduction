package telran.spring.calculator;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CalculatorAppl {

	public static void main(String[] args) {
		ConfigurableApplicationContext ct = SpringApplication.run(CalculatorAppl.class, args);
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				System.out.println("for server shutdown type 'exit'");				
				String line = scanner.nextLine();				
				if(line.equalsIgnoreCase("exit")) {
					break;
				}
			}
		}
		ct.close();
	}
	
}

package telran.spring;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class SpringArchitectureApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ct = SpringApplication.run(SpringArchitectureApplication.class, args);
		try (Scanner scanner = new Scanner(System.in)) {
			while(true) {
				System.out.println("for shutdown type 'exit'");
				String line = scanner.nextLine();
				if(line.equalsIgnoreCase("exit")) {
					break;
				}
			}
		}
		ct.close();
	}

}

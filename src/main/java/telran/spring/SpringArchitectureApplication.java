package telran.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import telran.spring.controller.MessageSenderMenu;
import telran.spring.view.ConsoleInputOutput;
import telran.spring.view.Menu;

@SpringBootApplication
public class SpringArchitectureApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ct = SpringApplication.run(SpringArchitectureApplication.class, args);
		MessageSenderMenu messageSenderMenu = ct.getBean(MessageSenderMenu.class);
		Menu menu = new Menu("Message senders", 4, messageSenderMenu.getMessageSenderItems());
//		Menu menu = new Menu("Message senders", 4, messageSenderMenu.getMessageSenderOptions());
		menu.perform(new ConsoleInputOutput());
		ct.close();
	}

}

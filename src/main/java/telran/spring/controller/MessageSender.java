package telran.spring.controller;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.dto.Message;
import telran.spring.service.Sender;

@RestController
@RequestMapping("messages")
public class MessageSender {
	
	Map<String, Sender> senders;

	public MessageSender(Map<String, Sender> senders) {
		this.senders = senders;
	}

	@PostMapping
	String sendMessage(@RequestBody @Valid Message message) {
		Sender sender = senders.get(message.type);
		return sender != null ? sender.send(message) : "Wrong type " + message.type;
	}

	@GetMapping
	Set<String> getTypes() {
		return senders.keySet();
	}
	
	@PostConstruct
	void displayTypes() {
		System.out.printf("application context is created with types %s%n", senders.keySet());
	}
	
	@PreDestroy
	void shutdown() {
		System.out.println("Bye, performed graceful shutdown");
	}

}
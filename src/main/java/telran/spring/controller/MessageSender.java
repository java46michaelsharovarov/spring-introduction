package telran.spring.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import telran.spring.dto.Message;
import telran.spring.service.Sender;

@RestController
@RequestMapping("messages")
public class MessageSender {
	
	static Logger LOG = LoggerFactory.getLogger(MessageSender.class);
	Map<String, Sender> senders;

	public MessageSender(Map<String, Sender> senders) {
		this.senders = senders;
	}

	@PostMapping
	String sendMessage(@RequestBody @Valid Message message) {
		LOG.debug("received request for sending text: {}, sender type: {}", message.text, message.type);
		Sender sender = senders.get(message.type);
		return sender != null ? sender.send(message) : "Wrong type " + message.type;
	}

	@GetMapping
	Set<String> getTypes() {
		return senders.keySet();
	}
	
	@PostConstruct
	void displayTypes() {
		LOG.info("application context is created with types {}", senders.keySet());
	}
	
	@PreDestroy
	void shutdown() {
		LOG.info("Bye, performed graceful shutdown");
	}

}
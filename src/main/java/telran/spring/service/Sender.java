package telran.spring.service;

import org.slf4j.*;

import telran.spring.dto.Message;

public interface Sender {
	
	static Logger LOG = LoggerFactory.getLogger(Sender.class);
	
	String send(Message message);
	
}
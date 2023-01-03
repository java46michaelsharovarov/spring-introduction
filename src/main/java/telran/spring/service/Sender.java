package telran.spring.service;

import telran.spring.dto.Message;

public interface Sender {
	
	String send(Message message);
	
}
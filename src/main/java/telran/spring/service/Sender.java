package telran.spring.service;

import telran.spring.view.InputOutput;

public interface Sender {
	
	void send(InputOutput io, String message);
	
}
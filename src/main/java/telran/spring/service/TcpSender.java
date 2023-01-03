package telran.spring.service;

import org.springframework.stereotype.Service;

import telran.spring.dto.*;

@Service("TCP")
public class TcpSender implements Sender {
	
	@Override
	public String send(Message message) {
		TcpMessage tcpMessage = (TcpMessage) message;
		return String.format("message: '%s' has been sent to tcp address: %s:%d\n",tcpMessage.text,
				tcpMessage.ipAddress, tcpMessage.port);

	}

}
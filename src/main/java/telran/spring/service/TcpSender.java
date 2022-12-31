package telran.spring.service;

import org.springframework.stereotype.Service;

@Service("TCP")
public class TcpSender implements Sender {

	@Override
	public void send(String text) {
		System.out.printf("TCP message: %s has been send\n", text);
	}

}

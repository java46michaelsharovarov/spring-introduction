package telran.spring.service;

import org.springframework.stereotype.Service;

@Service("SMS")
public class SmsSender implements Sender {

	@Override
	public void send(String text) {
		System.out.printf("SMS message: %s has been send\n", text);
	}

}

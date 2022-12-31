package telran.spring.service;

import org.springframework.stereotype.Service;

@Service("Whatsup")
public class WhatsupSender implements Sender {

	@Override
	public void send(String text) {
		System.out.printf("Whatsup message: %s has been send\n", text);
	}

}

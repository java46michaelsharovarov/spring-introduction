package telran.spring.service;

import org.springframework.stereotype.Service;

import telran.spring.dto.*;

@Service("Email")
public class EmailSender implements Sender {

	@Override
	public String send(Message message) {
		EmailMessage emailMessage;
		try {
			emailMessage = (EmailMessage) message;
		} catch (Exception e) {
			return String.format("message data mismatch sender type");
		}
		return String.format("message: '%s' has been sent to email address: %s\n", emailMessage.text,
				emailMessage.emailAddress);
	}

}
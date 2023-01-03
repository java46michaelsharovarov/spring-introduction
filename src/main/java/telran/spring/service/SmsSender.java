package telran.spring.service;

import org.springframework.stereotype.Service;

import telran.spring.dto.*;

@Service("SMS")
public class SmsSender implements Sender {

	@Override
	public String send(Message message) {
		SmsMessage smsMessage = (SmsMessage) message;
		return String.format("message: '%s' has been sent to phone number: %s\n", smsMessage.text,
				smsMessage.phoneNumber);
	}

}
package telran.spring.service;

import org.springframework.stereotype.Service;

import telran.spring.view.InputOutput;

@Service("SMS")
public class SmsSender implements Sender {

	@Override
	public void send(InputOutput io, String message) {
		String phoneNumber = io.readPredicate("Enter phone number", "Wrong phone number",
				SmsSender::phoneNumberPredicate);
		io.writeLine(String.format("message: '%s' has been sent to phone number: %s\n", message, phoneNumber));
	}

	private static boolean phoneNumberPredicate(String phone) {
		return phone.matches(phoneNumberRegEx());
	}

	private static String phoneNumberRegEx() {
		return "(\\+972\\s*-?|0)(5\\d|7[2-7])(-?\\d){7}";
	}

}
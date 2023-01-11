package telran.spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import telran.spring.dto.EmailMessage;
import telran.spring.dto.TcpMessage;
import telran.spring.service.*;

@SpringBootTest
class MessageServicesTest {

	@Autowired
	EmailSender emailSender;
	@Autowired
	SmsSender smsSender;
	@Autowired
	TcpSender tcpSender;	

	@Test
	void emailSenderTest() {
		EmailMessage message = new EmailMessage();
		message.emailAddress = "mail@mail.com";
		message.text = "Hello world!";
		message.type = "Email";
		assertTrue(emailSender.send(message).contains("has been sent to email"));
	}
	
	@Test
	void emailSenderWrongDTOTest() {
		TcpMessage message = new TcpMessage();
		message.ipAddress = "1.1.1.1";
		message.port = 4000;
		message.text = "Hello world!";
		message.type = "Email";
		assertTrue(emailSender.send(message).contains("message data mismatch sender type"));
	}

}

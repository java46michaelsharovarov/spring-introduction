package telran.spring;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.controller.MessageSender;
import telran.spring.dto.EmailMessage;

@WebMvcTest(MessageSender.class)
class MessageSenderTest {
	
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

	@Test
	void rightDataTest() throws Exception {
		EmailMessage message = new EmailMessage();
		message.emailAddress = "mail@mail.com";
		message.text = "Hello world!";
		message.type = "Email";
		String messageJSON = mapper.writeValueAsString(message);
		mockMvc.perform(post("http://localhost:8080//messages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(messageJSON)).andExpect(status().isOk());
	}
	
	@Test
	void wrongDataTest() throws Exception {
		EmailMessage message = new EmailMessage();
		message.emailAddress = "mail";
		message.text = "Hello world!";
		message.type = "Email";
		String messageJSON = mapper.writeValueAsString(message);
		mockMvc.perform(post("http://localhost:8080//messages")
				.contentType(MediaType.APPLICATION_JSON)
				.content(messageJSON)).andExpect(status().isBadRequest());
	}

}

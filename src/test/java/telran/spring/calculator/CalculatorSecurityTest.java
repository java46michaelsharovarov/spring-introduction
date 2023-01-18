package telran.spring.calculator;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import telran.spring.calculator.controller.CalculatorController;
import telran.spring.calculator.security.CalculatorSecurityConfiguration;

@WebMvcTest(CalculatorController.class)
@Import(CalculatorSecurityConfiguration.class)
public class CalculatorSecurityTest {
	
	@Autowired
	MockMvc mocMvc;
	private static final String URL = "http://localhost:8080//calculator";
	
	@Test
	void rightAuthenticationAuthorization() throws Exception {
		mocMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}").with(user("admin").roles("ADMIN")))
		.andExpect(status().isBadRequest());				
	}
	
	@Test
	void wrongAuthentication() throws Exception {
		mocMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}"))
		.andExpect(status().isUnauthorized());				
	}

	@Test
	void wrongAuthorization() throws Exception {
		mocMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{}").with(user("admin")))
		.andExpect(status().isForbidden());				
	}
}
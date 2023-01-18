package telran.spring.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.calculator.controller.CalculatorController;
import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.security.CalculatorSecurityConfiguration;

@WebMvcTest(CalculatorController.class)
@Import(CalculatorSecurityConfiguration.class)
@WithMockUser(roles = {"USER", "ADMIN"})
class CalculatorControllerTests {
	
	private static ArithmeticOperationData data;
	private static final String URL = "http://localhost:8080//calculator";
	private static final String MILTIPLICATION = "*";
	private static final String ARITHMETIC_OPERATIONS = "arithmetic operations";
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

	@BeforeEach
	void beforeEach() {
		data = new ArithmeticOperationData();
		data.operationName  = ARITHMETIC_OPERATIONS;
		data.additionalData  = MILTIPLICATION;
		data.operand1  = 15.0;
		data.operand2  = 3.0;
	}
	
	@Test
	void properOperationDataTest() throws Exception {		
		String dataJSON = mapper.writeValueAsString(data);
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dataJSON)).andExpect(status().isOk());
	}
	
	@Test
	void improperOperationDataTest() throws Exception {
		data.operand1  = null;
		String dataJSON = mapper.writeValueAsString(data);
		mockMvc.perform(post(URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(dataJSON)).andExpect(status().isBadRequest());
	}
	
	@Test
	void getTest() throws Exception {
		mockMvc.perform(get(URL)).andExpect(status().isOk());
	}

}

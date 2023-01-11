package telran.spring.calculator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.calculator.controller.CalculatorController;
import telran.spring.calculator.dto.ArithmeticOperationData;

@WebMvcTest(CalculatorController.class)
class CalculatorControllerTests {
	
	@Autowired
	MockMvc mockMvc;
	ObjectMapper mapper = new ObjectMapper();

//	@Test
//	void properOperationData() throws Exception {
//		ArithmeticOperationData data = new ArithmeticOperationData();
//		data.operationName  = "arithmetic operations";
//		data.additionalData  = "*";
//		data.operand1  = 15.0;
//		data.operand2  = 3.0;
//		String dataJSON = mapper.writeValueAsString(data);
//		mockMvc.perform(post("http://localhost:8080//calculator")
//				.contentType(MediaType.APPLICATION_JSON)
//				.content(dataJSON)).andExpect(status().isOk());
//	}
	
	@Test
	void improperOperationData() throws Exception {
		ArithmeticOperationData data = new ArithmeticOperationData();
		data.operationName  = "arithmetic operations";
		data.additionalData  = ")";
		data.operand1  = 15.0;
		data.operand2  = 3.0;
		String dataJSON = mapper.writeValueAsString(data);
		mockMvc.perform(post("http://localhost:8080//calculator")
				.contentType(MediaType.APPLICATION_JSON)
				.content(dataJSON)).andExpect(status().isBadRequest());
	}

}

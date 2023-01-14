package telran.spring.calculator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AbstractOperation implements Operation {

	@Autowired
	ObjectMapper mapper;

	@Value("${app.message.mismath.data: data mismatch with operation type}")
	String mismatchOperationWithData;

}

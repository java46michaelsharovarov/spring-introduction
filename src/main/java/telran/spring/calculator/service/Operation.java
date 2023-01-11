package telran.spring.calculator.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import telran.spring.calculator.dto.OperationData;

public interface Operation {
	
	ObjectMapper mapper = new ObjectMapper();
	
	static Logger LOG = LoggerFactory.getLogger(Operation.class);
	
	Set<String> getMethodNames();

	String execute(OperationData data);
	
	String getOperationName(); 
	
}

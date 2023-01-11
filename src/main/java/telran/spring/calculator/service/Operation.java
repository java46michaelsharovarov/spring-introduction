package telran.spring.calculator.service;

import java.util.Set;

import telran.spring.calculator.dto.OperationData;

public interface Operation {
	
	Set<String> getMethodNames();

	String execute(OperationData data);
	
	String getOperationName(); 
	
}

package telran.spring.calculator.service;

import java.util.Map;

import telran.spring.calculator.dto.OperationData;

public interface Operation {
	
	Map<String, ? extends Object> getMethods();

	String execute(OperationData data);
	
	String getOperationName();
	
}

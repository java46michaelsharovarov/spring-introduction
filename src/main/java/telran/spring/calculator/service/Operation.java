package telran.spring.calculator.service;

import java.lang.reflect.Method;
import java.util.Map;

import telran.spring.calculator.dto.OperationData;

public interface Operation {

	String execute(OperationData data);
	
	public Map<String, Method> getMethods();
	
}

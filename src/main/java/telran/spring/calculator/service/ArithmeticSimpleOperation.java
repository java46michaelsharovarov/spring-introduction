package telran.spring.calculator.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.Getter;
import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.annotations.OperatorSign;

@Getter
@Service("arithmetic operations")
public class ArithmeticSimpleOperation implements Operation {

	private Map<String, Method> methods;

	public ArithmeticSimpleOperation() {
		this.methods = Arrays.stream(ArithmeticSimpleOperationMethods.class.getDeclaredMethods())
				.collect(Collectors.toMap(m -> m.getAnnotation(OperatorSign.class).value(), Function.identity()));
	}
	
	@Override
	public String execute(OperationData data) {
		ArithmeticOperationData operationData = (ArithmeticOperationData) data;
		String res = null;
		Method method = methods.get(operationData.additionalData);
		if(method == null) {
			return String.format("'%s' - no such method", operationData.additionalData);
		}
		try {
			res = (String) method.invoke(null, operationData.operand1, operationData.operand2);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return String.format("%.1f %s %.1f = %s", operationData.operand1, operationData.additionalData, operationData.operand2, res);
	}

}

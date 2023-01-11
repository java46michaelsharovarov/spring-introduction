package telran.spring.calculator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class ArithmeticSimpleOperation extends AbstractOperation {

	private String operationName = "arithmetic operations";
	@Value("${app.message.wrong.arithmetic.operation: wrong arithmetic operation}")
	String wrongArithmeticOperation;
	private static Map<String, BiFunction<Double, Double, String>> methods;
	
	static {
		methods = new HashMap<>();
		methods.put("*", (o1, o2) -> o1 * o2 + "");
		methods.put("-", (o1, o2) -> o1 - o2 + "");
		methods.put("+", (o1, o2) -> o1 + o2 + "");
		methods.put("/", (o1, o2) -> {return o2 != 0 ? (o1 / o2) + "" : "division by zero";});
	}
	
	@Override
	public String execute(OperationData operationData) {
		ArithmeticOperationData data;
		try {
			data = (ArithmeticOperationData) operationData;
			LOG.debug("Executed : {}", mapper.writeValueAsString(data));
		} catch (Exception e) {
			LOG.error(mismatchOperationWithData);
			return String.format(mismatchOperationWithData);
		}
		var method = methods.getOrDefault(data.additionalData,
				(o1, o2) -> String.format("'%s' - %s %s", data.additionalData, wrongArithmeticOperation, methods.keySet()));
		var res = method.apply(data.operand1, data.operand2);
		return res.contains(methods.keySet().toString()) 
				?  res : String.format("%.1f %s %.1f = %s", data.operand1, data.additionalData, data.operand2, res);
	}
	
	@Override
	public String getOperationName() {
		return this.operationName;
	}

	@Override
	public Set<String> getMethodNames() {
		return methods.keySet();
	}

}

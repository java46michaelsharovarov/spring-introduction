package telran.spring.calculator.service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
//("arithmetic operations")
public class ArithmeticSimpleOperation implements Operation {
	
	@Value("${app.message.wrong.arithmetic.operation: wrong arithmetic operation}")
	String wrongArithmeticOperation;
	@Value("${app.message.mismath.data: data mismatch with operation type}")
	String mismmatchOperationWithData;
	private static Map<String, BiFunction<Double, Double, String>> operations;
	
	static {
		operations = new HashMap<>();
		operations.put("*", (o1, o2) -> o1 * o2 + "");
		operations.put("-", (o1, o2) -> o1 - o2 + "");
		operations.put("+", (o1, o2) -> o1 + o2 + "");
		operations.put("/", (o1, o2) -> {return o2 != 0 ? (o1 / o2) + "" : "division by zero";});
	}
		@Override
		public String execute(OperationData operationData) {
			ArithmeticOperationData data;
			try {
				data = (ArithmeticOperationData) operationData;
			} catch (Exception e) {
				return String.format(mismmatchOperationWithData);
			}
			var method = operations.getOrDefault(data.additionalData,
					(o1, o2) -> String.format("'%s' - %s %s", data.additionalData, wrongArithmeticOperation, operations.keySet()));
			var res = method.apply(data.operand1, data.operand2);
			return res.contains(operations.keySet().toString()) 
					?  res : String.format("%.1f %s %.1f = %s", data.operand1, data.additionalData, data.operand2, res);
		}

}

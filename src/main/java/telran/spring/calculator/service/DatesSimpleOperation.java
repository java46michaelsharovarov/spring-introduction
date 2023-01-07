package telran.spring.calculator.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
//("dates operations")
public class DatesSimpleOperation implements Operation {

	@Value("${app.message.wrong.date.operation: wrong date operation}")
	String wrongDateOperation;
	@Value("${app.message.mismath.data: data mismatch with operation type}")
	String mismmatchOperationWithData;	
	private static Map<String, BiFunction<LocalDate, Integer, String>> operations;
	
	static {
		operations = new HashMap<>();
		operations.put("+", (date, days) -> date.plusDays(days).toString());
		operations.put("-", (date, days) -> date.minusDays(days).toString());
	}

	@Override
	public String execute(OperationData operationData) {
		DateDaysOperationData data;
		try {
			data = (DateDaysOperationData) operationData;
		} catch (Exception e) {
			return String.format(mismmatchOperationWithData);
		}
		LocalDate setDate = LocalDate.parse(data.date);
		var method = operations.getOrDefault(data.additionalData, 
				(date, days) -> String.format("'%s' - %s %s", data.additionalData, wrongDateOperation, operations.keySet()));
		var res = method.apply(setDate, data.days);		
		return res.contains(operations.keySet().toString()) 
				? res : String.format("%s %s %d days = %s", data.date, data.additionalData, data.days, res);
	}
	
}

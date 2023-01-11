package telran.spring.calculator.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesSimpleOperation extends AbstractOperation {

	private String operationName = "dates operations";
	@Value("${app.message.wrong.date.operation: wrong date operation}")
	String wrongDateOperation;
	private static Map<String, BiFunction<LocalDate, Integer, String>> methods;
	
	static {
		methods = new HashMap<>();
		methods.put("+", (date, days) -> date.plusDays(days).toString());
		methods.put("-", (date, days) -> date.minusDays(days).toString());
	}

	@Override
	public String execute(OperationData operationData) {
		DateDaysOperationData data;
		try {
			data = (DateDaysOperationData) operationData;
			LOG.debug("Executed : {}", mapper.writeValueAsString(data));
		} catch (Exception e) {
			LOG.error(mismatchOperationWithData);
			return String.format(mismatchOperationWithData);
		}
		LocalDate setDate = LocalDate.parse(data.date);
		var method = methods.getOrDefault(data.additionalData, 
				(date, days) -> String.format("'%s' - %s %s", data.additionalData, wrongDateOperation, methods.keySet()));
		var res = method.apply(setDate, data.days);		
		return res.contains(methods.keySet().toString()) 
				? res : String.format("%s %s %d days = %s", data.date, data.additionalData, data.days, res);
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

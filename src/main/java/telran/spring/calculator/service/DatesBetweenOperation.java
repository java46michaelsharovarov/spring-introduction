package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.BiFunction;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesBetweenOperation implements Operation {
	
	private String operationName = "between dates";
	@Value("${app.message.mismath.data: data mismatch with operation type}")
	String mismmatchOperationWithData;

	@Override
	public String execute(OperationData operationData) {
		DatesOperationData data;
		try {
			data = (DatesOperationData) operationData;
		} catch (Exception e) {
			return String.format(mismmatchOperationWithData);
		}	
		LocalDate dateFrom = LocalDate.parse(data.dateFrom);
		LocalDate dateTo = LocalDate.parse(data.dateTo);		
		return String.format("between %s - %s number of days %d", 
				dateFrom, dateTo, ChronoUnit.DAYS.between(dateFrom, dateTo));
	}
	
	@Override
	public String getOperationName() {
		return this.operationName;
	}

	@Override
	public Map<String, BiFunction<Double, Double, String>> getMethods() {
		return null;
	}

}

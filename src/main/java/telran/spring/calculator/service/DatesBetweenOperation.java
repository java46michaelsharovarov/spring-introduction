package telran.spring.calculator.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service
public class DatesBetweenOperation extends AbstractOperation {
	
	private String operationName = "between dates";

	@Override
	public String execute(OperationData operationData) {
		DatesOperationData data;
		try {
			data = (DatesOperationData) operationData;
		} catch (Exception e) {
			return String.format(mismatchOperationWithData);
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
	public Set<String> getMethodNames() {
		return null;
	}

}

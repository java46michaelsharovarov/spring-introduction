package telran.spring.calculator.service;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Map;

import org.springframework.stereotype.Service;

import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.dto.OperationData;

@Service("between dates")
public class DatesBetweenOperation implements Operation {

	@Override
	public String execute(OperationData data) {
		DatesOperationData operationData = (DatesOperationData) data;
		LocalDate dateFrom;
		LocalDate dateTo;
		try {
			dateFrom = LocalDate.parse(operationData.dateFrom, DateTimeFormatter.ISO_LOCAL_DATE);
			dateTo = LocalDate.parse(operationData.dateTo, DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (Exception e) {
			return "date format should be - 'yyyy-mm-dd'";
		}
		return String.format("between %s - %s number of days %d", 
				dateFrom, dateTo, ChronoUnit.DAYS.between(dateFrom, dateTo));
	}

	@Override
	public Map<String, Method> getMethods() {
		return null;
	}

}

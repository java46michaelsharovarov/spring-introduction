package telran.spring.calculator.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.Getter;
import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.OperationData;
import telran.spring.calculator.service.annotations.OperatorSign;

@Getter
@Service("dates operations")
public class DatesSimpleOperation implements Operation {
	
	private Map<String, Method> methods;

	public DatesSimpleOperation() {
		this.methods = Arrays.stream(DatesSimpleOperationMethods.class.getDeclaredMethods())
				.collect(Collectors.toMap(m -> m.getAnnotation(OperatorSign.class).value(), Function.identity()));
	}

	@Override
	public String execute(OperationData data) {
		DateDaysOperationData operationData = (DateDaysOperationData) data;
		LocalDate date = LocalDate.parse(operationData.date, DateTimeFormatter.ISO_LOCAL_DATE);
		String res = null;
		Method method = methods.get(operationData.additionalData);
		if(method == null) {
			return String.format("'%s' - no such method", operationData.additionalData);
		}
		try {
			res = (String) method.invoke(null, date, operationData.days);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return String.format("%s %s %d days = %s", operationData.date, operationData.additionalData, operationData.days, res);
	}
	
	/****************solution by Granovsky********************/
	
//	@Override
//	public String execute(OperationData data) {
//		String res = "";
//		DateDaysOperationData dateData = (DateDaysOperationData) data;
//		try {
//			LocalDate date = LocalDate.parse(dateData.date);
//			int days = dateData.days;
//			if(data.additionalData.equalsIgnoreCase("before")) {
//				days = -days;
//			}
//			res = date.plusDays(days).toString();
//		} catch (Exception e) {
//			res = "Wrong Date format should be YYYY-MM-DD";
//		} 		
//		return res;
//	}
	
}

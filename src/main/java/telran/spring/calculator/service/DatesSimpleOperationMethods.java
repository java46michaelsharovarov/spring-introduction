package telran.spring.calculator.service;

import java.time.LocalDate;


import telran.spring.calculator.service.annotations.OperatorSign;

public class DatesSimpleOperationMethods {

	@OperatorSign("-")
	static String subtraction(LocalDate date, int days) {
		return date.minusDays(days).toString();
	}
	
	@OperatorSign("+")
	static String addition(LocalDate date, int days) {
		return date.plusDays(days).toString();
	}
}

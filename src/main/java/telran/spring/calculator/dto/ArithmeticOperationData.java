package telran.spring.calculator.dto;

import jakarta.validation.constraints.Digits;

public class ArithmeticOperationData extends OperationData {

	@Digits(fraction = 3, integer = 15)
	public double operand1;
	
	@Digits(fraction = 3, integer = 15)
	public double operand2;
}

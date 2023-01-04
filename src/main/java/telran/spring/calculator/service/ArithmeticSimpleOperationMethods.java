package telran.spring.calculator.service;

import telran.spring.calculator.service.annotations.OperatorSign;

public class ArithmeticSimpleOperationMethods {

	@OperatorSign("-")
	static String subtraction(double operand1, double operand2) {
		return (operand1 - operand2) + "";
	}
	
	@OperatorSign("+")
	static String addition(double operand1, double operand2) {
		return (operand1 + operand2) + "";
	}
	
	@OperatorSign("*")
	static String multiplication(double operand1, double operand2) {
		return (operand1 * operand2) + "";
	}
	
	@OperatorSign("/")
	static String division(double operand1, double operand2) {
		return operand2 != 0 ? (operand1 / operand2) + "" : "division by zero";
	}
}

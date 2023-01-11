package telran.spring.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import telran.spring.calculator.dto.ArithmeticOperationData;
import telran.spring.calculator.dto.DateDaysOperationData;
import telran.spring.calculator.dto.DatesOperationData;
import telran.spring.calculator.service.*;

@SpringBootTest
class CalculatorServicesTests {

	private static ArithmeticOperationData data;
	private static final String ADDITION = "+";
	private static final String DATES_OPERATIONS = "dates operations";
	private static final String DATE_2 = "2023-01-19";
	private static final String DATE_1 = "2023-01-01";
	private static final String MULTIPLICATIONS = "*";
	private static final String BETWEEN_DATES = "between dates";
	private static final String ARITHMETIC_OPERATIONS = "arithmetic operations";
	@Autowired
	ArithmeticSimpleOperation arithmeticSimpleOperation;
	@Autowired
	DatesBetweenOperation datesBetweenOperation;
	@Autowired
	DatesSimpleOperation datesSimpleOperation;
	@Value("${app.message.mismath.data}")
	String mismatchOperationWithData;
	
	@BeforeEach
	void beforeEach() {
		data = new ArithmeticOperationData();
		data.operationName = ARITHMETIC_OPERATIONS;
		data.additionalData = MULTIPLICATIONS;
		data.operand1  = 15.0;
		data.operand2  = 3.0;	
	}
	
	@Test
	void arithmeticSimpleOperationTest() {			
		assertTrue(arithmeticSimpleOperation.execute(data).contains("45.0"));
	}
	
	@Test
	void arithmeticSimpleOperationWrongDTOTest() {
		DateDaysOperationData data = new DateDaysOperationData();
		data.operationName = ARITHMETIC_OPERATIONS;
		data.additionalData = MULTIPLICATIONS;
		data.date = DATE_1;
		data.days = 5;
		assertTrue(arithmeticSimpleOperation.execute(data).contains(mismatchOperationWithData));
	}
	
	@Test
	void datesBetweenOperationTest() {
		DatesOperationData data = new DatesOperationData();
		data.operationName = BETWEEN_DATES;
		data.additionalData = null;
		data.dateFrom = DATE_1;
		data.dateTo = DATE_2;
		assertTrue(datesBetweenOperation.execute(data).contains("18"));
	}
	
	@Test
	void datesBetweenOperationWrongDTOTest() {
		data.operationName = BETWEEN_DATES;
		assertTrue(datesBetweenOperation.execute(data).contains(mismatchOperationWithData));
	}
	
	@Test
	void datesSimpleOperationTest() {
		DateDaysOperationData data = new DateDaysOperationData();
		data.operationName = DATES_OPERATIONS;
		data.additionalData = ADDITION;
		data.date = DATE_1;
		data.days = 18;
		assertTrue(datesSimpleOperation.execute(data).contains(DATE_2));
	}
	
	@Test
	void datesSimpleOperationWrongDTOTest() {
		data.operationName = DATES_OPERATIONS;
		assertTrue(datesSimpleOperation.execute(data).contains(mismatchOperationWithData));
	}

}

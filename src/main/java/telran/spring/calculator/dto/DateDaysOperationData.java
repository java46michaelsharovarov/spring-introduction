package telran.spring.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class DateDaysOperationData extends OperationData {

	@NotNull
	@Pattern(regexp = "\\d{4}-(0\\d|1[012])-(0\\d|[12]\\d|3[01])")
	public String date;
	
	@Positive
	public int days;
	
}

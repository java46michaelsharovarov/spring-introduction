package telran.spring.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public class DateDaysOperationData extends OperationData {

	@NotNull
	@Pattern(regexp = DATE_PATTERN)
	public String date;
	
	@Positive
	public int days;
	
}

package telran.spring.calculator.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class DatesOperationData extends OperationData {

	@NotNull
	@Pattern(regexp = "\\d{4}-(0\\d|1[012])-(0\\d|[12]\\d|3[01])")
	public String dateFrom;
	
	@NotNull
	@Pattern(regexp = "\\d{4}-(0\\d|1[012])-(0\\d|[12]\\d|3[01])")
	public String dateTo;
	
}

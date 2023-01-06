package telran.spring.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class EmailMessage extends Message {
	
	@Email
	@NotEmpty
	public String emailAddress;
	
}

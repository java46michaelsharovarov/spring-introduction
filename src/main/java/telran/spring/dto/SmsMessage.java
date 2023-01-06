package telran.spring.dto;

import jakarta.validation.constraints.Pattern;

public class SmsMessage extends Message {

	@Pattern(regexp = "(\\+972\\s*-?|0)(5\\d|7[2-7])(-?\\d){7}")
	public String phoneNumber;
	
}

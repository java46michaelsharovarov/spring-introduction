package telran.spring.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
@JsonSubTypes({ @Type(EmailMessage.class), @Type(SmsMessage.class), @Type(TcpMessage.class) })

public class Message {

	public String type;
	public String text;

}

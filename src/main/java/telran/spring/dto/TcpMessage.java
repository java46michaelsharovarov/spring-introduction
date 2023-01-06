package telran.spring.dto;

import jakarta.validation.constraints.*;

public class TcpMessage extends Message {

	@Pattern(regexp = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])")
	public String ipAddress;
	
	@Min(1024)
	@Max(49151)
	public int port;
	
}

package telran.spring.service;

import org.springframework.stereotype.Service;

import telran.spring.view.InputOutput;

@Service("TCP")
public class TcpSender implements Sender {

	@Override
	public void send(InputOutput io, String message) {
		String ipAddress = io.readPredicate("Enter IP address", "Wrong IP address", TcpSender::ipV4Predicate);
		int port = io.readInt("Enter port number", "Wrong port number", 1024, 49151);
		io.writeLine(String.format("message: '%s' has been sent to tcp address: %s:%d\n", message, ipAddress, port));
	}

	private static boolean ipV4Predicate(String ip) {
		return ip.matches(ipV4RegEx());
	}

	private static String ipV4RegEx() {
		return String.format("((%1$s)\\.){3}(%1$s)", ipV4Part());
	}

	private static String ipV4Part() {
		return "\\d\\d?|[01]\\d{2}|2[0-4]\\d|25[0-5]";
	}

}
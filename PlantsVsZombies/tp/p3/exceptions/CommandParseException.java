package tp.p3.exceptions;

public class CommandParseException extends Exception{ // Sintaxis de comando erronea

	public CommandParseException() {super();}
	
	public CommandParseException(String message) {super(message);}
	
	public CommandParseException(String message, Throwable cause) {super(message,cause);}
	
	public CommandParseException(Throwable cause) {super(cause);}
	
	public CommandParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
	
}

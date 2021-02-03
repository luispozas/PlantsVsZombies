package tp.p3.exceptions;

public class InvalidPositionException extends Exception{ //Usada al intentar añadir un objeto con posiciones invalidas en el tablero
	public InvalidPositionException() {super();}
	
	public InvalidPositionException(String message) {super(message);}
	
	public InvalidPositionException(String message, Throwable cause) {super(message,cause);}
	
	public InvalidPositionException(Throwable cause) {super(cause);}
	
	public InvalidPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}

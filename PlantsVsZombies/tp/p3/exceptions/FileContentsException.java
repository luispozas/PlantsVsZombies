package tp.p3.exceptions;

public class FileContentsException extends Exception{ // Usada al encontrar datos de fichero erroneos

	public FileContentsException() {super();}
	
	public FileContentsException(String message) {super(message);}
	
	public FileContentsException(String message, Throwable cause) {super(message,cause);}
	
	public FileContentsException(Throwable cause) {super(cause);}
	
	public FileContentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}

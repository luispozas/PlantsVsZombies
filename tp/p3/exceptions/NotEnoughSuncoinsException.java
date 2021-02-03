package tp.p3.exceptions;

public class NotEnoughSuncoinsException extends Exception{  // Usada al intentar comprar una planta sin suncoins suficientes
	
    public NotEnoughSuncoinsException() {super();}
	
	public NotEnoughSuncoinsException(String message) {super(message);}
	
	public NotEnoughSuncoinsException(String message, Throwable cause) {super(message,cause);}
	
	public NotEnoughSuncoinsException(Throwable cause) {super(cause);}
	
	public NotEnoughSuncoinsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message,cause,enableSuppression,writableStackTrace);
	}
}

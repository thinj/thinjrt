package java.lang;

             
public class ArithmeticException extends RuntimeException {
	public ArithmeticException(String cause) {
		super(cause);
	}

	public ArithmeticException() {
		this(null);
	}
}

package java.lang;

public class NullPointerException extends RuntimeException {
	public NullPointerException(String cause) {
		super(cause);
	}

	public NullPointerException() {
		this(null);
	}
}

package java.lang;

public class IndexOutOfBoundsException extends RuntimeException {
	public IndexOutOfBoundsException(String cause) {
		super(cause);
	}

	public IndexOutOfBoundsException() {
		this(null);
	}
}

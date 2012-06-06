package java.lang;

public class ArrayIndexOutOfBoundsException extends IndexOutOfBoundsException {
	public ArrayIndexOutOfBoundsException(String cause) {
		super(cause);
	}

	public ArrayIndexOutOfBoundsException(int index) {
		super("Index out of bounds: " + index);
	}

	public ArrayIndexOutOfBoundsException() {
		this(null);
	}
}

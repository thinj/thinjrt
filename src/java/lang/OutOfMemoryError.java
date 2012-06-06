package java.lang;

public class OutOfMemoryError extends VirtualMachineError {
	// Preallocate a single instance:
	private static OutOfMemoryError INSTANCE = new OutOfMemoryError();
	public OutOfMemoryError(String cause) {
		super(cause);
	}

	public OutOfMemoryError() {
	}
	
	/**
	 * Reachable from native code.
	 * @return Returns the preallocated instance of this exception
	 */
	private static OutOfMemoryError getInstance() {
		return INSTANCE;
	}
}

package java.lang;

/**
 * The base class for all exceptions
 */
public class Throwable {
	private String aCause;
	private int aStackTrace[];

	public Throwable() {
		this(null);
	}

	public Throwable(String cause) {
		aCause = cause;
		int depth = getStackTraceDepth();
		aStackTrace = new int[depth];
		for (int i = 0; i < depth; i++) {
			aStackTrace[i] = getStackTraceElement(i);
		}
	}

	/**
	 * This method returns the 'program counter' at the given depth.
	 * 
	 * @param depth The stack frame depth
	 * @return The 'program counter' at the given depth.
	 */
	private native int getStackTraceElement(int depth);

	/**
	 * This method returns the number of frames / depth on the stack
	 * 
	 * @return The number of frames / depth on the stack
	 */
	private native int getStackTraceDepth();

	public void printStackTrace() {
		System.err.print(getClass());
		System.err.println(aCause != null ? (": " + aCause) : "");

		boolean addComma = false;
		for (int pc : aStackTrace) {
			if (addComma) {
				System.err.print(",");
			} else {
				System.err.print("  at: ");
				addComma = true;
			}
			System.err.print(pc);
		}
		System.err.println("");
	}

	@Override
	public String toString() {
		return "Throwable: " + aCause;
	}
}

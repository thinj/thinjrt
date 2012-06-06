package java.lang;

public final class Class {
	private static Class[] aAllClasses;

	// The number of this class:
	private int aClassId;

	private static native Class[] getAllClasses();

	static {
		// All referenced Class'es shall be referenced; otherwise good old gc will dispose them:
		aAllClasses = getAllClasses();
	}

	/**
	 * Public no-constructor
	 */
	private Class() {
	}

	public final String getName() {
		return "#" + aClassId;
	}

	@Override
	public String toString() {
		return getName();
	}
}

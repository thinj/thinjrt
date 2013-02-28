package java.lang;

public final class Class {
	// The number of this class:
	private int aClassId;
	
	// An array of all classes - is set from native code:
	private static Class[] aAllClasses;
	
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

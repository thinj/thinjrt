package thinj;

public class VirtualMachine {
	/**
	 * This method returns the size of heap, in bytes
	 * 
	 * @return The size of heap, in bytes
	 */
	public static native int getMaxHeap();

	/**
	 * This method returns the current heap usage, in count of bytes
	 * 
	 * @return The current heap usage, in count of bytes
	 */
	public static native int getHeapUsage();
}

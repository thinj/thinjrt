package java.lang;

/**
 * Thread class. <br/>
 * TODO The id generation shall be atomic<br/>
 * TODO The thread collection shall be modified atomically<br/>
 */
public class Thread implements Runnable {
	// TODO For some obscure reasons enum does not work; so for now we use simple integers as
	// states:
	private static final int StateNew = 0;
	private static final int StateRunnable = 1;
	private static final int StateTerminated = 2;

	// //////////////////////////////////////////////////////////
	// Static attributes:
	// //////////////////////////////////////////////////////////
	private static Thread aAllThreads = null;

	private static Thread aCurrentThread;
	private static long aThreadIdGenerator = 0;

	// //////////////////////////////////////////////////////////
	// Instance attributes:
	// //////////////////////////////////////////////////////////
	// Instance variable: The unique thread id:
	private long aId;

	// The runnable to run:
	private final Runnable aRunnable;

	// The context for this thread:
	private byte[] aContext;

	// The stack for this thread:
	private byte[] aStack;

	// Simple thread collection:
	private Thread aNextThread;

	// The state of the thread:
	private int aState;

	static {
		// There is always only one thread running here - so no synchronisation is needed

		// Convert the running thread into the main thread instance:
		aCurrentThread = new Thread();
		aCurrentThread.aState = StateRunnable;

		// We don't know the size of the context, so get some help the the C-world:
		aCurrentThread.aContext = allocEmptyContext();
		getCurrentContext(aCurrentThread.aContext);

		aCurrentThread.aStack = getMainStack();

		// Simple list of threads:
		addToThreadCollection(aCurrentThread);

		// Let all the horses run:
		startScheduling();
	}

	/**
	 * This method adds the thread to the collection of all threads
	 * 
	 * @param thread The thread to add
	 */
	private static void addToThreadCollection(Thread thread) {
		// TODO Atomic access !
		thread.aNextThread = aAllThreads;
		aAllThreads = thread;
	}

	/**
	 * Constructor.
	 */
	public Thread() {
		this(null);
	}

	/**
	 * Constructor
	 * 
	 * @param runnable To be run when the thread is started.
	 */
	public Thread(Runnable runnable) {
		aRunnable = runnable;
		// TODO Atomic access
		aId = aThreadIdGenerator++;
		aState = StateNew;
	}

	/**
	 * This method starts the thread.
	 */
	public void start() {
		// TODO Synchronise!
		if (aState == StateNew) {
			// Allocate and prepare the stack for this thread:
			aStack = allocStack();
			// System.out.println("thraed stack: " + aStack);

			// Allocate and prepare the VM context for this thread:
			aContext = allocStartContext();

			// Change thread state:
			aState = StateRunnable;

			// There is always at least one thread; the current thread:
			addToThreadCollection(this);

			// Nothing more to do; the scheduling will take over from here.
		} else {
			throw new IllegalThreadStateException("Illegal thread state: " + aState);
		}
	}

	/**
	 * Package scope - not to be reached by other java classes. Will be called from native code.
	 */
	void runFromNative() {
		try {
			if (aRunnable != null) {
				aRunnable.run();
			} else {
				run();
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		System.out.println("thread dying..." + this);

		aState = StateTerminated;

		// Force rescheduling (and never come back to this thread):
		Thread.yield();
	}

	/**
	 * @return The unique thread id
	 */
	public long getId() {
		return aId;
	}

	@Override
	public void run() {
	}

	/**
	 * @return The current thread
	 */
	public static Thread currentThread() {
		return aCurrentThread;
	}

	/**
	 * @return The current thread VM context. Can be called at any time, but is intended for use
	 *         during clinit.
	 */
	private static native void getCurrentContext(byte[] context);

	/**
	 * @return The main thread VM stack. Shall only be called during clinit of this class!
	 */
	private static native byte[] getMainStack();

	/**
	 * This method allocates the JVM context for this thread. This includes setting the program
	 * counter to point at the {@link #runFromNative()} method
	 * 
	 * @return The object containing prepared VM context.
	 */
	private native byte[] allocStartContext();

	/**
	 * This method allocates the an empty context for this thread.
	 * 
	 * @return The object containing an unprepared VM context.
	 */
	private static native byte[] allocEmptyContext();

	/**
	 * This method allocates a Thread stack and pushes the thread object onto the stack thus
	 * preparing the stack for a call of the {@link #runFromNative()} method
	 * 
	 * @return The object containing the prepared VM stack.
	 */
	private native byte[] allocStack();

	/**
	 * This method reschedules
	 */
	public static native void yield();

	/**
	 * This method will enable scheduling. Used by GC to determine how to get hold of current stack
	 * etc. and to enable the 'pre-emptive' scheduling.
	 */
	private native static void startScheduling();
	
}

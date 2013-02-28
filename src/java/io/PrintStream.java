package java.io;

public class PrintStream {
	private static native void outStringLf(String s);

	private static native void outString(String s);

	public void println(String s) {
		outStringLf(s);
	}

	public void print(String s) {
		outString(s);
	}

	public void println() {
		println("");
	}

	public void print(boolean b) {
		print(b ? "true" : "false");
	}

	public void println(boolean b) {
		println(b ? "true" : "false");
	}

	public void print(int i) {
		print("" + i);
	}

	public void println(int i) {
		println("" + i);
	}

	public void print(long j) {
		StringBuilder sb = new StringBuilder();
		sb.append(j);
		print(sb);
	}

	public void println(long j) {
		StringBuilder sb = new StringBuilder();
		sb.append(j);
		println(sb);
	}

	public void print(Object o) {
		outString(o == null ? "null" : o.toString());
	}

	public void println(Object o) {
		outStringLf(o == null ? "null" : o.toString());
	}
}

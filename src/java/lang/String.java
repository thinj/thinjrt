package java.lang;

import java.util.Arrays;

public class String {

	private char[] value;

	public String(char value[], int offset, int count) {		
		this.value = new char[count];
		for (int i = 0; i < count; i++) {
			this.value[i] = value[i + offset];
		}
	}

	public String(char value[]) {
		this.value = value;
	}

	public int length() {
		return value.length;
	}

	public static String valueOf(Object o) {
		return o == null ? "null" : o.toString();
	}

	public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
		for (int i = srcBegin; i < srcEnd; i++) {
			dst[dstBegin + i - srcBegin] = value[i];
		}
	}

	@Override
	public String toString() {
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(value);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		String other = (String) obj;
		
		if (!Arrays.equals(value, other.value))
			return false;
		return true;
	}
}

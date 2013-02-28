package java.lang;

public class StringBuilder {
	private char[] aValue;

	public StringBuilder(String s) {
		aValue = new char[s.length()];
		s.getChars(0, s.length(), aValue, 0);
	}

	public StringBuilder() {
		aValue = new char[0];
	}

	public StringBuilder append(String s) {
		if (s == null) {
			return append("null");
		} else {
			char[] newValue = new char[aValue.length + s.length()];

			for (int i = 0; i < aValue.length; i++) {
				newValue[i] = aValue[i];
			}

			s.getChars(0, s.length(), newValue, aValue.length);
			aValue = newValue;

			return this;
		}
	}

	public StringBuilder append(Object o) {
		return append(o == null ? "null" : o.toString());
	}

	public StringBuilder append(boolean b) {
		return append(b ? "true" : "false");
	}

	public StringBuilder append(int value) {	
		// TODO Fails when using Integer.MIN_VALUE!!!
		boolean signed = value < 0;
		if (signed) {
			value = -value;
		}
		char[] buf = new char[11];
		int ix = buf.length;
		do {
			buf[--ix] = (char) ('0' + (value % 10));
			value = value / 10;
		} while (value > 0);

		if (signed) {
			buf[--ix] = '-';
		}

		append(new String(buf, ix, buf.length - ix));
		return this;
	}

	public StringBuilder append(long j) {
		// Veeeeeeerrryyyyyy inefficient, but often this code reveals bugs in GC, so wait with optimisation:
		String s = "";
		if (j < 0) {
			append("-");
			j = -j;
		}
		String[] numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		do {
			s = numbers[(int)(j % 10)] + s;
			j = j / 10;
		} while (j > 0);
		append(s);
		return this;
	}

	public String toString() {
		return new String(aValue);
	}
}

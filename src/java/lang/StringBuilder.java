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

	public StringBuilder append(int i) {
		// Veeeeeeerrryyyyyy inefficient:
		String s = "";
		if (i < 0) {
			append("-");
			i = -i;
		}
		String[] numbers = new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		do {
			s = numbers[i % 10] + s;
			i = i / 10;
		} while (i > 0);
		append(s);
		return this;
	}

	public String toString() {
		return new String(aValue);
	}
}

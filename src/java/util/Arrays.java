package java.util;

public class Arrays {

	public static int hashCode(char[] value) {
		int h = 37;
		for (char c : value) {
			h = h * 2 + c;
		}
		return h;
	}

	public static boolean equals(char[] value1, char[] value2) {
		if (value1 == value2) {
			return true;
		}
		if (value1 != null && value2 == null) {
			return false;
		}
		
		if (value1 == null && value2 != null) {
			return false;
		}

		if (value1.length != value2.length) {
			return false;
		}
		
		boolean eq = true;
		for (int i = 0; i < value1.length; i++) {
			if (value1[i] != value2[i]) {
				eq = false;
				break;
			}
		}

		return eq;
	}
}

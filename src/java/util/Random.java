package java.util;

/**
 * See http://www.codeproject.com/Articles/25172/Simple-Random-Number-Generation
 * 
 */
public class Random {
	private int m_z = 12345;
	private int m_w = 54321;

	public int nextInt() {
		m_z = 36969 * (m_z & 65535) + (m_z >> 16);
		m_w = 18000 * (m_w & 65535) + (m_w >> 16);
		// return (m_z << 16) + m_w;
		int retval = (m_z << 16) + m_w;
		return 0x7fffffff & (retval >> 1);
	}

	public int nextInt(int n) {
		int scale = ((-(0x80000000 / n)) & 0x7fffffff);
		return nextInt() / scale;
	}
}

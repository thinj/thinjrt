package java.lang;

/**
 * 
 * Alzheimers in memoriam: javah -classpath . tinyjvm.rt.Object
 * 
 * @author hammer
 * 
 */
public class Object {
	public Object() {
	}

	public boolean equals(Object o) {
		return this == o;
	}

	public native int hashCode();
	
	public native Class getClass();
	
	public String toString() {
		return getClass().getName()+ "@" + hashCode();
	}
}

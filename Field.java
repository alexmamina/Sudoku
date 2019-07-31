public class Field {

	public int value;
	public final boolean initial;
	
	
	public Field() {
		value = 0;
		initial = false;
	}	
	public Field(int v, boolean in) {
		setValue(v);
		initial = in;
	}	
	public int getValue() {
		return value;
	}
	public void setValue(int x) {
		value = x;
	}	
	public boolean getInitial() {
		return initial;
	}
	public String toString() {
		String a = "'";
		String s = value + " ";
		if (initial) {
			return value+a;
		}
		else return s;
	}
}

import java.util.*;
import java.awt.*;
public class MatteBorder {

	int top, left, bottom, right;
	Color matteColor;
	public MatteBorder(int top,
            int left,
            int bottom,
            int right,
            Color matteColor) {
		this.top = top;
		this.bottom = bottom;
		this.left = left;
		this.right = right;
		this.matteColor = matteColor;
	}
	
}

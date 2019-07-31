
public class XGameGrid extends GameGrid{

	public XGameGrid(int[][] newGrid) {
		super(newGrid);
	}
	public XGameGrid(String path) {
		super(path);
	}
	public XGameGrid(GameGrid agrid) {
		super(agrid);
	}
	
	@Override
	public String toString() {
		String nums = "     1   │  2  │  3       4  │  5  │  6       7  │  8  │  9   \n";
	     for (int y = 0; y < GRID_DIM; y++) {
	       nums = nums + 
	       (y+1 + "  │ ");
	          for (int x = 0; x < GRID_DIM; x++) {
	        	if (x+y==GRID_DIM-1 || x==y) {
	        		nums += "["+grid[y][x]+"]  ";
	        	}
	        	else nums += " "+grid[y][x] + "   ";
	        	if (x % SUBGRID_DIM == 2) {
	        		nums += "  ";
	        	}
	        	
	          }
	          if (y % SUBGRID_DIM == 2) nums += "\n\n";
	      	nums += "\n";
	     }
	     return nums;
	}
	
	@Override
	
	protected boolean isValid(int x, int y, int n) {
		boolean first = super.isValid(x, y, n);
		return first && checkDiagonal(x, y, n);
		
	}
	
	private boolean checkDiagonal(int x, int y, int n) {
		boolean check = true;
		if (x == y) {
			for (int i = 0; i < GRID_DIM; i++) {
				if (grid[i][i].getValue() == n) return false;
				else check = check && true;
			}
		} else if (x+y == GRID_DIM-1) {
			for (int i = 0; i < GRID_DIM; i++) {
				if (grid[i][GRID_DIM-1-i].getValue() == n) return false;
				else check = check && true;
			}
		}
		
		return check;
	}
	

}

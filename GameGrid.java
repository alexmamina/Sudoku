import java.util.Objects;

public abstract class GameGrid {

	// Constants for coordinate boundaries and Sudoku numbers
    public static final int GRID_DIM = 9;
    public static final int SUBGRID_DIM = GRID_DIM / 3;
    public static final int MAX_VAL = 9;
    public static final int MIN_VAL = 1;
    public static final int EMPTY_VAL = 0;
    public int initialValues;
    
    
	protected final Field[][] grid;
	
	public String toString() {
		String nums = "     1  │  2 │  3       4  │  5 │  6       7  │  8 │  9   \n" +
				"   ┌────┼────┼────┐   ┌────┼────┼────┐   ┌────┼────┼────┐\n";
	     for (int y = 0; y < grid.length; y++) {
	       nums = nums + 
	       (y+1 + "  │ ");
	          for (int x = 0; x < GRID_DIM; x++) {
//	        	
	            if ((x == 2) || (x == 5)) {
	              nums = nums + (grid[y][x] + " │   │ ");
	            } else if (x == 8 && (y % 3 != 2)) {
	            	nums = nums + (grid[y][x] + " │\n─  ├────┼────┼────┤   ├────┼────┼────┤   ├────┼────┼────┤");
	            }
	            else {
	              nums = nums + (grid[y][x] + " │ ");
	            }
	          }
	          if (y == 2 || y == 5) {
	            nums = nums + "\n   └────┴────┴────┘   └────┴────┴────┘   └────┴────┴────┘\n" +
	            		"   ┌────┬────┬────┐   ┌────┬────┬────┐   ┌────┬────┬────┐\n";
	          } else {
	            nums = nums + "\n";
	          }	        
	        }
	     nums += "   └────┴────┴────┘   └────┴────┴────┘   └────┴────┴────┘";
	        return nums;
	}
	
	private Field[][] initialiseGrid(int[][] newGrid) {
		Field[][] grid = new Field[GRID_DIM][GRID_DIM];
		for (int i = 0; i<GRID_DIM; i++) {
			for (int j = 0; j<GRID_DIM; j++) {
				if (newGrid[i][j] == 0) {
				grid[i][j] = new Field();
				} else {
					grid[i][j] = new Field(newGrid[i][j], true);
					
					
				}
			}
				
		}
		return grid;
	}

	public GameGrid(int[][] newGrid) {
		Objects.requireNonNull(newGrid);
		grid = initialiseGrid(newGrid);
	}

	public GameGrid(String path) {
		Objects.requireNonNull(path);
		grid = initialiseGrid(IOUtils.loadFromFile(path));
	}
	
	
	public GameGrid(GameGrid agrid) {
		Objects.requireNonNull(agrid);		
		grid = new Field[GRID_DIM][GRID_DIM];
		for (int i = 0; i < GRID_DIM; i++) {
			for (int j = 0; j < GRID_DIM; j++) {
				this.grid[i][j] = new Field(agrid.getField(i, j), agrid.isInitial(i,j));
				
			}
		}
	}
	
	public int getField(int x, int y)  {

		if (x < 0 || x > GRID_DIM || y < 0 || y > GRID_DIM) {
				throw new IllegalArgumentException("Coordinates out of bounds");
			} else {
				return grid[y][x].getValue();
			}
	}
	
	public boolean isInitial(int x, int y) {
		if (x < 0 || x > GRID_DIM-1 || y < 0 || y > GRID_DIM-1) {
			throw new IllegalArgumentException("Coordinates out of bounds");
		} else {
		return grid[y][x].getInitial();
		}
	}
	
	public boolean setField(int x, int y, int r) {
		if (x < 0 || x > GRID_DIM-1 || y < 0 || y > GRID_DIM-1 || r < MIN_VAL || r > MAX_VAL) {
			throw new IllegalArgumentException("Value(s) out of bounds");
		} else if (isValid(x,y,r) && !isInitial(x,y)) {
		grid[y][x].setValue(r);
		return true;
		} 
		return false;
		
	}
	public void clearField(int x, int y) {
		if (x < 0 || x > GRID_DIM-1 || y < 0 || y > GRID_DIM-1) {
				throw new IllegalArgumentException("Value out of bounds");
			} else if (!isInitial(x,y)) {
				grid[y][x].setValue(0);
			} else System.out.println("You're changing an initial value");

	 }
	 
	 
    // Check the row
    private boolean checkRow(int x, int y, int n) {
    	boolean check = true;
    	for (int i = 0; i < GRID_DIM; i++) {
    		if (grid[y][i].getValue() == n) {
    			check = false;
    			//System.out.println("Conflicting number at row "+(y+1)+" and col "+(i+1));
    		} else { 
    			check = check && true;
    		}
    	}
    	return check;
    }
    
    // Check the column
    private boolean checkColumn(int x, int y, int n) {
    	boolean check = true;
    	for (int i = 0; i < GRID_DIM; i++) {
    		if (grid[i][x].getValue() == n) {
    			check = false;
    		//	System.out.println("Conflicting number at row "+(i+1)+" and col "+(x+1));
    		} else { check = check && true;
    	}	
    }
    	return check;
    }
    
    
    // Check the block
    private boolean checkSubGrid(int x, int y, int n) {
    	boolean check = true;

    	int a = x - x % 3;
    	int b = (y) - y % 3;
    	for (int j = b; j < (b+3); j++) {
    		for (int i = a; i < (a+3); i++) {
    			if (grid [j][i].getValue() == n) {
    				check = false;
    				//System.out.println("Conflicting number at row "+(j+1)+" and col "+(i+1));
    			} else {
    				check = true && check;
    			}
    		}
    	}
    	return check;
    }
 
    
    protected boolean isValid(int x, int y, int n) {
    	boolean result = true;
    	result = checkRow(x, y, n) && checkColumn(x, y, n) && checkSubGrid(x, y, n);
    	return result;
    } 
 
    
    public static int getFreeVariables(GameGrid my) {
    	int free = 0;
    	for (int i = 0; i < GRID_DIM; i++) {
    		for (int j = 0; j < GRID_DIM; j++) {
    			if (my.isInitial(i,j)) free++;
    		}
    	}
    	return free;
    }
	

    public static GameGrid copyGameGrid(GameGrid grid) {
		if (grid instanceof RGameGrid) {
			RGameGrid game = new RGameGrid((RGameGrid) grid);
			return game;
		} else {
			XGameGrid game = new XGameGrid((XGameGrid) grid);
			return game;
		}
		
	}


}

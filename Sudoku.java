import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class Sudoku {

	private static String solution;
	public static GameGrid game;
    /**
     * Print a game menu message to the console.
     */
    public static void printMenu() {
        System.out.print("\n" +
                "1. Set field\n" +
                "2. Clear field\n" +
                "3. Print game\n" +
                "4. Find solutions\n" +
                "5. Exit\n\n" +
                "Select an action [1-5]: ");
    }

    public static void chooseGame() {
    	System.out.print("Select game mode:\n" +
                "1. Regular sudoku\n" +
                "2. X Sudoku\n"+
                "Select an action [1-2]: ");
    	
    }
    /**
     * Read a single integer value from the console and return it.
     * This function blocks the program's execution until a user
     * entered a value into the command line and confirmed by pressing
     * the Enter key.
     * @return The user's input as integer or -1 if the user's input was invalid.
     */
    public static int parseInput() {
        Scanner in = new Scanner(System.in);
        try {
            return in.nextInt();
        } catch (InputMismatchException missE) {
            in.next(); // discard invalid input
            return -1;
        }
    }

    /**
     * Display a dialog requesting a single integer which is returned
     * upon completion.
     *
     * The dialog is repeated in an endless loop if the given input
     * is not an integer or not within min and max bounds.
     *
     * @param msg: a name for the requested data.
     * @param min: minimum accepted integer.
     * @param max: maximum accepted integer.
     * @return The user's input as integer.
     */
    public static int requestInt(String msg, int min, int max) {
        Objects.requireNonNull(msg);

        while(true) {
            System.out.print("Please provide " + msg + ": ");
            int input = parseInput();
            if (input >= min && input <= max) return input;
            else {
                System.out.println("Invalid input. Must be between " + min + " and " + max);
            }
        }
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

    ////////////////////////MAIN///////////////////////////////////
    
    public static void main(String[] args) {
     
    	
    	if (args[0] == null) 
    		throw new IllegalArgumentException("File path not given");
  
    	chooseGame();
    	int opt = parseInput();
    	switch(opt) {
    	  case 1:
    		game = new RGameGrid(args[0]);
    		break;
    	  case 2:
    		game = new XGameGrid(args[0]);
    		break;
    	}
    	
    	GameGrid gameCopy = copyGameGrid(game);
       
        System.out.println(game);
        
        
      while (true) {
    	System.out.println("\nThe rank of this sudoku is " + Ranker.rankSudoku(game));
        printMenu();
        int num = parseInput();
        int x,y,r;
        switch(num) {
          case 1 :
      		x = requestInt("x-coordinate", 1, 9);
            y = requestInt("y-coordinate", 1, 9);
            r = requestInt("number", 1, 9);
        	if (game.setField(x-1, y-1, r)) {
        		System.out.println(game);
        	} else System.out.println("Conflicting number. Can't set value");
            
            break;
          case 2 :
    	    x = requestInt("x-coordinate", 1, 9);
            y = requestInt("y-coordinate", 1, 9);
            game.clearField(x-1, y-1);
            System.out.println(game);
            break;
          case 3 :
            System.out.println(game);
            break;
          case 4 :
        	GameGrid my = copyGameGrid(gameCopy);
        	Solver.solutions.clear();
        	Solver.findAllSolutions(my);
        	for (GameGrid sol : Solver.solutions) {
        		System.out.println(sol + "\n\n");
        	}
        	
        	return;
          case 5 :
        	return;
          default :
            System.out.println("Invalid input!");
            break;
        }
      }
  
    }
}

import java.util.HashMap;

public class Ranker {
	public static float rankSudoku(GameGrid my) {
		Solver.solutions.clear();
		float rank;
		int num = Solver.findAllSolutions(my).size();
		float free = GameGrid.getFreeVariables(my)/100.0F;
		rank = num + free;
		return rank;
	}
	

	
	public static void main(String[] args) {
		HashMap<String, GameGrid> sudokus = IOUtils.loadfromFolder(args[0] + " " + args[1]);
		
		for (String sudoku : sudokus.keySet()) {
			Solver.solutions.clear();
			System.out.println(sudoku + ": rank is " + rankSudoku(sudokus.get(sudoku)));
		}
		
	}
}

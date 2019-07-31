import javax.swing.*;
import java.io.File;
public class Sudoku1 {

	public static String askForPath() {
		String s = JOptionPane.showInputDialog("Please provide path to the Sudoku "
				+ "game file");
		File game = new File(s);
		if (s.isEmpty() || !game.exists()) return null;
		else return s;
	}


public static void main(String[] args) {
		String s = "/Users/alexmamina/eclipse-workspace/Sudoku/" + 
				askForPath();
		SudokuFrame window = new SudokuFrame(s);
		window.setVisible(true);
	

	
}
}

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SelectedAction implements ActionListener {

	private GameGrid game;
	
	
	
	public SelectedAction(GameGrid game) {
		
		this.game = game;
		
	}

	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		int num = Character.getNumericValue(b.getText().charAt(0));
		for (int i = 0; i < GameGrid.GRID_DIM; i++) {
			for (int j = 0; j < GameGrid.GRID_DIM; j++) {
				
				
				if (!SudokuFrame.grid[i][j].getText().equals("")) {
				if (Character.getNumericValue(SudokuFrame.grid[i][j].getText().charAt(0)) == num) {
					SudokuFrame.grid[i][j].setBackground(Color.yellow);
				} else {
					SudokuFrame.grid[i][j].setBackground(Color.white);
				}
				}
			}
		}
	}

}

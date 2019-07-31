import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SudokuNotesAction implements ActionListener{

	private JButton[][] grid;
	private GameGrid game;

	public SudokuNotesAction(JButton[][] grid, GameGrid game) {
		this.grid = grid;
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JToggleButton b = (JToggleButton) e.getSource();
		if (b.isSelected()) {
			b.setText("Notes are on");
		} else {
			b.setText("Notes are off");
			for (int i = 0; i < 9; i++) {
				SudokuFrame.nums[i].setSelected(false);
				for (int j = 0; j < GameGrid.GRID_DIM; j++) {
					SudokuFrame.grid[i][j].setSelected(false);
					SudokuFrame.grid[i][j].setBackground(Color.white);
				}
			}
		}

	}

	
}

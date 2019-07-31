import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JToggleButton;

public class InsertNumberAction implements ActionListener {

	public static int errors = 0;
	private GameGrid game;
	
	public InsertNumberAction(GameGrid game) {
		this.game = game;
	}
	@Override
	
	public void actionPerformed(ActionEvent e) {
		 int gameCounter = createCounter();
		 
		
		JToggleButton b = (JToggleButton) e.getSource();
		if (!SudokuFrame.notes.isSelected()) {
		if (b.isSelected()) {
			for (int i = 0; i < 9; i++) {
				if (!SudokuFrame.nums[i].getText().equals(b.getText())) {
				SudokuFrame.nums[i].setSelected(false);
				}
				SudokuFrame.clear.setSelected(false);
			}
		} 
		
		}

		
		JToggleButton var = (JToggleButton) e.getSource();
		
		
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
		
		
		for (int i = 0; i < GameGrid.GRID_DIM; i++) {
			for (int j = 0; j < GameGrid.GRID_DIM; j++) {
				if (!SudokuFrame.notes.isSelected()) {
					if (SudokuFrame.grid[i][j].isSelected()) {
						
						if (game.isValid(i, j, Integer.parseInt(var.getText()))) {
							game.setField(i, j, Integer.parseInt(var.getText()));
							SudokuFrame.grid[i][j].setText(var.getText());
							SudokuFrame.grid[i][j].setForeground(Color.black);
							SudokuFrame.grid[i][j].setBackground(Color.white);
							gameCounter--;
							checkQuantity(Integer.parseInt(var.getText()));
							var.setSelected(false);
							SudokuFrame.grid[i][j].setSelected(false);
							if (gameCounter == 0) {
								JOptionPane.showMessageDialog(null, "Congratulations! You've finished the game!\n"
										+ "Errors: " + errors);
							}
							break;
						} else {
							JOptionPane.showMessageDialog(null, "Invalid input: " + var.getText() + ". Conflicting number exists."); 
							SudokuFrame.grid[i][j].setText("");
							SudokuFrame.grid[i][j].setBackground(Color.white);
							game.clearField(i, j);
							checkQuantity(Integer.parseInt(var.getText()));
							SudokuFrame.grid[i][j].setSelected(false);
							var.setSelected(false);
							errors++;
							break;
						}
		
					}
				} else {
					String result = SudokuFrame.grid[i][j].getText();
					//if notes are selected
					
					if (SudokuFrame.grid[i][j].isSelected()) {
						
						result += var.getText();
						
						SudokuFrame.grid[i][j].setText(result);
						SudokuFrame.grid[i][j].setBackground(Color.white);
						SudokuFrame.grid[i][j].setForeground(Color.gray);
						var.setSelected(false);
						
						break;
					}
				}
			}
		}
	}

	public void checkQuantity(int n) {
		int counter = 0;
		for (int i = 0; i < GameGrid.GRID_DIM; i++) {
			for (int j = 0; j < GameGrid.GRID_DIM; j++) {
				if (game.getField(i, j) == n) {
					counter++;
				}
			}
		}
		if (counter == 9) {
			SudokuFrame.nums[n-1].setVisible(false);
		}
	}
	
	
	protected int createCounter() {
		int free = 0;
    	for (int i = 0; i < GameGrid.GRID_DIM; i++) {
    		for (int j = 0; j < GameGrid.GRID_DIM; j++) {
    			if (game.getField(i, j) == 0) free++;
    		}
    	}
    	return free;
	}
	
	
}

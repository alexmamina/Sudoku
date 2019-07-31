import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class SudokuFieldAction implements ActionListener{

	private final int x, y;
	private  GameGrid game;
	public JButton var;
	public static int errors = 0;
	
	
	public SudokuFieldAction(int x, int y, GameGrid game) {
		this.x = x;
		this.y = y;
		this.game = game;
	}
	

	
	
	public void actionPerformed(ActionEvent e) {
		
		 if (e.getSource() instanceof JButton) {
			 var = (JButton) e.getSource();
			 var.setSelected(true);
		     var.setBackground(Color.LIGHT_GRAY);
		     for (int i = 0; i < GameGrid.GRID_DIM; i++) {
					for (int j = 0; j < GameGrid.GRID_DIM; j++) {
						if (!SudokuFrame.grid[i][j].equals(var)) {
						SudokuFrame.grid[i][j].setBackground(Color.white);
						SudokuFrame.grid[i][j].setSelected(false);
						}
					}
				}
		     if (SudokuFrame.clear.isSelected()) {
					var.setText("");
					var.setBackground(Color.white);
					game.clearField(x, y);
					checkQuantity2(Integer.parseInt(var.getText()));
					SudokuFrame.clear.setSelected(false);
				}
		 }
		 
//		 String n = JOptionPane.showInputDialog("Enter a number:");
//		 var.setText(n);
//		 game.setField(x, y, Integer.parseInt(n));
//		 var.setBackground(Color.white);
//		 var.setForeground(Color.black);
//		 var.setSelected(false);
	}

	
	

	

	
		 public void checkQuantity2(int n) {
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
		 
}
			
			/*
			if (SudokuFrame.notes.isSelected()) {
//					//	HERE MAKE IT DISAPPEAR IF NOT VALID
				String opt = "";
				for (int i = 0; i < 9; i++) {
					if (SudokuFrame.nums[i].isSelected()) {
						opt+= SudokuFrame.nums[i].getText();
				}
					var.setText(opt);
					var.setForeground(Color.LIGHT_GRAY);
					var.setBackground(Color.white);
					
			}
			
			
			
			} else {
				var.setBackground(Color.LIGHT_GRAY);
				for (int i = 0; i < 9; i++) {
					if (SudokuFrame.nums[i].isSelected()) { 
							if (game.isValid(x, y, Integer.parseInt(SudokuFrame.nums[i].getText()))) {
							var.setText(SudokuFrame.nums[i].getText());
							game.setField(x, y, Integer.parseInt(SudokuFrame.nums[i].getText()));
							gameCounter--;
							checkQuantity(Integer.parseInt(SudokuFrame.nums[i].getText()));
							var.setForeground(Color.black);
							var.setBackground(Color.white);
							SudokuFrame.nums[i].setSelected(false);
							
							if (gameCounter == 0) {
								JOptionPane.showMessageDialog(null, "Congratulations! You've finished the game!\n"
										+ "Errors: " + SudokuFieldAction.errors);
							}
							break;
							
							
							
							
						}
						
					} 
			
				} 
				
			
			}
			
		} */
		


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class SudokuMenuAction implements ActionListener {
	
	
	private  GameGrid game;
	private String option;

	
	
	public SudokuMenuAction(String a, GameGrid game) {
		option = a;
		this.game = game;
	}

	JMenuItem var = new JMenuItem();
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			
		switch(option) {
		
			
		case "load" : 
			var = (JMenuItem) e.getSource();
			String path = "/Users/alexmamina/eclipse-workspace/Sudoku/" + 
					Sudoku1.askForPath();
			game = new RGameGrid(path);
			
			for (int i = 0; i < GameGrid.GRID_DIM; i++) {
				for (int j = 0; j < GameGrid.GRID_DIM; j++) {
					if (!game.isInitial(i, j)) {
						SudokuFrame.grid[i][j].setText("");
						SudokuFrame.grid[i][j].setForeground(Color.BLACK);
					} else {
					SudokuFrame.grid[i][j].setText(Integer.toString(game.getField(i, j))+"'");
					SudokuFrame.grid[i][j].setForeground(Color.BLUE);
					}
				}
			}
			
			
			break;
			
		case "rank" :
			var = (JMenuItem) e.getSource();
			String ran = Float.toString(Ranker.rankSudoku(game));
			JFrame rank = new JFrame();
			String r = "The rank of this sudoku is "+ran;
			JLabel l = new JLabel(r);
			rank.setLocation(135,210);
			rank.setVisible(true);
			rank.add(l);
			rank.setSize(220, 60);	
			break;
			
		case "solve" :
			
			var = (JMenuItem) e.getSource();

			Solver.solutions.clear();
        	Solver.findAllSolutions(game);
        	
        	
    		for (GameGrid solut : Solver.solutions) {
    			JFrame sol = new JFrame();
    			JLabel solution = new JLabel();
    			String rows = "";

    			for (int i = 0; i < GameGrid.GRID_DIM; i++) {

    				for (int j = 0; j < GameGrid.GRID_DIM; j++) {
					rows += "| "+solut.getField(j, i) + " |  ";
    					
    				}}
    			
    			
    			solution.setText("<html>"+rows+"<br /></html>");
				sol.add(solution);
				sol.setSize(270, 200);
				sol.setVisible(true);
				sol.setLocation((int) (Math.random()*1000), (int) (Math.random()*1000));

        	}
    		break;
    		
		case "clear" :
			for (int i = 0; i < GameGrid.GRID_DIM; i++) {
				for (int j = 0; j < GameGrid.GRID_DIM; j++) {
					if (!game.isInitial(i, j))  {
					game.clearField(i,j);
					SudokuFrame.grid[i][j].setText("");
					SudokuFrame.grid[i][j].setBackground(Color.white);
					}
				}
				SudokuFrame.nums[i].setVisible(true);
			}
			break;
    		
    		
			
			}
		}
		
	
	}
	
	
}



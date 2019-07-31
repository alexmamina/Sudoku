import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SudokuFrame extends JFrame{

	private GameGrid game;
	public static JButton[][] grid = new JButton[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
	public static JToggleButton notes, clear;
	public static JButton unselect;
	public static JToggleButton[] nums = new JToggleButton[9];
	
	public SudokuFrame(String path) {
		setSize(500,500);
		setTitle(path);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		game = new RGameGrid(path);
		GridLayout layout = new GridLayout(GameGrid.GRID_DIM+2, GameGrid.GRID_DIM);
		//layout.addLayoutComponent("test", q);
		setLayout(layout);
		createButtonGrid();
		insertMenu();
		insertNumbers();
		
	}

	
		private void createButtonGrid() {
		
		for (int i = 0; i < GameGrid.GRID_DIM; i++) {
			for (int j = 0; j < GameGrid.GRID_DIM; j++) {
					
					SudokuFieldAction a = new SudokuFieldAction (i, j, game);
					InsertNumberAction insert = new InsertNumberAction (game);
					
					SelectedAction c = new SelectedAction (game);
					if (game.getField(i, j) == 0) {
						JButton b = new JButton();
						if (j % GameGrid.SUBGRID_DIM == 2 && i % GameGrid.SUBGRID_DIM == 2) {
							b.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
					} 
						else if (i % GameGrid.SUBGRID_DIM == 2) {
						b.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
					} else if (j % GameGrid.SUBGRID_DIM == 2){
						b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
					}
						else {b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
					}
						
						b.setForeground(Color.black);
						b.setOpaque(true);
						b.setBackground(Color.WHITE);
						
						add(b);	
						setEnabled(true);
						b.addActionListener(a);	
						grid[i][j] = b;
						
					}
					else {
						JButton b = new JButton(Integer.toString(game.getField(i, j))+"'");
						if (j % GameGrid.SUBGRID_DIM == 2 && i % GameGrid.SUBGRID_DIM == 2) {
							b.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 3, Color.black));
					} 
						else if (i % GameGrid.SUBGRID_DIM == 2) {
						b.setBorder(BorderFactory.createMatteBorder(1, 1, 3, 1, Color.black));
					} else if (j % GameGrid.SUBGRID_DIM == 2){
						b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 3, Color.black));
					}
						else {b.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.black));
					}
						b.setForeground(Color.blue);
						b.setOpaque(true);
						b.setBackground(Color.WHITE);
					
						add(b);
						b.addActionListener(c);
						grid[i][j] = b;
					}
				
			}
		}
	}
	
	private void insertMenu() {
		SudokuMenuAction a = new SudokuMenuAction("load", game);
		SudokuMenuAction b = new SudokuMenuAction("solve", game);
		SudokuMenuAction c = new SudokuMenuAction("rank", game);
		SudokuMenuAction e = new SudokuMenuAction("clear", game);
		
		SudokuNotesAction f = new SudokuNotesAction(grid, game);
		
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("Menu");
		
		bar.add(menu);
		
		JMenuItem load = new JMenuItem("Load Game");
		load.addActionListener(a);
		JMenuItem solve = new JMenuItem("Find all solutions");
		solve.addActionListener(b);
		JMenuItem rank = new JMenuItem("Rank Game");
		rank.addActionListener(c);
		JMenuItem clear = new JMenuItem("Clear the board");
		clear.addActionListener(e);
		
		menu.add(load);
		menu.addSeparator();
		menu.add(rank);
		menu.addSeparator();
		menu.add(solve);
		menu.addSeparator();
		menu.add(clear);
		setJMenuBar(bar);
		notes = new JToggleButton("Notes", false);
		notes.addActionListener(f);
		bar.add(notes);
		
		
	}
	
	
	private void insertNumbers() {
	
		InsertNumberAction insert = new InsertNumberAction(game);
		JToggleButton one = new JToggleButton("1");
		add(one);
		nums[0] = one;
		JToggleButton two = new JToggleButton("2");
		add(two);
		nums[1] = two;
		JToggleButton three = new JToggleButton("3");
		add(three);
		nums[2] = three;
		JToggleButton four = new JToggleButton("4");
		add(four);
		nums[3] = four;
		JToggleButton five = new JToggleButton("5");
		add(five);
		nums[4] = five;
		JToggleButton six = new JToggleButton("6");
		add(six);
		nums[5] = six;
		JToggleButton sev = new JToggleButton("7");
		add(sev);
		nums[6] = sev;
		JToggleButton eight = new JToggleButton("8");
		add(eight);
		nums[7] = eight;
		JToggleButton nine = new JToggleButton("9");
		add(nine);
		nums[8] = nine;
		for (int i = 0; i < 9; i++) {
			
			nums[i].addActionListener(insert);
		}
		clear = new JToggleButton("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < GameGrid.GRID_DIM; i++) {
					for (int j = 0; j < GameGrid.GRID_DIM; j++) {
						if (grid[i][j].isSelected()) {
							grid[i][j].setText("");
							grid[i][j].setBackground(Color.white);
							game.clearField(i, j);
							clear.setSelected(false);
							grid[i][j].setSelected(false);
						}
					}
					nums[i].setSelected(false);
				}
			}
			
		});
		unselect = new JButton("Untick");
		unselect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				for (int i = 0; i < GameGrid.GRID_DIM; i++) {
					for (int j = 0; j < GameGrid.GRID_DIM; j++) {
						grid[i][j].setBackground(Color.white);
						grid[i][j].setSelected(false);
					}
					nums[i].setSelected(false);
				}
				unselect.setSelected(false);
			}
		
		});
		add(clear);
		add(unselect);
		
	}

	
}

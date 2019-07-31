import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;

import java.io.UncheckedIOException;
import java.io.IOException;
import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class IOUtils {

    /**
     * This function loads a Sudoku game grid from a file.
     *
     * @param gridFileName the path to a Sudoku grid data file
     * @return a two-dimensional integer array holding the data from the specified file
     *
     */
    public static int[][] loadFromFile(String gridFileName) {
        Objects.requireNonNull(gridFileName);

        Path fileName = Paths.get(gridFileName);

        if (!Files.exists(fileName))
            throw new IllegalArgumentException("Given file does not exist: " + fileName);

        int[][] grid = new int[GameGrid.GRID_DIM][GameGrid.GRID_DIM];
        
        try {     
        	Scanner in = new Scanner(fileName);         
	
	        for(int row = 0; row < GameGrid.GRID_DIM; row++) {
	            for(int column = 0; column < GameGrid.GRID_DIM; column++) {
	                if(!in.hasNextInt())
	                    throw new RuntimeException("Given Sudoku file has invalid format: " + fileName);
	
	                int value = in.nextInt();
	                if (value < 0 || value > 9)
	                    throw new RuntimeException("Given Sudoku file has invalid "
	                               + "entry at: " + column + "x" + row);
	               
	                grid[row][column] = value;
	            }
	        }
	        
	        in.close();
        
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return grid;
    }

    
    
    
    public static HashMap<String, GameGrid> loadfromFolder(String path) {
    	HashMap<String, GameGrid> sudokus = new HashMap<>();
    	String[] files;
    	File directory = new File(path);
    	if (!directory.isDirectory()) return sudokus;
    	files = directory.list();
    	for (String f : files) {
    		if (f.endsWith(".sd")) {
    			GameGrid sudoku = new RGameGrid(path+"/"+f);
    			sudokus.put(f, sudoku);
    		}
    	}
    	return sudokus;
    }
    
    
    
    
}


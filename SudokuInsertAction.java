
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SudokuInsertAction implements ActionListener {

	private static GameGrid  game;
	public SudokuInsertAction(GameGrid game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
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
	}

	

}

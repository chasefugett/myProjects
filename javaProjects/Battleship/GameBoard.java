import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * @author Chase Fugett
 * @purpose constructs a gameboard with 100 buttons to represent
 * a 10x10 board
 *
 */
public class GameBoard {
	
	private BattleButton[][] array;

	private ArrayList<Ship> shipList = new ArrayList<Ship>();

	private Image hit;
	private Image miss;
	
	public GameBoard() {
		super();
	}

	/**
	 * ActionListener class for the gameboard that checks if where the player clicked is
	 * a hit or miss
	 */
	class GameBoardListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

		}
	}
	
	/**
	 * Returns the ArrayList of ships
	 * @return the shipList
	 */
	public ArrayList<Ship> getShipList() {
		return shipList;
	}

	/**
	 * Sets the ArrayList of ships
	 * @param shipList the shipList to set
	 */
	public void setShipList(ArrayList<Ship> shipList) {
		this.shipList = shipList;
	}

	/**
	 * Returns the array for the gameboard
	 * 
	 * @return the array
	 */
	public BattleButton[][] getArray() {
		return array;
	}

	/**
	 * Sets the array for the gameboard
	 * 
	 * @param array
	 *            the array to set
	 */
	public void setArray(BattleButton[][] array) {
		this.array = array;
	}
	
	public JPanel createGameBoard() {
		JPanel panel = new JPanel();

		// Creates a panel with a grid layout
		panel.setLayout(new GridLayout(10, 10));

		// Creates an action listener
		ActionListener l = new GameBoardListener();

		// Initializes the array with 100 values and initializes all the buttons
		array = new BattleButton[10][10];

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				BattleButton bb = new BattleButton(i, j);
				bb.addActionListener(l);
				bb.setPreferredSize(new Dimension(50, 50));
				bb.setBorderPainted(false);
				bb.setOpaque(true);

				// Creates the alternating black and white pattern
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						bb.setBackground(Color.BLACK);
					} else {
						bb.setBackground(Color.WHITE);
					}
				} else {
					if (j % 2 == 0) {
						bb.setBackground(Color.WHITE);
					} else {
						bb.setBackground(Color.BLACK);
					}
				}

				// Adds each BattleButton to a 2D array
				array[i - 1][j - 1] = bb;

				// Adds each BattleButton to the panel
				panel.add(bb);
			}
		}

		return panel;
	}
	
}

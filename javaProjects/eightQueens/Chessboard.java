package edu.miamioh.fugettcj.Project2;

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
 * Constructs a chessboard object with 64 buttons to represent the board
 */
public class Chessboard {

	private ChessButton[][] array;

	private ArrayList<Queen> queenList = new ArrayList<Queen>();

	private Image image;

	/**
	 * 
	 */
	public Chessboard() {
		super();
	}

	/**
	 * ActionListener class for the chessboard that adds a queen icon if there
	 * is not one there, and takes it away if there is one already there
	 */
	class ChessboardListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

				// Creates a ChessButton from the button clicked
				ChessButton cb = (ChessButton) e.getSource();

				// Creates a new queen from the ChessButton
				Queen q = new Queen(cb.getRow(), cb.getColumn());

				try {
					image = ImageIO.read(getClass()
							.getResource("QueenIcon.png"));
				} catch (IOException ex) {
				}

				// If there is no queen there, adds one
				if (((AbstractButton) e.getSource()).getIcon() == null) {
					// Cannot add a queen if there are already 8 on the board
					if (queenList.size() < 8) {
					((AbstractButton) e.getSource()).setIcon(new ImageIcon(
							image));

					// Adds the queen to the list of queens
					queenList.add(q);
					}
				} 
				// If there is a queen there, removes it
				else {
					((AbstractButton) e.getSource()).setIcon(null);

					// Removes the queen from the list of queens
					queenList.remove(q);
				}
			}
		
	}
	
	/**
	 * Returns the ArrayList of queens
	 * @return the queenList
	 */
	public ArrayList<Queen> getQueenList() {
		return queenList;
	}

	/**
	 * Sets the ArrayList of queens
	 * @param queenList the queenList to set
	 */
	public void setQueenList(ArrayList<Queen> queenList) {
		this.queenList = queenList;
	}

	/**
	 * Returns the array for the chessboard
	 * 
	 * @return the array
	 */
	public ChessButton[][] getArray() {
		return array;
	}

	/**
	 * Sets the array for the chessboard
	 * 
	 * @param array
	 *            the array to set
	 */
	public void setArray(ChessButton[][] array) {
		this.array = array;
	}

	public JPanel createChessboard() {
		JPanel panel = new JPanel();

		// Creates a panel with a grid layout
		panel.setLayout(new GridLayout(8, 8));

		// Creates an action listener
		ActionListener l = new ChessboardListener();

		// Initializes the array with 64 values and initializes all the buttons
		array = new ChessButton[8][8];

		for (int i = 1; i <= 8; i++) {
			for (int j = 1; j <= 8; j++) {
				ChessButton cb = new ChessButton(i, j);
				cb.addActionListener(l);
				cb.setPreferredSize(new Dimension(50, 50));
				cb.setBorderPainted(false);
				cb.setOpaque(true);

				// Creates the alternating black and white pattern
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						cb.setBackground(Color.BLACK);
					} else {
						cb.setBackground(Color.WHITE);
					}
				} else {
					if (j % 2 == 0) {
						cb.setBackground(Color.WHITE);
					} else {
						cb.setBackground(Color.BLACK);
					}
				}

				// Adds each ChessButton to a 2D array
				array[i - 1][j - 1] = cb;

				// Adds each ChessButton to the panel
				panel.add(cb);
			}
		}

		return panel;
	}

}

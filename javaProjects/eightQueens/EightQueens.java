package edu.miamioh.fugettcj.Project2;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Chase Fugett
 * Creates an 8x8 chessboard for the user to solve the 8-Queens
 * problem, with buttons provided to check validity of solutions and
 * give tips
 */
public class EightQueens extends JFrame {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 500;

	private JLabel message;
	private JButton isValid;
	private JButton tip;

	private ChessButton button1;
	private ChessButton button2;
	private Image image;

	private Chessboard chessboard;

	/**
	 * Constructs the EightQueens GUI
	 */
	public EightQueens() {
		createControlPanel();

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(true);
	}

	/**
	 * Creates the control panel for the frame by calling methods to create the
	 * four subpanels
	 */
	public void createControlPanel() {
		createBoardPanel();
		createTextPanel();
		createTipPanel();
		createValidMovePanel();
	}

	/**
	 * Creates the 8x8 chessboard panel in the center position composed of a
	 * chessboard
	 */
	private void createBoardPanel() {
		JPanel panel = new JPanel();
		chessboard = new Chessboard();
		panel.add(chessboard.createChessboard());
		add(panel, BorderLayout.CENTER);
	}

	/**
	 * Creates the text panel that displays instructions and warning messages in
	 * the north position
	 */
	private void createTextPanel() {
		JPanel panel = new JPanel();
		message = new JLabel("Can you solve the 8-Queens problem? Good luck!");
		panel.add(message);
		add(panel, BorderLayout.NORTH);
	}

	/**
	 * Creates the tip panel that displays the tip button in the east position
	 */
	private void createTipPanel() {
		JPanel panel = new JPanel();
		tip = new JButton("Tip");
		ActionListener listener = new TipListener();
		tip.addActionListener(listener);
		panel.add(tip);
		add(panel, BorderLayout.EAST);
	}

	/**
	 * Creates the valid move panel that displays the valid move button in the
	 * west position
	 */
	private void createValidMovePanel() {
		JPanel panel = new JPanel();
		isValid = new JButton("Check if placement is valid");
		ActionListener listener = new ValidListener();
		isValid.addActionListener(listener);
		panel.add(isValid);
		add(panel, BorderLayout.WEST);
	}

	/**
	 * ActionListener class for the valid move button
	 */
	class ValidListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// If there are no queens on the board, return error message
			if (chessboard.getQueenList().size() == 0) {
				message.setText("Please place a queen on the board.");
			}
			// Otherwise checks if it is a valid solution
			// NOTE: code is modified from the textbook
			else {
				ArrayList<Queen> queens = new ArrayList<Queen>();
				queens = chessboard.getQueenList();
				ChessButton[][] boardButtons = new ChessButton[8][8];
				boardButtons = chessboard.getArray();
				// Checks to see if any queens attack each other. Gives
				// conflict message stating location if they do
				for (int i = 0; i < queens.size(); i++) {
					for (int j = i + 1; j < queens.size(); j++) {
						if (queens.get(i).attacks(queens.get(j))) {
							message.setText("Queens ("
									+ queens.get(i).toString() + " and "
									+ queens.get(j).toString()
									+ ") in conflict! Try again.");
							try {
								image = ImageIO.read(getClass().getResource(
										"QueenIncorrectIcon.png"));
							} catch (IOException ex) {
							}

							// Sets buttons from the queens that are in conflict
							button1 = boardButtons[queens.get(i).getRow() - 1][queens
									.get(i).getColumn() - 1];
							button2 = boardButtons[queens.get(j).getRow() - 1][queens
									.get(j).getColumn() - 1];

							// Sets icons for the given queens in conflict to
							// highlight
							// the conflict
							button1.setIcon(new ImageIcon(image));
							button2.setIcon(new ImageIcon(image));

							return;
						}
					}
				}
				// If there are as many queens in the acceptable solutions array
				// as
				// there should be queens on the board, then player wins
				if (queens.size() == 8) {
					message.setText("It works! Congrats, you win!");
				}
				// Otherwise the solution is acceptable and may continue
				// Also, resets the queens to appear as normal
				else {
					for (int i = 0; i < queens.size(); i++) {
						for (int j = i + 1; j < queens.size(); j++) {
							try {
								image = ImageIO.read(getClass().getResource(
										"QueenIcon.png"));
							} catch (IOException ex) {
							}
							button1 = boardButtons[queens.get(i).getRow() - 1][queens
									.get(i).getColumn() - 1];
							button1.setIcon(new ImageIcon(image));
						}
					}
					message.setText("Valid solution. You may continue.");
				}
			}

		}
	}

	/**
	 * ActionListener class for the tip button
	 */
	class TipListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// Only works when there are less than 8 queens on the board
			if (chessboard.getQueenList().size() < 8) {

				ArrayList<Queen> queens = new ArrayList<Queen>();
				queens = chessboard.getQueenList();

				ArrayList<Queen> solutionSet = new ArrayList<Queen>();

				// Runs through all rows and columns on the boards
				for (int i = 1; i < 9; i++) {
					for (int j = 1; j < 9; j++) {
						// Creates a new queen at that specific row and column
						Queen q = new Queen(i, j);
						// Goes through the ArrayList of current queens
						for (int k = 0; k < queens.size(); k++) {
							// If the queen does not attack the queen
							if ((q.attacks(queens.get(k)) == false)) {
								// If the queen is not already in the solution
								// set, it is added
								// NOTE: this should prevent multiple entries
								// of the same suggestion
								if (solutionSet.contains(q) == false) {
									solutionSet.add(q);
								}
							}
						}
					}
				}
				// Gives the first solution in the solution set
				message.setText("Try this: " + solutionSet.get(0).toString());
			}
		}
	}
}

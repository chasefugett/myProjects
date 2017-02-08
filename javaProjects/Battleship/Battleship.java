import java.awt.BorderLayout;
import java.awt.HeadlessException;
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
import javax.swing.JTextField;

import EightQueens.TipListener;
import EightQueens.ValidListener;

/**
 * @author Chase Fugett
 * @purpose creates a 10x10 battleship board with the player board
 * and the AI board shown, as well as buttons for submitting move and
 * starting a new game
 */
public class Battleship extends JFrame {

	private static final int FRAME_WIDTH = 800;
	private static final int FRAME_HEIGHT = 800;
	
	private JLabel message;
	private JLabel specificMessage;
	private JButton submit;
	private JButton newGame;
	private JTextField startCoordinates;
	private JTextField endCoordinates;
	private JTextField shootCoordinates;
	
	private BattleButton button1;
	private BattleButton button2;
	private BattleButton button3;
	
	private Image image1;
	private Image image2;
	private Image image3;
	
	private GameBoard playerBoard;
	private GameBoard aiBoard;

	/**
	 * Constructs the Battleship GUI
	 */
	public Battleship() {
		createControlPanel();
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(true);
	}
	
	/**
	 * Creates the control panel for the frame by calling methods to 
	 * create the subpanels
	 */
	private void createControlPanel() {
		createPlayerBoardPanel();
		createAIBoardPanel();
		createTextPanel();
		createNewGamePanel();
		createSubmitPanel();
	}
	
	/**
	 * Creates the 10x10 player gameboard panel in the center position composed of a
	 * gameboard
	 */
	private void createPlayerBoardPanel() {
		JPanel panel = new JPanel();
		playerBoard = new GameBoard();
		panel.add(playerBoard.creatGameBoard());
		add(panel, BorderLayout.CENTER);
	}
	
	/**
	 * Creates the 10x10 AI gameboard panel in the west position composed of a
	 * gameboard
	 */
	private void createAIBoardPanel() {
		JPanel panel = new JPanel();
		aiBoard = new GameBoard();
		panel.add(aiBoard.creatGameBoard());
		add(panel, BorderLayout.WEST);
	}
	
	/**
	 * Creates the text panel that displays instructions and other messages in
	 * the north position
	 */
	private void createTextPanel() {
		JPanel panel = new JPanel();
		message = new JLabel("New Game! Place your ships!");
		panel.add(message);
		add(panel, BorderLayout.NORTH);
	}
	
	/**
	 * Creates the submit move panel that displays the submit move button, 
	 * a specific message, and text fields to input coordinates in the south position
	 */
	private void createSubmitPanel() {
		JPanel panel = new JPanel();
		submit = new JButton("Submit Move");
		specificMessage = new JLabel("Give the start and end coordinates for your "
				+ "battleship in the form \'x,y\':");
		startCoordinates = new JTextField();
		endCoordinates = new JTextField();
		shootCoordinates = new JTextField();
		ActionListener listener = new SubmitListener();
		submit.addActionListener(listener);
		panel.add(submit, BorderLayout.WEST);
		panel.add(specificMessage, BorderLayout.EAST);
		panel.add(startCoordinates, BorderLayout.CENTER);
		panel.add(endCoordinates, BorderLayout.CENTER);
		add(panel, BorderLayout.SOUTH);
	}
	
	/**
	 * Creates the new game move panel that displays the new game button in the
	 * east position
	 */
	private void createNewGamePanel() {
		JPanel panel = new JPanel();
		newGame = new JButton("Start a new game");
		ActionListener listener = new newGameListener();
		newGame.addActionListener(listener);
		panel.add(newGame);
		add(panel, BorderLayout.EAST);
	}
	
	
}

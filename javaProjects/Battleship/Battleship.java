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
	private JButton submitMove;
	private JButton newGame;
	
	private BattleButton button1;
	private BattleButton button2;
	private BattleButton button3;
	
	private Image image1;
	private Image image2;
	private Image image3;
	
	private gameBoard gameBoard;

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
		
	}
	
	
}

import javax.swing.JFrame;

/**
 * @author Chase Fugett
 * @purpose runs the Battleship GUI
 */
public class BattleshipRunner {

	public static void main(String[] args) {
		JFrame frame = new Battleship();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

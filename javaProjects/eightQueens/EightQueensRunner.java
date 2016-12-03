package edu.miamioh.fugettcj.Project2;

import javax.swing.JFrame;

/**
 * @author Chase Fugett
 * Runs the EightQueens GUI
 */
public class EightQueensRunner {

	public static void main(String[] args) {
		
		JFrame frame = new EightQueens();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}

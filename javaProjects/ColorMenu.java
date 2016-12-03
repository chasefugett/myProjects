package edu.miamioh.fugettcj.Lab10;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 * @author Chase Fugett
 * To create a frame that changes background color
 */
public class ColorMenu extends JFrame {
	private JPanel panel;
	private JMenuBar menuBar;

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 400;

	/**
	 * Creates the frame for changing the background color
	 */
	public ColorMenu() {
		// Creates a panel and sets the original background color
		panel = new JPanel();
		panel.setBackground(Color.RED);
		this.add(panel);
		
		// Creates a menu bar and adds menu items to a menu
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		menuBar.add(createColorMenu());
		
		// Adds a mouse listener to the panel
		MouseListener listener = new MyMouseListener(); 
		panel.addMouseListener(listener);

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
	}

	private JMenu createColorMenu() {
		JMenu colorMenu = new JMenu("Color Menu");
		colorMenu.add(createRedMenuItem());
		colorMenu.add(createGreenMenuItem());
		colorMenu.add(createBlueMenuItem());
		return colorMenu;
	}

	/**
	 * @return JMenuItem
	 * Creates Red menu item with action listener
	 */
	public JMenuItem createRedMenuItem() {

		class RedMenuListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				panel.setBackground(Color.RED);
			}
		}

		JMenuItem red = new JMenuItem("Red");
		ActionListener listener = new RedMenuListener();
		red.addActionListener(listener);
		return red;
	}

	/**
	 * @return JMenuItem
	 * Creates Green menu item with action listener
	 */
	public JMenuItem createGreenMenuItem() {

		class GreenMenuListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				panel.setBackground(Color.GREEN);
			}
		}

		JMenuItem green = new JMenuItem("Green");
		ActionListener listener = new GreenMenuListener();
		green.addActionListener(listener);
		return green;
	}

	/**
	 * @return JMenuItem
	 * Creates Blue menu item with action listener
	 */
	public JMenuItem createBlueMenuItem() {

		class BlueMenuListener implements ActionListener {

			public void actionPerformed(ActionEvent event) {
				panel.setBackground(Color.BLUE);
			}
		}

		JMenuItem blue = new JMenuItem("Blue");
		ActionListener listener = new BlueMenuListener();
		blue.addActionListener(listener);
		return blue;
	}

	public class MyMouseListener implements MouseListener {

		// Changes background color depending on previous color
		public void mousePressed(MouseEvent event) {
			if (panel.getBackground().equals(Color.RED)) {
				panel.setBackground(Color.GREEN);
			}
			else if (panel.getBackground().equals(Color.GREEN)) {
				panel.setBackground(Color.BLUE);
			}
			else if (panel.getBackground().equals(Color.BLUE)) {
				panel.setBackground(Color.RED);
			}
		}

		// Do-nothing methods
		public void mouseReleased(MouseEvent event) {
		}

		public void mouseClicked(MouseEvent event) {
		}

		public void mouseEntered(MouseEvent event) {
		}

		public void mouseExited(MouseEvent event) {
		}
	}
	
	

	
	
}

package edu.miamioh.fugettcj.Lab10;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Chase Fugett
 * Sets up a GUI for converting currency between USD, EUR, and GBP
 */
public class ConversionGUI extends JFrame{
	
	private JLabel initialConversionLabel;
	private JTextField initialConversionField;
	private JLabel fromCurrencyLabel;
	private JLabel toCurrencyLabel;
	private JButton convertButton;
	private JLabel finalConversionLabel;
	private JLabel finalAmountLabel;
	private JComboBox currenciesCombo;
	private JComboBox currenciesComboTo;
	private JComboBox currenciesComboFrom;
	private ActionListener listener;
	private JLabel warningText;

	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEIGHT = 105;
	
	/**
	 * Creates the frame for converting currencies
	 */
	public ConversionGUI() {
		createControlPanel();
		
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setResizable(false);
	}
	
	/**
	 * Creates the control panel for the GUI
	 */
	public void createControlPanel() {
		// Creates and organizes the initialAmountPanel
		JPanel initialAmountPanel = new JPanel();
		initialAmountPanel.setLayout(new GridLayout(1,2));
		initialConversionLabel = new JLabel("Amount to be Converted: ");
		final int FIELD_WIDTH = 7;
		initialConversionField = new JTextField(FIELD_WIDTH);
		initialAmountPanel.add(initialConversionLabel);
		initialAmountPanel.add(initialConversionField);
		
		// Creates and organizes the currenicesPanel
		JPanel currenciesPanel = new JPanel();
		currenciesPanel.setLayout(new GridLayout(1,4));
		fromCurrencyLabel = new JLabel("From:");
		toCurrencyLabel = new JLabel("--> To:");
		currenciesComboFrom = createComboBox();
		currenciesComboTo = createComboBox();
		currenciesPanel.add(fromCurrencyLabel);
		currenciesPanel.add(currenciesComboFrom);
		currenciesPanel.add(toCurrencyLabel);
		currenciesPanel.add(currenciesComboTo);
		
		// Creates and organizes the convertPanel
		JPanel convertPanel = new JPanel();
		convertButton = new JButton("Convert");
		listener = new ConversionListener();
		convertButton.addActionListener(listener);
		convertPanel.add(convertButton);
		convertPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		// Creates and organizes the finalConversionPanel
		JPanel finalConversionPanel = new JPanel();
		// finalConversionPanel.setLayout(new GridLayout(1,2));
		finalConversionPanel.setAlignmentY(CENTER_ALIGNMENT);
		finalConversionLabel = new JLabel("New Amount: ");
		finalAmountLabel = new JLabel("money");
		finalConversionPanel.add(finalConversionLabel);
		finalConversionPanel.add(finalAmountLabel);
		
		// Creates the warningPanel
		JPanel warningPanel = new JPanel();
		warningText = new JLabel("Convert Your Currency!");
		warningPanel.add(warningText);
		
		// Creates the control panel with a border layout and then adds the
		// subpanels to their appropriate positions
		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new BorderLayout());
		controlPanel.add(warningPanel, BorderLayout.NORTH);
		controlPanel.add(initialAmountPanel, BorderLayout.WEST);
		controlPanel.add(currenciesPanel, BorderLayout.CENTER);
		controlPanel.add(convertPanel, BorderLayout.EAST);
		controlPanel.add(finalConversionPanel, BorderLayout.SOUTH);
		controlPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		this.add(controlPanel);
	}
	
	/**
	 * Creates the combo box of currencies to convert
	 * @return JComboBox
	 */
	public JComboBox createComboBox() {
		// Adds currencies to the combo box and gives an action listener
		currenciesCombo = new JComboBox();
		currenciesCombo.addItem("U.S. dollars");
		currenciesCombo.addItem("Euros");
		currenciesCombo.addItem("British Pounds");
		currenciesCombo.setEditable(false);
		
		// Adds the combo box to a panel and returns the panel
		return currenciesCombo;
	}
	
	class ConversionListener implements ActionListener {
		
		public void actionPerformed(ActionEvent event) {
			// Warning message if the currencies are the same
			if (currenciesComboTo.getSelectedItem().equals
					(currenciesComboFrom.getSelectedItem())) {
				warningText.setText("Warning: Cannot have both currencies be the"
						+ " same!");
			}
			else {
				warningText.setText("Currency Converted!");
				// If the first combo box is dollars
				if (currenciesComboFrom.getSelectedItem().equals("U.S. dollars")) {
					// If second combo box is euros
					if (currenciesComboTo.getSelectedItem().equals("Euros")) {
						double money = Double.parseDouble
								(initialConversionField.getText());
						double total = money * 1.42;
						finalAmountLabel.setText(String.format("%.2f", total) +
								" Euros");
					}
					// If second combo box is british pounds
					else {
						double money = Double.parseDouble
								(initialConversionField.getText());
						double total = money * 1.64;
						finalAmountLabel.setText(String.format("%.2f", total) +
								" British Pounds");
					}
				}
				// If the first combo box is euros
				else if (currenciesComboFrom.getSelectedItem().equals("Euros")) {
					// If second combo box is dollars
					if (currenciesComboTo.getSelectedItem().equals("U.S. dollars")) {
						double money = Double.parseDouble
								(initialConversionField.getText());
						double total = money / 1.42;
						finalAmountLabel.setText(String.format("%.2f", total) +
								" U.S. dollars");
					}
					// If second combo box is british pounds
					else {
							double money = Double.parseDouble
									(initialConversionField.getText());
							double total = money * 1.13;
							finalAmountLabel.setText(String.format("%.2f", total) +
									" Brititsh Pounds");
					}
				}
				// If the first combo box is british pounds
				else {
					// If second combo box is dollars
					if (currenciesComboTo.getSelectedItem().equals("British Pounds")) {
						double money = Double.parseDouble
								(initialConversionField.getText());
						double total = money / 1.64;
						finalAmountLabel.setText(String.format("%.2f", total) +
								" U.S. dollars");
					}
					// If second combo box is euros
					else {
						double money = Double.parseDouble
								(initialConversionField.getText());
						double total = money / 1.13;
						finalAmountLabel.setText(String.format("%.2f", total) +
								" Euros");
					}
				}
			}
		}
	}
	
}

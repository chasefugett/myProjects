package Gallhp;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;

import Tpdahp.*;
import Tpfahp.*;
import Tpdohp.*;
import Twfahp.*;


public class Demo extends JDialog {
	private JTextField dimensionUserInput;
	private JTextField topTempTextBox;
	private JTextField bottomTempTextBox;
	private JTextField leftTempTextBox;
	private JTextField rightTempTextBox;
	private int iterationCount;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Demo dialog = new Demo();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Demo() {
		setBounds(0, 0, 1200, 800);
		getContentPane().setLayout(null);
		
		JPanel UserInputPanel = new JPanel();
		UserInputPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		UserInputPanel.setBounds(6, 39, 210, 433);
		getContentPane().add(UserInputPanel);
		UserInputPanel.setLayout(null);
		
		JPanel PrecisionCheckboxes = new JPanel();
		PrecisionCheckboxes.setBounds(6, 6, 198, 68);
		UserInputPanel.add(PrecisionCheckboxes);
		
		final JCheckBox chckbxTpdahp = new JCheckBox("Tpdahp");
		chckbxTpdahp.setSelected(true);
		PrecisionCheckboxes.add(chckbxTpdahp);
		
		final JCheckBox chckbxTpfahp = new JCheckBox("Tpfahp");
		chckbxTpfahp.setSelected(true);
		PrecisionCheckboxes.add(chckbxTpfahp);
		
		final JCheckBox chckbxTwa = new JCheckBox("Twfahp");
		chckbxTwa.setSelected(true);
		PrecisionCheckboxes.add(chckbxTwa);
		
		final JCheckBox chckbxTpdohp = new JCheckBox("Tpdohp");
		chckbxTpdohp.setSelected(true);
		PrecisionCheckboxes.add(chckbxTpdohp);
		
		dimensionUserInput = new JTextField();
		dimensionUserInput.setBounds(115, 103, 89, 28);
		UserInputPanel.add(dimensionUserInput);
		dimensionUserInput.setColumns(10);
		
		JLabel lblDimensions = new JLabel("Dimensions:");
		lblDimensions.setBounds(6, 109, 81, 16);
		UserInputPanel.add(lblDimensions);
		
		JLabel lblTopTemperature = new JLabel("Top temp:");
		lblTopTemperature.setBounds(6, 137, 81, 16);
		UserInputPanel.add(lblTopTemperature);
		
		topTempTextBox = new JTextField();
		topTempTextBox.setBounds(115, 131, 89, 28);
		UserInputPanel.add(topTempTextBox);
		topTempTextBox.setColumns(10);
		
		bottomTempTextBox = new JTextField();
		bottomTempTextBox.setBounds(115, 161, 89, 28);
		UserInputPanel.add(bottomTempTextBox);
		bottomTempTextBox.setColumns(10);
		
		final JLabel lblBottomTemp = new JLabel("Bottom temp:");
		lblBottomTemp.setBounds(6, 167, 89, 16);
		UserInputPanel.add(lblBottomTemp);
		
		leftTempTextBox = new JTextField();
		leftTempTextBox.setBounds(115, 188, 89, 28);
		UserInputPanel.add(leftTempTextBox);
		leftTempTextBox.setColumns(10);
		
		final JLabel lblLeftTemp = new JLabel("Left temp:");
		lblLeftTemp.setBounds(6, 194, 89, 16);
		UserInputPanel.add(lblLeftTemp);
		
		rightTempTextBox = new JTextField();
		rightTempTextBox.setBounds(115, 216, 89, 28);
		UserInputPanel.add(rightTempTextBox);
		rightTempTextBox.setColumns(10);
		
		JLabel lblRightTemp = new JLabel("Right temp:");
		lblRightTemp.setBounds(6, 222, 100, 16);
		UserInputPanel.add(lblRightTemp);
		
		JButton btnRun = new JButton("Run");
		btnRun.setBounds(104, 257, 100, 29);
		UserInputPanel.add(btnRun);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(6, 257, 81, 29);
		UserInputPanel.add(btnExit);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(222, 6, 972, 766);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		final JTextPane tpfahpResults = new JTextPane();
		tpfahpResults.setBounds(501, 23, 465, 240);
		panel.add(tpfahpResults);
		
		final JLabel lblTpdahpResults = new JLabel("Tpdahp Results");
		lblTpdahpResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpdahpResults.setForeground(Color.WHITE);
		lblTpdahpResults.setBounds(6, 6, 475, 16);
		panel.add(lblTpdahpResults);
		
		final JLabel lblTpfahpReults = new JLabel("Tpfahp Results");
		lblTpfahpReults.setForeground(Color.WHITE);
		lblTpfahpReults.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpfahpReults.setBounds(502, 6, 464, 16);
		panel.add(lblTpfahpReults);
		
		final JTextPane twfahpResults = new JTextPane();
		twfahpResults.setBounds(6, 410, 475, 240);
		panel.add(twfahpResults);
		
		JLabel lblNewLabel = new JLabel("Twfahp Results");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(6, 392, 460, 16);
		panel.add(lblNewLabel);
		
		final JTextPane tpdohpResults = new JTextPane();
		tpdohpResults.setBackground(Color.WHITE);
		tpdohpResults.setForeground(Color.BLACK);
		tpdohpResults.setBounds(501, 410, 465, 240);
		panel.add(tpdohpResults);
		
		JLabel lblTpdohpResults = new JLabel("Tpdohp Results");
		lblTpdohpResults.setForeground(Color.WHITE);
		lblTpdohpResults.setHorizontalAlignment(SwingConstants.CENTER);
		lblTpdohpResults.setBounds(491, 392, 475, 16);
		panel.add(lblTpdohpResults);
		
		final JTextPane twfahpDesignStudy = new JTextPane();
		twfahpDesignStudy.setBounds(6, 662, 475, 98);
		panel.add(twfahpDesignStudy);
		
		final JTextPane tpdohpDesignStudy = new JTextPane();
		tpdohpDesignStudy.setBounds(501, 662, 465, 98);
		panel.add(tpdohpDesignStudy);
		
		final JTextPane tpdahpResults = new JTextPane();
		panel.add(tpdahpResults);
		tpdahpResults.setBounds(6, 23, 475, 240);
		
		final JTextPane tpdahpDesignStudy = new JTextPane();
		tpdahpDesignStudy.setBounds(6, 271, 475, 98);
		panel.add(tpdahpDesignStudy);
		
		final JTextPane tpfahpDesignStudy = new JTextPane();
		tpfahpDesignStudy.setBounds(501, 271, 465, 98);
		panel.add(tpfahpDesignStudy);
		
		JLabel lblDiffusionDesign = new JLabel("Heated Plate Design Study");
		lblDiffusionDesign.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblDiffusionDesign.setHorizontalAlignment(SwingConstants.CENTER);
		lblDiffusionDesign.setBounds(6, 6, 210, 31);
		getContentPane().add(lblDiffusionDesign);
		
		// action listener for exit button
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		btnRun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String [] arguments = {"-d", dimensionUserInput.getText(),"-t", topTempTextBox.getText(), "-b", 
						bottomTempTextBox.getText(), "-l", leftTempTextBox.getText(), "-r", rightTempTextBox.getText()};
				if(chckbxTpdahp.isSelected()){
					long start = System.currentTimeMillis();
					tpdahpResults.setText(runTest("Tpdahp", arguments));
					String runtime = "Time: "+ String.valueOf(System.currentTimeMillis() - start) + " ms";
					long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					tpdahpDesignStudy.setText(runtime + "\nRelative change stopping criterion: .00001" + "\nIterations: " + iterationCount +
							"\nMemory Used: "+ usedMemory + " bytes");
				}if(chckbxTpfahp.isSelected()){
					long start = System.currentTimeMillis();
					tpfahpResults.setText(runTest("Tpfahp", arguments));
					String runtime = "Time: "+ String.valueOf(System.currentTimeMillis() - start) + " ms";
					long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					tpfahpDesignStudy.setText(runtime + "\nRelative change stopping criterion: .00001" + "\nIterations: " + iterationCount +
							"\nMemory Used: "+ usedMemory + " bytes");
				}if(chckbxTwa.isSelected()){
					long start = System.currentTimeMillis();
					twfahpResults.setText(runTest("Twfahp", arguments));
					String runtime = "Time: "+ String.valueOf(System.currentTimeMillis() - start) + " ms";
					long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					twfahpDesignStudy.setText(runtime + "\nRelative change stopping criterion: .00001" + "\nIterations: " + iterationCount +
							"\nMemory Used: "+ usedMemory + " bytes");
				}if(chckbxTpdohp.isSelected()){
					long start = System.currentTimeMillis();
					tpdohpResults.setText(runTest("Tpdohp", arguments));
					String runtime = "Time: "+ String.valueOf(System.currentTimeMillis() - start) + " ms";
					long usedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
					tpdohpDesignStudy.setText(runtime + "\nRelative change stopping criterion: .00001" + "\nIterations: " + iterationCount +
							"\nMemory Used: "+ usedMemory + " bytes");
				}
			}
		});
	}
	
	public String runTest(String check, String arguments[]){
		String output = "";
		switch(check) {
		case "Tpdahp":
			Tpdahp.Arguments diffArgs1 = new Tpdahp.Arguments(arguments);
			Tpdahp.Demo temp = new Tpdahp.Demo(arguments);
			temp.main(arguments);
			output = temp.results;
			iterationCount = temp.iterationCount;
			break;
		case "Tpfahp":
			Tpfahp.Demo temp2 = new Tpfahp.Demo(arguments);
			temp2.main(arguments);
			output = temp2.results;
			iterationCount = temp2.iterationCount;
			break;
		case "Twfahp":
			Twfahp.Demo temp3 = new Twfahp.Demo(arguments);
			temp3.main(arguments);
			output = temp3.results;
			iterationCount = temp3.iterationCount;
			break;
		case "Tpdohp":
			Tpdohp.Demo temp4 = new Tpdohp.Demo(arguments);
			temp4.main(arguments);
			output = temp4.results;
			iterationCount = temp4.iterationCount;
			break;
		default:
			output = "default";
			break;
		}
		return output;
	}
}

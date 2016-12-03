package edu.miamioh.fugettcj.Project2;

import javax.swing.JButton;

/**
 * @author Chase Fugett
 * Creates a ChessButton which contains row and column data
 */
public class ChessButton extends JButton{

	private int row;
	private int column;
	
	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * @param row
	 * @param column
	 */
	public ChessButton(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	
	
	
}

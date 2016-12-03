package edu.miamioh.fugettcj.Project2;

/**
 * @author Chase Fugett
 * @section E
 * @instructor Matthew Stephan
 * @purpose creates a queen in the eight queens problem
 * NOTE: this class has been sourced from the textbook
 */
public class Queen {

	private int row;
	private int column;

	/**
	 * Constructs a queen at a given position
	 * 
	 * @param r
	 * @param c
	 */
	public Queen(int r, int c) {
		row = r;
		column = c;
	}

	/**
	 * Checks whether this queen attacks another
	 * @param other
	 * @return true if this and the other queen are in the same row, column,
	 * or diagonal
	 */
	public boolean attacks(Queen other) {
		return row == other.row
				|| column == other.column
				|| Math.abs(row - other.row) == 
				Math.abs(column - other.column);
	}

	@Override
	/**
	 * Overrides the toString method
	 */
	public String toString() {
		return "" + "ABCDEFGH".charAt(column - 1) + (row);
	}

	@Override
	/**
	 * Overrides the equals method
	 */
	public boolean equals(Object o) {
		if (o instanceof Queen) {
			Queen q = (Queen) o;
			return row == q.row && column == q.column;
		}
		else {
			return false;
		}
	}

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
	
	

}

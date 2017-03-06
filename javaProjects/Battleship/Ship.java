
/**
 * @author Chase Fugett
 * @purpose creates a ship of a specific size and location on the board
 *
 */
public class Ship {
	
	private int startRow;
	private int startColumn;
	private int length;
	private String direction;
	
	/**
	 * @param startRow
	 * @param startColumn
	 * @param length
	 * @param direction
	 */
	public Ship(int startRow, int startColumn, int length, String direction) {
		super();
		this.startRow = startRow;
		this.startColumn = startColumn;
		this.length = length;
		this.direction = direction;
	}

	/**
	 * @return the startRow
	 */
	public int getStartRow() {
		return startRow;
	}

	/**
	 * @param startRow the startRow to set
	 */
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	/**
	 * @return the startColumn
	 */
	public int getStartColumn() {
		return startColumn;
	}

	/**
	 * @param startColumn the startColumn to set
	 */
	public void setStartColumn(int startColumn) {
		this.startColumn = startColumn;
	}

	/**
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	

}

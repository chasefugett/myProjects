package Tpfahp;

public class Plate {

	private float[][] layout = null;

	public Plate(final Arguments diffArgs) {
		this.layout = new float[diffArgs.d + 2][diffArgs.d + 2];
		initialize(diffArgs);
	}

	public void initialize(final Arguments diffArgs) {
		for (int row = 0; row < diffArgs.d+2; row++) {
			for (int cell = 0; cell < diffArgs.d+2; cell++) {
				if (row == 0) {
					layout[row][cell] = (float) diffArgs.top;
				} else if (row == ((diffArgs.d+2) - 1)) {
					layout[row][cell] = (float) diffArgs.bottom;
				} else if (cell == 0) {
					layout[row][cell] = (float) diffArgs.left;
				} else if (cell == ((diffArgs.d+2) - 1)) {
					layout[row][cell] = (float) diffArgs.right;
				} else {
					layout[row][cell] = 0;
				}
			}
		}
	}

	public float getCell(int row, int column) {
		return layout[row][column];
	}

	public void setCell(int row, int column, float d) {
		layout[row][column] = d;
	}
}


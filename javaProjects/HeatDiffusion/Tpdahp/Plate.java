package Tpdahp;

public class Plate {

	public double[][] layout = null;

	public Plate(final Arguments args) {
		this.layout = new double[args.d + 2][args.d + 2];
		initialize(args);
	}

	public void initialize(final Arguments args) {
		for (int row = 0; row < args.d+2; row++) {
			for (int cell = 0; cell < args.d+2; cell++) {
				if (row == 0) {
					layout[row][cell] = args.top;
				} else if (row == ((args.d+2) - 1)) {
					layout[row][cell] = args.bottom;
				} else if (cell == 0) {
					layout[row][cell] = args.left;
				} else if (cell == ((args.d+2) - 1)) {
					layout[row][cell] = args.right;
				} else {
					layout[row][cell] = 0;
				}
			}
		}
	}

	public double getCell(int row, int column) {
		return layout[row][column];
	}

	public void setCell(int row, int column, double item) {
		layout[row][column] = item;
	}
}

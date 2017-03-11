package Tpdohp;

import java.util.ArrayList;

public class Plate {
	private ArrayList<ArrayList<Double>> layout;

	public Plate(final Arguments args) {
		this.layout = new ArrayList<ArrayList<Double>>();

		for (int i = 0; i <= args.d + 2; i++) {
			layout.add(new ArrayList<Double>());
		}
		for (int i = 0; i <= args.d + 2; i++) {
			for (int j = 0; j <= args.d + 2; j++) {
				layout.get(i).add(j, 0.0);
			}
		}
		initialize(args);
	}

	public void initialize(final Arguments args) {
		for (int row = 0; row < args.d + 2; row++) {
			for (int cell = 0; cell < args.d + 2; cell++) {
				if (row == 0) {
					layout.get(row).set(cell, args.top);
				} else if (row == ((args.d + 2) - 1)) {
					layout.get(row).set(cell, args.bottom);
				} else if (cell == 0) {
					layout.get(row).set(cell, args.left);
				} else if (cell == ((args.d + 2) - 1)) {
					layout.get(row).set(cell, args.right);
				} else {
					layout.get(row).set(cell, 0.0);
				}
			}
		}
	}

	public double getCell(int row, int column) {
		return layout.get(row).get(column);
	}

	public void setCell(int row, int column, double item) {
		layout.get(row).set(column, item);
	}
}
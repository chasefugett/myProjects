package Twfahp;

public class DiffusionSimulation {
	private final Arguments args;
	private final Plate oldPlate;
	private final Plate newPlate;
	private final int ITERATION_LIMIT = 100;
	public int iterationCount;
	
	public DiffusionSimulation(final Arguments args, final Plate oldPlate, final Plate newPlate) {
		this.args = args;
		this.oldPlate = oldPlate;
		this.newPlate = newPlate;
	}

	public void calculate() {
		iterationCount = 0;

		while (!done(iterationCount, oldPlate, newPlate)) {
			for (int iteration = 0; iteration < ITERATION_LIMIT; iteration++) {
				for (int i = 1; i <= args.d; i++) {
					for (int j = 1; j <= args.d; j++) {
						// Compute lattice point temperature as average of
						// neighbors
						newPlate.setCell(i, j, (float) ((oldPlate.getCell(i + 1, j) + oldPlate.getCell(i - 1, j)
								+ oldPlate.getCell(i, j + 1) + oldPlate.getCell(i, j - 1)) / 4.0));

					}
				}

			}
			// Swap the plates and continue
			swap(oldPlate, newPlate);
			iterationCount++;
		}
	}
	
	public String display() {
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= args.d; i++) {
			for (int j = 1; j <= args.d; j++) {
				String cell = String.format("%.2f", newPlate.getCell(i, j));
				sb.append(cell + "\t");
				if (j == args.d) {
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	private void swap(Plate oldPlate, Plate newPlate) {
		for (int i = 0; i < args.d + 2; i++) {
			for (int j = 0; j < args.d + 2; j++) {
				oldPlate.setCell(i, j, newPlate.getCell(i, j));
			}
		}
	}

	private boolean done(int count, Plate oldPlate, Plate newPlate) {
		int changeCount = 0;
		if (count < args.d) {
			return false;
		} else if (count > ITERATION_LIMIT) {
			return true;
		} else {
			for (int i = 1; i < args.d+1; i++) {
				for (int j = 1; j < args.d+1; j++) {
					if (Math.abs(newPlate.getCell(i, j) - oldPlate.getCell(i, j)) < .00001) {
						changeCount++;
					}
				}
			}
			if (changeCount > (args.d * args.d)) {
				return true;
			}
		}
		return false;
	}

}




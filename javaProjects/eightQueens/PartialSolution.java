package edu.miamioh.fugettcj.Project2;

import java.util.Arrays;

/**
 * @author Chase Fugett
 * Constructs a partial solution to the eight queens problem
 */
public class PartialSolution {

	// An array of potential solutions
	private Queen[] queens;
	
	// The number of queens that should be on the board to win
	private static final int NQUEENS = 8;
	
	public static final int ACCEPT = 1;
	public static final int ABANDON = 2;
	public static final int CONTINUE = 3;
	
	/**
	 * Constructs a partial solution of the given size
	 * @param size
	 */
	public PartialSolution(int size) {
		queens = new Queen[size];
	}
	
	/**
	 * Examine a partial solution
	 * @return one of accept, abandon, or continue
	 */
	public int examine() {
		// Checks to see if any queens attack each other. Solution is abandoned
		// if they do
		for (int i = 0; i < queens.length; i++) {
			for (int j = i + 1; j < queens.length; j++) {
				if (queens[i].attacks(queens[j])) {
					return ABANDON;
				}
			}
		}
		// If there are as many queens in the acceptable solutions array as 
		// there should be queens on the board, then return accept
		if (queens.length == NQUEENS) {
			return ACCEPT;
		}
		// Otherwise the solution is acceptable and may continue
		else {
			return CONTINUE;
		}
	}
	
	/**
	 * Gives all extensions of this partial solution
	 * @return an array of partial solutions that extend this solution
	 */
	public PartialSolution[] extend() {
		// Generate a new solution for each column
		PartialSolution[] result = new PartialSolution[NQUEENS];
		for (int i = 0; i < result.length; i ++) {
			int size = queens.length;
			
			// The new solution has one more row than this one
			result[i] = new PartialSolution(size + 1);
			
			// Copy this solution into the new one
			for (int j = 0; j < size; j++) {
				result[i].queens[j] = queens[j];
			}
			
			// Append the new queen into the ith column
			result[i].queens[size] = new Queen(size, i);
		}
		
		return result;
	}

	@Override
	/**
	 * Overrides the toString method
	 */
	public String toString() {
		return Arrays.toString(queens);
	}

}

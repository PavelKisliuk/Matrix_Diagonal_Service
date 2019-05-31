/*  By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.model;

import java.util.Arrays;

/**
 * The {@code SquareMatrix} class is class-wrapper for two-dimensional array representation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class SquareMatrix {
	/**
	 * Two-dimensional array.
	 */
	private int[][] squareMatrix;

	/**
	 * Default constructor.
	 */
	public SquareMatrix() {
		squareMatrix = new int[0][];
	}

	/**
	 * Constructor for {@code SquareMatrix}.
	 * <p>
	 *
	 * @param size is size of {@code squareMatrix}.
	 */
	public SquareMatrix(int size) {
		squareMatrix = new int[size][size];
	}

	/**
	 * Return element of {@code squareMatrix}.
	 * <p>
	 *
	 * @param row    is row of {@code squareMatrix}.
	 * @param column is column of {@code squareMatrix}.
	 * @return element of {@code squareMatrix}.
	 */
	public int get(int row, int column) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return -1;
		}
		return squareMatrix[row][column];
	}

	/**
	 * Set special element to {@code squareMatrix}.
	 * <p>
	 *
	 * @param row    is row of {@code squareMatrix}.
	 * @param column is column of {@code squareMatrix}.
	 * @param value  is value for setting.
	 * @return {@code true} if element was set, else {@code false}.
	 */
	public boolean set(int row, int column, int value) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return false;
		}
		squareMatrix[row][column] = value;
		return true;
	}

	/**
	 * @return size of {@code squareMatrix}.
	 */
	public int size() {
		return squareMatrix.length;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SquareMatrix that = (SquareMatrix) o;

		return Arrays.deepEquals(squareMatrix, that.squareMatrix);

	}

	@Override
	public int hashCode() {
		return Arrays.deepHashCode(squareMatrix);
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < squareMatrix.length; i++) {
			for (int j = 0; j < squareMatrix.length; j++) {
				stringBuilder.append(squareMatrix[i][j]).append("-");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("\n");
		}
		return "SquareMatrix: \n" + stringBuilder.toString();
	}

	/**
	 * @return diagonal element's of {@code squareMatrix};
	 */
	public String toStringDiagonal() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < squareMatrix.length; i++) {
			stringBuilder.append(squareMatrix[i][i]).append("-");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("\n");
		return "Diagonal: \n" + stringBuilder.toString();
	}
}
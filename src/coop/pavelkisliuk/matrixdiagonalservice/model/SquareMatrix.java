package coop.pavelkisliuk.matrixdiagonalservice.model;

import java.util.Arrays;

public class SquareMatrix {
	private int[][] squareMatrix;

	public SquareMatrix() {
		squareMatrix = new int[0][];
	}

	public SquareMatrix(int size) {
		squareMatrix = new int[size][size];
	}

	public int get(int row, int column) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return -1;
		}
		return squareMatrix[row][column];
	}

	public boolean set(int row, int column, int value) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return false;
		}
		squareMatrix[row][column] = value;
		return true;
	}

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
		for(int i = 0; i < squareMatrix.length; i++) {
			for(int j = 0; j < squareMatrix.length; j++) {
				stringBuilder.append(squareMatrix[i][j]).append("-");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("\n");
		}
		return "SquareMatrix: \n" + stringBuilder.toString();
	}

	public String toStringDiagonal() {
		StringBuilder stringBuilder = new StringBuilder();
		for(int i = 0; i < squareMatrix.length; i++) {
			stringBuilder.append(squareMatrix[i][i]).append("-");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("\n");
		return "Diagonal: \n" + stringBuilder.toString();
	}
}
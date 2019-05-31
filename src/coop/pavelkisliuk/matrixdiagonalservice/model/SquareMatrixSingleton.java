package coop.pavelkisliuk.matrixdiagonalservice.model;

import java.util.concurrent.locks.ReentrantLock;

public enum SquareMatrixSingleton {
	INSTANCE;

	private Cell[][] squareMatrix;
	private static final ReentrantLock LOCK = new ReentrantLock();

	private class Cell {
		private int value;

		Cell(int value) {
			this.value = value;
		}

		private int getValue() {
			return value;
		}

		private boolean setValue(int value) {
			try {
				LOCK.lock();
				if (this.value != 0) {
					return false;
				} else {
					this.value = value;
					return true;
				}
			} finally {
				LOCK.unlock();
			}
		}

		@Override
		public String toString() {
			return String.valueOf(value);
		}
	}

	SquareMatrixSingleton() {
		init();
	}

	private void init() {
		MatrixSpecializeCreator creator = new MatrixSpecializeCreator();
		SquareMatrix tempMatrix = creator.create();

		squareMatrix = new Cell[tempMatrix.size()][tempMatrix.size()];
		for (int i = 0; i < squareMatrix.length; i++) {
			for (int j = 0; j < squareMatrix[i].length; j++) {
				squareMatrix[i][j] = new Cell(tempMatrix.get(i, j));
			}
		}
	}

	public int get(int row, int column) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return -1;
		}
		return squareMatrix[row][column].getValue();
	}

	public boolean set(int row, int column, int value) {
		if (row < 0 || row > squareMatrix.length ||
				column < 0 || column > squareMatrix[row].length) {
			return false;
		}
		return squareMatrix[row][column].setValue(value);
	}

	public int size() {
		return squareMatrix.length;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Cell[] matrix : squareMatrix) {
			for (Cell cell : matrix) {
				stringBuilder.append(cell).append("-");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("\n");
		}
		return "SquareMatrix: \n" + stringBuilder.toString();
	}

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
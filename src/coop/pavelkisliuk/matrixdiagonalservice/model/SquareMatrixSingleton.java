/*  By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.model;

import java.util.concurrent.locks.ReentrantLock;

/**
 * The {@code SquareMatrixSingleton} class is thread-safety singleton class-wrapper
 * for two-dimensional array representation.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see MatrixSpecializeCreator
 * @since 12.0
 */
public enum SquareMatrixSingleton {
	/**
	 * Instance of singleton.
	 */
	INSTANCE;

	/**
	 * Two-dimensional array.
	 */
	private Cell[][] squareMatrix;

	/**
	 * The {@code Cell} class is class-wrapper for representation of two-dimension array cell.
	 */
	private class Cell {
		/**
		 * Lock for multithreading realization.
		 */
		private final ReentrantLock LOCK = new ReentrantLock();

		/**
		 * Value of cell.
		 */
		private int value;

		/**
		 * Constructor for {@code Cell}.
		 * <p>
		 *
		 * @param value is value for initialization.
		 */
		Cell(int value) {
			this.value = value;
		}

		/**
		 * @return {@code value}
		 */
		private int getValue() {
			return value;
		}

		/**
		 * Set {@code value} special value only if previous value is 0.
		 * <p>
		 *
		 * @param value is value for setting.
		 * @return {@code true} if value was set.
		 */
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

	/**
	 * Default constructor.
	 */
	SquareMatrixSingleton() {
		init();
	}

	/**
	 * Initialize {@code squareMatrix}.
	 */
	private void init() {
		MatrixSpecializeCreator creator = new MatrixSpecializeCreator();
		//created temp SquareMatrix.
		SquareMatrix tempMatrix = creator.create();

		//Copy tempMatrix to this.
		squareMatrix = new Cell[tempMatrix.size()][tempMatrix.size()];
		for (int i = 0; i < squareMatrix.length; i++) {
			for (int j = 0; j < squareMatrix[i].length; j++) {
				squareMatrix[i][j] = new Cell(tempMatrix.get(i, j));
			}
		}
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
		return squareMatrix[row][column].getValue();
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
		return squareMatrix[row][column].setValue(value);
	}

	/**
	 * @return size of {@code squareMatrix}.
	 */
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
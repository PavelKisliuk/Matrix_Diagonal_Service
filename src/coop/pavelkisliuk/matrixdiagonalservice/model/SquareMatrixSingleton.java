package coop.pavelkisliuk.matrixdiagonalservice.model;

import coop.pavelkisliuk.matrixdiagonalservice.creator.SquareMatrixCreator;
import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.reader.MatrixFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public enum SquareMatrixSingleton {
	INSTANCE;

	private int[][] squareMatrix;
	private int size;
	private static ReentrantLock lock = new ReentrantLock();

	SquareMatrixSingleton() {
		List<String> squareMatrixString;

		try {
			squareMatrixString = new MatrixFileReader().read("testfile/Square_Matrix.txt");
		} catch (CustomException e) {
			String defaultString = "0-0-0-0-0-0-0-0-0-0";
			squareMatrixString = new ArrayList<>();
			for(int i = 0; i < 10; i++) {
				squareMatrixString.add(defaultString);
			}
		}

		SquareMatrix tempSquareMatrix = new SquareMatrixCreator().create(squareMatrixString);
		size = tempSquareMatrix.size();
		squareMatrix = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				squareMatrix[i][j] = tempSquareMatrix.get(i, j);
			}
		}
	}

	public int get(int row, int column) {
		return squareMatrix[row][column];
	}

	public boolean set(int row, int column, int value) {
		try {
			lock.lock();
			if (squareMatrix[row][column] == 0) {
				squareMatrix[row][column] = value;
				return true;
			} else {
				return false;
			}
		} finally {
			lock.unlock();
		}
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				stringBuilder.append(squareMatrix[i][j]).append("-");
			}
			stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			stringBuilder.append("\n");
		}
		return "SquareMatrix: \n" + stringBuilder.toString();
	}

	public String toStringDiagonal() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < size; i++) {
			stringBuilder.append(squareMatrix[i][i]).append("-");
		}
		stringBuilder.deleteCharAt(stringBuilder.length() - 1);
		stringBuilder.append("\n");
		return "Diagonal: \n" + stringBuilder.toString();
	}
}

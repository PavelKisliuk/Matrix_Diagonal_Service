package coop.pavelkisliuk.matrixdiagonalservice.model;

import coop.pavelkisliuk.matrixdiagonalservice.creator.SquareMatrixCreator;
import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.reader.MatrixFileReader;

import java.util.ArrayList;
import java.util.List;

public class MatrixSpecializeCreator {
	public static final String PATH;

	static {
		PATH = "testfile/Square_Matrix.txt";
	}

	SquareMatrix create() {
		MatrixFileReader reader = new MatrixFileReader();
		SquareMatrixCreator creator = new SquareMatrixCreator();

		List<String> stringMatrix;
		try {
			stringMatrix = reader.read(PATH);
		} catch (CustomException e) {
			stringMatrix = new ArrayList<>();
			for(int i = 0; i < 10; i++) {
				stringMatrix.add("0-0-0-0-0-0-0-0-0-0");
			}
		}

		return creator.create(stringMatrix);
	}
}

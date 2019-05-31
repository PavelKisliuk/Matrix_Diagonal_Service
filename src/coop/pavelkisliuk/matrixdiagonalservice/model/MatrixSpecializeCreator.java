/*  By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.model;

import coop.pavelkisliuk.matrixdiagonalservice.creator.SquareMatrixCreator;
import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.reader.MatrixFileReader;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code MatrixSpecializeCreator} class is class for creation of {@code SquareMatrix} for
 * {@code SquareMatrixSingleton}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see SquareMatrix
 * @see SquareMatrixSingleton
 * @since 12.0
 */
class MatrixSpecializeCreator {
	/**
	 * Path to file.
	 */
	public static final String PATH;

	static {
		PATH = "testfile/Square_Matrix.txt";
	}

	/**
	 * Return {@code SquareMatrix} created from {@code List} of {@code String} from file red from {@code PATH}.
	 * <p>
	 *
	 * @return {@code SquareMatrix}.
	 */
	SquareMatrix create() {
		MatrixFileReader reader = new MatrixFileReader();
		SquareMatrixCreator creator = new SquareMatrixCreator();

		List<String> stringMatrix;
		try {
			stringMatrix = reader.read(PATH);
		} catch (CustomException e) {
			stringMatrix = new ArrayList<>();
			for (int i = 0; i < 10; i++) {
				stringMatrix.add("0-0-0-0-0-0-0-0-0-0");
			}
		}

		return creator.create(stringMatrix);
	}
}
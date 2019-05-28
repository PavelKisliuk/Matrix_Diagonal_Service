package coop.pavelkisliuk.matrixdiagonalservice.creator;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrix;
import coop.pavelkisliuk.matrixdiagonalservice.reader.MatrixFileReader;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;

public class MatrixCreatorTest {
	private MatrixCreator matrixCreator = new MatrixCreator();

	@Test
	public void testCreateNull() {
		assertEquals(matrixCreator.create(null), new SquareMatrix());
	}

	@Test
	public void testCreateCorrect() throws CustomException {
		SquareMatrix expected = new SquareMatrix(10);
		List<String> list = new MatrixFileReader().read("testfile/Square_Matrix.txt");
		assertEquals(matrixCreator.create(list), expected);
	}
}
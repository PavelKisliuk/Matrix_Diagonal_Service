package coop.pavelkisliuk.matrixdiagonalservice.creator;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrix;
import coop.pavelkisliuk.matrixdiagonalservice.reader.MatrixFileReader;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class SquareMatrixCreatorTest {
	private SquareMatrixCreator creator = new SquareMatrixCreator();

	@Test
	public void testCreateNull() {
		assertEquals(creator.create(null), new SquareMatrix());
	}

	@Test
	public void testCreateEmpty() {
		assertEquals(creator.create(new ArrayList<>()), new SquareMatrix());
	}

	@Test
	public void testCreateCorrect() throws CustomException {
		SquareMatrix expected = new SquareMatrix(10);
		List<String> list = new MatrixFileReader().read("testfile/Square_Matrix.txt");
		assertEquals(creator.create(list), expected);
	}

	@Test
	public void testCreateIncorrect() {
		List<String> list = new ArrayList<>();
		list.add("0-0-0-0-0-0-0");
		list.add("0-0-0-0-0-0-0");
		assertEquals(creator.create(list), new SquareMatrix());
	}
}
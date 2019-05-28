package coop.pavelkisliuk.matrixdiagonalservice.reader;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

public class MatrixFileReaderTest {
	private MatrixFileReader necklaceFileReader = new MatrixFileReader();

	@Test(expectedExceptions = CustomException.class)
	public void testReadNull() throws CustomException {
		necklaceFileReader.read(null);
	}

	@Test(expectedExceptions = CustomException.class)
	public void testReadEmpty() throws CustomException {
		necklaceFileReader.read("");
	}

	@Test(expectedExceptions = CustomException.class)
	public void testReadNotExist() throws CustomException {
		necklaceFileReader.read("sgsgdgfgdfg");
	}

	@Test
	public void testRead() throws CustomException {
		List<String> expected = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			expected.add("0-0-0-0-0-0-0-0-0-0");
		}

		assertEquals(necklaceFileReader.read("testfile/Square_Matrix.txt"), expected);
	}
}
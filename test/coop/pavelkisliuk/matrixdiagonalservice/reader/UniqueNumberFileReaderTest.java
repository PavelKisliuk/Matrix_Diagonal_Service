package coop.pavelkisliuk.matrixdiagonalservice.reader;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class UniqueNumberFileReaderTest {
	private UniqueNumberFileReader fileReader = new UniqueNumberFileReader();

	@Test(expectedExceptions = CustomException.class)
	public void testReadNull() throws CustomException {
		fileReader.read(null);
	}

	@Test(expectedExceptions = CustomException.class)
	public void testReadEmpty() throws CustomException {
		fileReader.read("");
	}

	@Test(expectedExceptions = CustomException.class)
	public void testReadNotExist() throws CustomException {
		fileReader.read("sgsgdgfgdfg");
	}

	@Test
	public void testRead() throws CustomException {
		String expected = "4-16-64-256-1024";

		assertEquals(fileReader.read("testfile/Unique_Numbers.txt"), expected);
	}
}
package coop.pavelkisliuk.matrixdiagonalservice.creator;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.reader.UniqueNumberFileReader;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.testng.Assert.assertEquals;

public class UniqueNumberCreatorTest {
	private UniqueNumberCreator creator = new UniqueNumberCreator();

	@Test
	public void testCreateNull() {
		assertEquals(creator.create(null), new ArrayList<>());
	}

	@Test
	public void testCreateEmpty() {
		assertEquals(creator.create(""), new ArrayList<>());
	}

	@Test
	public void testCreateCorrect() throws CustomException {
		ArrayList<Integer> expected = new ArrayList<>();
		expected.add(4);
		expected.add(16);
		expected.add(64);
		expected.add(256);
		expected.add(1024);
		String str = new UniqueNumberFileReader().read("testfile/Unique_Numbers.txt");
		assertEquals(creator.create(str), expected);
	}
}
/*  By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.logic;

import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

/**
 * The {@code DiagonalPropagator} class implementation of {@code Runnable} interface
 * propagate diagonal of {@code SquareMatrix} by unique numbers.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton
 * @since 12.0
 */
public class DiagonalPropagator implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Unique number.
	 */
	private int uniqueNumber;

	/**
	 * Matrix shared by different threads.
	 */
	private SquareMatrixSingleton sharedMatrix;

	/**
	 * Variable for creating random numbers, which ensure unpredictability.
	 */
	private Random randomNumber = new SecureRandom();

	public DiagonalPropagator(int uniqueNumber, SquareMatrixSingleton sharedMatrix) {
		this.uniqueNumber = uniqueNumber;
		this.sharedMatrix = sharedMatrix;
	}

	@Override
	public void run() {
		int index = randomNumber.nextInt(sharedMatrix.size());
		do {
			if (sharedMatrix.set(index, index, uniqueNumber)) {
				LOGGER.log(Level.DEBUG, "Add in " + index + " element value " + uniqueNumber);
			} else {
				LOGGER.log(Level.DEBUG, "post addition " + uniqueNumber + " in " + index + " element");
			}
		} while ((index = findEmptyCell()) != -1);
	}

	/**
	 * Return random index of empty (0 in this case) matrix diagonal element.
	 *
	 * @return random index of empty (0 in this case) matrix diagonal element.
	 */
	private int findEmptyCell() {
		ArrayList<Integer> emptyCellGroup = new ArrayList<>();

		for (int i = 0; i < sharedMatrix.size(); i++) {
			if (sharedMatrix.get(i, i) == 0) {
				LOGGER.log(Level.TRACE, "empty place is " + i);
				emptyCellGroup.add(i);
			}
		}

		return !emptyCellGroup.isEmpty() ? emptyCellGroup.get(randomNumber.nextInt(emptyCellGroup.size())) : -1;
	}
}
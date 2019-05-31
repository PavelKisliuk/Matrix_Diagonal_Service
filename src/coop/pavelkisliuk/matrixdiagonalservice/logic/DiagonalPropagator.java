package coop.pavelkisliuk.matrixdiagonalservice.logic;

import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

public class DiagonalPropagator implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();

	private int uniqueNumber;
	private SquareMatrixSingleton sharedMatrix;
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
				LOGGER.log(Level.DEBUG, "Add in " + index + " elmnt value " + uniqueNumber);
			} else {
				LOGGER.log(Level.DEBUG, "post addition in " + index + " elmnt");
			}
		} while ((index = findEmptyCell()) != -1);
	}

	private int findEmptyCell() {
		ArrayList<Integer> emptyCellGroup = new ArrayList<>();

		int i = 0;
		while (i < sharedMatrix.size()) {
			if (sharedMatrix.get(i, i) == 0) {
				LOGGER.log(Level.TRACE, "empty place is " + i);
				emptyCellGroup.add(i);
			}
			i++;
		}

		return !emptyCellGroup.isEmpty() ? emptyCellGroup.get(randomNumber.nextInt(emptyCellGroup.size())) : -1;
	}
}
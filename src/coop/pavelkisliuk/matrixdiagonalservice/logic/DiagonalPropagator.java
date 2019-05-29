package coop.pavelkisliuk.matrixdiagonalservice.logic;

import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.SecureRandom;
import java.util.Random;

public class DiagonalPropagator implements Runnable {
	private static final Logger LOGGER = LogManager.getLogger();

	private int uniqueNumber;
	private SquareMatrixSingleton sharedMatrix;

	public DiagonalPropagator(int uniqueNumber, SquareMatrixSingleton sharedMatrix) {
		this.uniqueNumber = uniqueNumber;
		this.sharedMatrix = sharedMatrix;
	}

	@Override
	public void run() {
		Random randomNumber = new SecureRandom();

		int index;
		do {
			index = randomNumber.nextInt(sharedMatrix.size());
			if (sharedMatrix.set(index, index, uniqueNumber)) {
				LOGGER.log(Level.DEBUG, "Add in " + (index + 1) + " elmnt value " + uniqueNumber);
			}
		} while (!isDiagonalPropagate());
	}

	private boolean isDiagonalPropagate() {
		int i = 0;
		while (i < sharedMatrix.size()) {
			if (sharedMatrix.get(i, i) == 0) {
				LOGGER.log(Level.DEBUG, "empty place is " + i);
				return false;
			}
			i++;
		}
		return true;
	}
}
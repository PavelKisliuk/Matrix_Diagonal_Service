/*  By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.creator;

import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The {@code SquareMatrixCreator} class create instance of {@code SquareMatrix}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @see coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrix
 * @see coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton
 * @since 12.0
 */
public class SquareMatrixCreator {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Return {@code SquareMatrix} created from {@code List} of {@code String}.
	 * <p>
	 *
	 * @param elementGroup is data for {@code SquareMatrix} creation.
	 * @return {@code SquareMatrix} created from {@code List} of {@code String}.
	 */
	public SquareMatrix create(List<String> elementGroup) {
		if (elementGroup == null) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> " + null);
			return new SquareMatrix();
		}
		if (elementGroup.isEmpty()) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> empty List");
			return new SquareMatrix();
		}

		LOGGER.log(Level.TRACE, "Start create matrix.");
		SquareMatrix squareMatrix = new SquareMatrix(elementGroup.size());
		LOGGER.log(Level.DEBUG, "Created square matrix with size: " + elementGroup.size());

		for (int row = 0; row < elementGroup.size(); row++) {
			String[] valueGroup = elementGroup.get(row).split("-");

			for (int column = 0; column < valueGroup.length; column++) {
				squareMatrix.set(row, column, Integer.parseInt(valueGroup[column]));
			}
		}

		LOGGER.log(Level.DEBUG, "Return created matrix:\n" + squareMatrix.toString());
		return squareMatrix;
	}
}
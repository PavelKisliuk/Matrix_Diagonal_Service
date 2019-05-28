package coop.pavelkisliuk.matrixdiagonalservice.creator;

import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrix;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SquareMatrixCreator {
	private static final Logger LOGGER = LogManager.getLogger();

	public SquareMatrix create(List<String> elementGroup) {
		if (elementGroup == null) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> " + null);
			return new SquareMatrix();
		}
		if (elementGroup.isEmpty()) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> empty List");
			return new SquareMatrix();
		}
		//----------------------------------------------------------------
		LOGGER.log(Level.TRACE, "Start create matrix.");
		//----------------------------------------------------------------
		String[] valueGroup = elementGroup.get(0).split("-");
		//----------------------------------------------------------------
		if (valueGroup.length != elementGroup.size()) {
			LOGGER.log(Level.ERROR, "Can't create SquareMatrix -> incorrect number of elements in elementGroup");
			return new SquareMatrix();
		}
		//----------------------------------------------------------------
		SquareMatrix squareMatrix = new SquareMatrix(valueGroup.length);
		//----------------------------------------------------------------
		LOGGER.log(Level.DEBUG, "Created square matrix with size: " + valueGroup.length);
		//----------------------------------------------------------------
		for (int row = 0; row < valueGroup.length; row++) {
			valueGroup = elementGroup.get(row).split("-");
			//----------------------------------------------------------------
			for (int column = 0; column < valueGroup.length; column++) {
				squareMatrix.setElement(row, column, Integer.parseInt(valueGroup[column]));
			}
			//----------------------------------------------------------------
		}
		//----------------------------------------------------------------
		LOGGER.log(Level.DEBUG, "Return created matrix:\n" + squareMatrix.toString());
		return squareMatrix;
	}
}
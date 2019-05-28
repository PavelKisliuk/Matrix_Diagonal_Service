package coop.pavelkisliuk.matrixdiagonalservice.creator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class UniqueNumberCreator {
	private static final Logger LOGGER = LogManager.getLogger();

	public List<Integer> create(String elementGroup) {
		if (elementGroup == null) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> " + null);
			return new ArrayList<>();
		}
		if (elementGroup.isEmpty() ||
				elementGroup.isBlank()) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> empty");
			return new ArrayList<>();
		}
		//----------------------------------------------------------------
		ArrayList<Integer> list = new ArrayList<>();
		for (String uniqueNumber : elementGroup.split("-")) {
			list.add(Integer.parseInt(uniqueNumber));
		}
		return list;
	}
}

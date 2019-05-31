package coop.pavelkisliuk.matrixdiagonalservice.creator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class UniqueNumberCreator {
	private static final Logger LOGGER = LogManager.getLogger();

	public Set<Integer> create(String elementGroup) {
		if (elementGroup == null) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> " + null);
			return new HashSet<>();
		}
		if (elementGroup.isEmpty() ||
				elementGroup.isBlank()) {
			LOGGER.log(Level.ERROR, "Creator obtained elementGroup -> empty");
			return new HashSet<>();
		}

		Set<Integer> list = new HashSet<>();
		for (String uniqueNumber : elementGroup.split("-")) {
			list.add(Integer.parseInt(uniqueNumber));
		}
		return list;
	}
}
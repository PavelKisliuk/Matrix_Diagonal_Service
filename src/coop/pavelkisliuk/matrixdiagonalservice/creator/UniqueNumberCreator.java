/* By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.creator;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

/**
 * The {@code UniqueNumberCreator} class create {@code Set} of unique {@code Integer}.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class UniqueNumberCreator {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Return {@code Set} of unique {@code Integer} created from {@code String}.
	 * <p>
	 *
	 * @param elementGroup is data for {@code Set} creation.
	 * @return {@code Set} of unique {@code Integer} created from {@code String}.
	 */
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
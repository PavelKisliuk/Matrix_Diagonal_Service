/* By Pavel Kisliuk, 27.05.2019
 *  This is class for education and nothing rights don't reserved.
 */

package coop.pavelkisliuk.matrixdiagonalservice.reader;

import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The {@code UniqueNumberFileReader} class read special data for creation {@code Integer} unique values.
 * <p>
 *
 * @author Kisliuk Pavel Sergeevich
 * @since 12.0
 */
public class UniqueNumberFileReader {
	private static final Logger LOGGER = LogManager.getLogger();

	/**
	 * Read data from file and return it as {@code String}.
	 *
	 * @param path to file.
	 * @return {@code String} of red data.
	 * @throws CustomException if wrong {@param path} or {@param path} is {@code null}.
	 */
	public String read(String path) throws CustomException {
		if (path == null ||
				path.isBlank() ||
					Files.notExists(Paths.get(path))) {
			throw new CustomException();
		}

		String data;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
			LOGGER.log(Level.TRACE, "Start to read data from file.");
			data = reader.readLine();
			LOGGER.log(Level.DEBUG, "Data red from file.");
		} catch (FileNotFoundException e) {
			LOGGER.log(Level.ERROR, "File didn't find", e);
			return "";
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, "Can't read file", e);
			return "";
		}

		return data;
	}
}
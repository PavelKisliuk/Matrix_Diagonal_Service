import coop.pavelkisliuk.matrixdiagonalservice.creator.UniqueNumberCreator;
import coop.pavelkisliuk.matrixdiagonalservice.exception.CustomException;
import coop.pavelkisliuk.matrixdiagonalservice.logic.DiagonalPropagator;
import coop.pavelkisliuk.matrixdiagonalservice.model.SquareMatrixSingleton;
import coop.pavelkisliuk.matrixdiagonalservice.reader.UniqueNumberFileReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
	private static final Logger LOGGER = LogManager.getLogger();

	public static void main(String[] args) throws InterruptedException {
		Set<Integer> uniqueNumberGroup = createUniqueNumberGroup();

		ExecutorService executorService = Executors.newCachedThreadPool();
		SquareMatrixSingleton matrixSingleton = SquareMatrixSingleton.INSTANCE;

		for (Integer number : uniqueNumberGroup) {
			executorService.execute(new DiagonalPropagator(number, matrixSingleton));
		}

		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.SECONDS);
		LOGGER.log(Level.DEBUG, matrixSingleton.toStringDiagonal());
	}

	private static Set<Integer> createUniqueNumberGroup() {
		UniqueNumberFileReader reader = new UniqueNumberFileReader();
		UniqueNumberCreator creator = new UniqueNumberCreator();

		Set<Integer> unique;
		try {
			unique = creator.create(reader.read("testfile/Unique_Numbers.txt"));
		} catch (CustomException e) {
			unique = new HashSet<>();
			for (int i = 0; i < 6; i++) {
				unique.add(i);
			}
		}
		return unique;
	}
}
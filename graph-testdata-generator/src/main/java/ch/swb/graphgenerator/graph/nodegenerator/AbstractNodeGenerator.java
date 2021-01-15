package ch.swb.graphgenerator.graph.nodegenerator;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import com.github.javafaker.Faker;

public abstract class AbstractNodeGenerator<T> {
	protected EasyRandom easyRandom;
	protected Faker faker;

	public AbstractNodeGenerator() {
		EasyRandomParameters parameters = new EasyRandomParameters()
				.seed(System.currentTimeMillis())
				.objectPoolSize(100)
				.randomizationDepth(3)
				.dateRange(LocalDate.of(1960, 1, 1), LocalDate.of(1990, 12, 31))
				.stringLengthRange(8, 50)
				.collectionSizeRange(1, 10)
				.scanClasspathForConcreteTypes(true)
				.overrideDefaultInitialization(false)
				.ignoreRandomizationErrors(true);

		this.easyRandom = new EasyRandom(parameters);
		this.faker = new Faker(Locale.GERMANY);
	}

	public abstract T generateNode();

	public abstract List<T> generateNodes(int number);
}

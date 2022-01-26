package executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ThreadLocalRandom;

public class Calculation implements Callable<Double> {

	private final int id;
	private static int counter = 1;

	public Calculation() {
		id = counter++;
	}

	// es sind alle Exceptions erlaubt, aber wir dürfen die throws-Klausel auch
	// weglassen
	@Override
	public Double call() /* throws Exception */ {
		double result = 0;
		System.out.printf("Thread '%s' (Id %d): Starting calculation %d\n",
				Thread.currentThread().getName(), Thread.currentThread().getId(), id);
		// System.out.println("calculating next value ...");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 100000000; i++) {
			if (i % 2 == 0)
				result += random.nextDouble(50);
			else
				result -= random.nextDouble(50);
		}
		System.out.printf("Thread '%s' (Id %d): Calculation %d completed, result = %.2f \n",
				Thread.currentThread().getName(), Thread.currentThread().getId(), id, result);
		return result;
	}

}

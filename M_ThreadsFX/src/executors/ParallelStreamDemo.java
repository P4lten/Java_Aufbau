package executors;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();

		List<Calculation> calculations = new ArrayList<>();
		
		for (int i = 1; i <= 20; i++) {
			// eine Berechnung erzeugen
			Calculation calc = new Calculation();
			calculations.add(calc);
		}

		// mit der Stream-API die calc Methode parallel ausführen und summieren
		double sum = calculations.parallelStream().mapToDouble(Calculation::call).sum();

		System.out.println("Result: " + sum);

		Instant end = Instant.now();
		long ms = Duration.between(start, end).toMillis();
		System.out.printf("Time elapsed: %d ms\n", ms);

	}

}

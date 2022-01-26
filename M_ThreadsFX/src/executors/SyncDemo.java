package executors;

import java.time.Duration;
import java.time.Instant;

public class SyncDemo {

	public static void main(String[] args) {
		Instant start = Instant.now();
		
		double sum = 0;
		// synchrone Ausführung: 1 Berechnung nach der anderen
		for (int i = 1; i <= 20; i++) {
			// eine Berechnung erzeugen
			Calculation calc = new Calculation();
			// und ausführen 
			double value = calc.call();
			sum += value;
			
		}
		
		System.out.println("Result: " + sum);
		Instant end = Instant.now();
		long ms = Duration.between(start, end).toMillis();
		System.out.printf("Time elapsed: %d ms\n", ms);
		

	}

}

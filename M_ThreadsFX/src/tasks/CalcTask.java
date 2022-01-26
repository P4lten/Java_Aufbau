package tasks;

import java.util.concurrent.ThreadLocalRandom;

import javafx.concurrent.Task;

// Task ist eine Basisklasse, die die Benachrichtigung über Fortschritt und Status 
// sowie eine Möglichkeit zum Abbrechen des Tasks enthält
public class CalcTask extends Task<Double> {

	private int count;

	public CalcTask(int count) {
		this.count = count;
	}

	// synchrone Variante, ohne fortschritt oder Cancel
	public double runCalculation(int count) {
		double sum = 0;
		for (int i = 1; i <= count; i++) {
			double value = calcValue();
			sum += value;

		}

		return sum;
	}

	@Override
	protected Double call() throws Exception {
		System.out.printf("Calctask.call in Thread %s\n", Thread.currentThread().getName());
		double sum = 0;
		for (int i = 1; i <= count; i++) {
			// in jedem Durchlauf prüfen, ob der Task abgebrochen wurde
			if(isCancelled()) {
				updateMessage("Task wurde bei Schritt %d abgebrochen".formatted(i));
				// aus der Schleife raus -> Task beenden
				break;
			}
			double value = calcValue();
			sum += value;

			// den Fortschritt aktualisieren (es sind i von count Schritten erledigt
			updateProgress(i, count);
			// Status-Message anpassen
			updateMessage("Schritt %d von %d, Sume bisher: %.2f".formatted(i, count, sum));
		}

		return sum;
	}

	private double calcValue() {
		double result = 0;
		// System.out.println("calculating next value ...");
		ThreadLocalRandom random = ThreadLocalRandom.current();
		for (int i = 0; i < 1000000; i++) {
			if (i % 2 == 0)
				result += random.nextDouble(50);
			else
				result -= random.nextDouble(50);
		}
		return result;
	}

}

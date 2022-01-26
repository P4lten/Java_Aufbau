package competition;

import java.util.Random;

public class Runner implements Runnable {

	private static int nextPlace = 1;
	// für die Plazierung
	private int place;

	private static final Object syncObject = new Object();

	public String name;

	public Runner(String name) {
		this.name = name;
		System.out.printf("Runner-Konstruktor für %s int Thread %s\n", name, Thread.currentThread().getName());

	}

	public int getPlace() {
		return place;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run() {
		System.out.printf("run-Methode für %s int Thread %s\n", name, Thread.currentThread().getName());
		Random rnd = new Random();

		for (int i = 1; i <= 5; i++) {
			System.out.printf("%s beginnt mit Runde %d\n", name, i);
			try {
				Thread.sleep(rnd.nextInt(500));
			} catch (InterruptedException e) {
				// Die InterruptedException kann nur auftreten, wenn irgendjemand
				// den Thread mit interrupt() beendet -> hier einfach nur anzeigen
				// -> da der Wettkampf nicht vorzeitig beendet wird, hier einfach nur anzeigen
				e.printStackTrace();
			}
		}
		synchronized (syncObject) {

			System.out.printf("%s ist fertig!\n", name);
			// die Plazierung abholen
			finish();
		}
	}

	// synchronized method geht nicht weil wir pro Instanz einen Thread haben
	private void finish() {
		// einen kritischen Abschnitt für den Zugriff auf das statische Feld verwenden
		// nur 1 Thread darf gleichzeitig diesen Code-Abschnitt ausführen
		// andere müssen ggf warten bis der vorherige Thread fertig ist
		synchronized (syncObject) {

			place = nextPlace;
			System.out.printf("%s erreicht Platz %d\n", name, place);

			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				System.out.println("InterruptedException");
			}

			nextPlace++;
		}
	}

}

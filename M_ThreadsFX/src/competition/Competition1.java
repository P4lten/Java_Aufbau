package competition;

import java.util.Arrays;
import java.util.Comparator;

public class Competition1 {

	public static void main(String[] args) {

		// die Läufer im wettkampf 
		Runner[] runnerList = {
			new Runner("Quicksilver"),	new Runner("Flash"), new Runner("Sonic"),	
		};

//		// synchrone Ausführung
//		for (Runner runner : runnerList) {
//			runner.run();
//		}
//	
	
		
		//asynchrone Ausführung in mehreren Threads
		// für jeden Läufer einen Thread erzeugen und starten
		Thread [] runnerThreads = new Thread[runnerList.length];
		for (int i = 0; i < runnerThreads.length; i++) {
			Runnable task = runnerList[i];
			// Thread-Objekt mit dem Runner als Runnable erzeugen
			runnerThreads[i] = new Thread(task);
			// wenn der Thread gestartet wird, führt er die run-Methode des Runner-Objekts aus
//			runnerThreads[i] = new Thread(runnerList[i]);
			runnerThreads[i].start();
		}
		
		
		System.out.println("Alle Läufer sind gestartet...");
		
		// ein bisscen warten
		try {
//			Thread.sleep(3000);
			
			for (Thread thread : runnerThreads) {
				// auf das Ende des Threads warten
				thread.join();
			}
			
		} catch (InterruptedException e) {
			System.out.println("InterruptedException in main");
		}
		
		System.out.println("Ergebniss:");
		// sortieren nach der Plazierung
		Arrays.sort(runnerList, Comparator.comparing(Runner::getPlace));
		
		
		// anzeigen welche Platzierung die Läufer erreicht haben
		for (Runner runner : runnerList) {
			System.out.printf("%s: %d. Platz\n", runner.getName(), runner.getPlace());
		}
		
		
		System.out.println("Main ist zu ende!");
		
	}
	
}

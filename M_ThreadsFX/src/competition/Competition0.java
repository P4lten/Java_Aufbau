package competition;

import java.util.Arrays;
import java.util.Comparator;

public class Competition0 {

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
			// die daemon-Threads ausführen
			runnerThreads[i].setDaemon(true);
			// wenn der Thread gestartet wird, führt er die run-Methode des Runner-Objekts aus
			runnerThreads[i].start();
		}
		
		
		System.out.println("Alle Läufer sind gestartet...");
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println("InterruptedException");
		}
		
		System.out.println("Main ist zu ende!");
		
	}
	
}

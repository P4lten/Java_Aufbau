package competition;

import java.util.Comparator;
import java.util.stream.Stream;

public class Competition2 {

	public static void main(String[] args) {

		// die Läufer im wettkampf 
		Runner[] runnerList = {
			new Runner("Quicksilver"),	new Runner("Flash"), new Runner("Sonic"),	
		};

		
		// Thread-Objekte erzeugen
		Thread[] runnerThreads = Stream.of(runnerList)
				// statt Lambda Expression
				// .map(r -> new Thread(r))
				// Construktor reference zum Thread[]-Konstruktor
				.map(Thread::new)
				// statt Lambda Expression
				// .toArray(size -> new Thread[size]);
				// Constructor reference zum Thread[]-Konstruktor
				.toArray(Thread[]::new);
		
		// und alle starteb
		Stream.of(runnerThreads).forEach(t -> t.start());
		
		// warten bis alle im Ziel sind
		Stream.of(runnerThreads).forEach(t ->{
			try {
			t.join();
			}catch (InterruptedException e) {
				System.out.println("Interruptet Exception");
			}
		
		});
		
		// Ergebnis anzeigen
		System.out.println("Ergebniss");
		Stream.of(runnerList)
		//.sorted(Comparator.comparing(r-> getPlace()))
		.sorted(Comparator.comparing(Runner::getPlace))
		.forEach(r -> System.out.printf("%s: %d. Platz\n", r.getName(), r.getPlace()));

		
	}

}

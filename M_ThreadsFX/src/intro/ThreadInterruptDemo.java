package intro;

public class ThreadInterruptDemo {

	public static void main(String[] args) {
		
		
		// endlose Threads:
		MyEndlessThread t1 = new MyEndlessThread("Endless 1"), t2 = new MyEndlessThread("Endless 2");
		// einen Thread zu einem Hintergrundthread machen 
		t1.setDaemon(true);
		// start führt die run-Methode von myEndlessThread aus
		
		t1.start();
		t2.start();
		
		try {
			System.out.println("Threads gestartet, Enter zum Beenden drücken");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// jetzt den beiden anderen die interrupt-Nachricht senden,
		// wenn sie nicht daemon Threads sind
		if(!t1.isDaemon())
			t1.interrupt();
		if(!t2.isDaemon())
			t2.interrupt();
		

		
		
		System.out.println("Letzte Anweisung aus main");
	}

}

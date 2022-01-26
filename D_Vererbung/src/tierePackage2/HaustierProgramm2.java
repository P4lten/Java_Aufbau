package tierePackage2;



public class HaustierProgramm2 {

	public static void main(String[] args) {
//		Haustier[] meineTiere = new Haustier[4];
//		
//		meineTiere[0] = new Hund("Rex", 25); //Umwandlung Hund => Haustier
//		meineTiere[1] = new Katze("Miez","Wollknaeul");//Umwandlung Katze => Haustier
//		meineTiere[2] = new Dackel("Wuffi", 12);//Umwandlung Dackel => Haustier
//		meineTiere[3] = new Haustier("Tierlein");

		// Besser mit Initialisierungsliste
		Haustier[] meineTiere = new Haustier[] {

				new Hund("Rex", 25), // Umwandlung Hund => Haustier
				new Katze("Miez", "Wollknaeul"), // Umwandlung Katze => Haustier
				new Dackel("Wuffi", 12), // Umwandlung Dackel => Haustier
				// Abstrakte Klasse kann nicht instanziert werden
//				new Haustier("Tierlein") 
				};

		
		for (Haustier einTier : meineTiere) {
			testeTier(einTier);
			
		}
	}

	static void testeTier(Haustier einTier) {
		System.out.printf("Test für das Tier %s\n", einTier.getKosename());
		//Klassenname anzeigen
		System.out.printf("Das Tier ist vom Typ %s\n" , einTier.getClass().getSimpleName());
		// zeige Dich
		System.out.print("ZeigeDich: ");
		einTier.zeigeDich();
		System.out.print("Beweg dich: ");
		einTier.bewegDich();
		System.out.print("Gib laut von dir: ");
		einTier.gibEinenLautVonDir();
		
		//toString:
		// expliezierter Aufruf
		String info = einTier.toString();
		System.out.println("toString1: " + info);
		
		// implizierter Aufruf bei Stringverkettung
		System.out.println("toString2: " + einTier/*.toString()*/);
		
		// implizerter Aufruf bei printf
		System.out.printf("toString3: %s\n", einTier);
		System.out.println();
		
	}

}

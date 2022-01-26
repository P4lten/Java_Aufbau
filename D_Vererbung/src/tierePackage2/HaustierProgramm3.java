package tierePackage2;

public class HaustierProgramm3 {

	public static void main(String[] args) {


		// Besser mit Initialisierungsliste
		Haustier[] meineTiere = new Haustier[] {

				new Hund("Rex", 25), // Umwandlung Hund => Haustier
				new Katze("Miez", "Wollknaeul"), // Umwandlung Katze => Haustier
				new Dackel("Wuffi", 12), // Umwandlung Dackel => Haustier
				
				};

		
		for (Haustier einTier : meineTiere) {
			testeInstanceOf(einTier);
			
		}
	}

	static void testeInstanceOf(Haustier einTier) {
		System.out.printf("Test für das Tier %s\n", einTier.getKosename());
		
		// wenn das Tier mit Hund Kompatibel ist (dh. Hund oder Dackel)
		if (einTier instanceof Hund) {
			System.out.println("Es ist eine Art Hund");
			Hund einHund = (Hund)einTier;
			//Hund-spezifische Methoden
			einHund.belle();
			System.out.printf("Der Hund hat %d kg\n", einHund.getGewicht());
		} else if(einTier instanceof Katze) {
			System.out.println("Das Tier ist eine Art von Katze");
			// explizite Umwandlung und Methodenaufruf in einem
			((Katze)einTier).miaue();
		}
		
		if (einTier.getClass() == Hund.class) {
			System.out.println("Objekt der Klasse Hund");
		} else if(einTier.getClass() == Katze.class) {
			System.out.println("Objekt der Klasse Katze");
		} else if(einTier.getClass() == Dackel.class) {
			System.out.println("Objekt der Klasse Dackel");
		}
		
		System.out.println();	
	}
}

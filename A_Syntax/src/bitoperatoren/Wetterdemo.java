package bitoperatoren;

import java.time.LocalDate;

public class Wetterdemo {

	public static void main(String[] args) {
		// Statt fixe Werte die Konstanten verwenden
		// Wetterbits wetter = new Wetterbits("Wien", LocalDate.now(), (byte) (2 | 1 | 4));
		Wetterbits wetter = new Wetterbits("Wien", LocalDate.now(), (byte) (Wetterbits.WOLKEN| Wetterbits.SONNE |Wetterbits.REGEN));

		System.out.println(wetter.toString());
		
		// testen ob sonne vorkommt
		if (wetter.enthaeltBits(Wetterbits.SONNE)) {
			System.out.println("Es ist sonnig.");
		} else {
			System.out.println("Keine Sonne an diesem Tag.");
		}
		
		//Wind und Regen hinzufügen (Regen ist schon drin, hat keine Auswirkung mehr
		wetter.bitsHinzu((byte) (Wetterbits.REGEN|Wetterbits.NEBEL));
		System.out.println("Wetter neu: " + wetter.toString());
		
		// Nebel und Regen entfernen (Nebel ist nicht drin, hat keine Auswirkungen mehr
		wetter.bitsEntfernen((byte) (Wetterbits.REGEN | Wetterbits.NEBEL));
		
		System.out.println("Nach entfernen: " + wetter /*toString()*/);
		
	}

}

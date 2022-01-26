package kontrollstrukuren;

import java.util.Scanner;

public class SwitchDemo {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Welche Spielkarte?");
		String spielkarte = scan.nextLine();
		String kartenFarbe = getKartenFarbe_1(spielkarte);
		System.out.printf("Die Farbe der Spielkarte %s ist %s\n", spielkarte, kartenFarbe);
		
		//2. Methode auch testen
		kartenFarbe = getKartenFarbe_2(spielkarte);
		System.out.printf("Die Farbe der Spielkarte %s ist %s\n", spielkarte, kartenFarbe);

		scan.close();
	}

	static String getKartenFarbe_1(String karte) {

		String farbe;
		switch (karte) {
		case "Karo":
		case "Herz":
			farbe = "Rot";
			break;
		case "Pik":
		case "Treff":
			farbe = "Schwarz";
			break;
		default:
			// Ungültig -> Fehler auslösen, damit die Ausführung bei einem
			// catch-Block weiter
			throw new IllegalArgumentException("Ungültige Karte: " + karte);
		// ab hier "Unreachable"
		// break;
		}
		// die Farbe zurückliefern
		return farbe;
	}

	static String getKartenFarbe_2(String karte) {
		String farbe;

		switch (karte) {
		case "Karo", "Herz" -> farbe = "ROT";
				// am Ende der case-Anweisungen ist kein break nötig
		case "Pik", "Treff" -> farbe = "SCHWARZ";
				// schwarze Karten
		default -> throw new IllegalArgumentException("Unexpected value: " + karte);
		}
		return farbe;
	}
}

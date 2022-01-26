package anderes;

import java.util.Scanner;

public class DatumBerechnen {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		// Jahr einlesen und ermitteln ob es ein Schaltjahr ist
		System.out.print("Gib bitte das Jahr ein:");
		int jahr = input.nextInt();
		boolean schaltjahr = istSchaltjahr(jahr);
		// Tagesnummer einlesen
		System.out.printf("Gib bitte die Tagesnummer ein (1-%d):", schaltjahr ? 366 : 365);
		int tagesnummer = input.nextInt();

		// Initialisieren: Monat auf Jänner setzen und Anzahl Tage des Monats berechnen
		int monat = 1;
		int monatsTage = getMonatstage(1, jahr);
		// Variable für die Berechnung des Tages
		int tag = tagesnummer;
		// solange der Tag größer als die Tage im aktuellen Monat ist
		while (tag > monatsTage) {
			// die tage des aktuellen Monats abziehen
			tag -= monatsTage;
			// Monat inkrementieren
			monat++;
			// Tage des Monats neu berechnen
			monatsTage = getMonatstage(monat, jahr);
			// System.out.println("monatstage: " + monatsTage);
		}

		// Ergebnis anzeigen
		System.out.printf("Der %d. Tag ist der %02d.%02d.%d\n", tagesnummer, tag, monat, jahr);

		input.close();
	}

	static int getMonatstage(int monat, int jahr) {
		int monatstage;
		switch (monat) {
		// Monat mit 31 Tagen
		case 1, 3, 5, 7, 8, 10, 12 -> monatstage = 31;
		// Monate mit 30 Tagen
		case 4, 6, 9, 11 -> monatstage = 30;
		// Februar (ohne Schaltjahr)
		case 2 -> monatstage = istSchaltjahr(jahr) ? 29 : 28;

		default -> throw new IllegalArgumentException("Unexpected value: " + monat);
		}
		return monatstage;
	}

	static boolean istSchaltjahr(int jahr) {
		return jahr % 4 == 0 && jahr % 100 != 0 || jahr % 400 == 0;

	}
}

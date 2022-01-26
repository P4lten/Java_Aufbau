package eindimensional;

import java.util.Arrays;
import java.util.Random;

public class Niederschlagsmengen {
	public static void main (String [] args) {
		// Array für die Niederschlagswerte pro Monat
		
		double [] werte = new double[12];
		Random random = new Random();
		
		//an jedem Index einen Wert setzen
		for (int i = 0; i < werte.length; i++) {
			// zufallswert holen
			double wert = random.nextDouble() * 100;
			// und im Array eintragen
			werte[i] = wert;
		}
		
		// alle Werte anzeigen mit foreach
		for(double wert : werte) {
			System.out.printf("%.1f ", wert);
		}
		
		System.out.println();
		
		// Monat mit dem wenigsten Niederschlag suchen
		
		int iMin = 0;
		
		for (int i = 0; i < werte.length; i++) {
			// wenn der aktuelle Wert kleiner ist -> neues Minumum merken
			if (werte[i] < werte[iMin]) {
				iMin = i;
				// wenn 0 mm gefallen sind aufhören (kleiner kann es nicht mehr werden)
				if(werte[0] == 0) {
					break;
				}
			}
		}
		System.out.printf("Das Minumum war im Monat %d mit %.1f mm\n", iMin+1, werte[iMin]);
		
		String [] monatsnamen = {"Jaenner", "Februar", "Maerz", "Mai", "Juni", "July", "August", "September", "Oktober", "November", "Dezember"};
		
		System.out.printf("Das Minumum war im Monat %s mit %.1f mm\n", monatsnamen[iMin], werte[iMin]);
		
		//hilfsklasse für Arrays
		// das Array sortieren
		
		Arrays.sort(werte);
		
		System.out.println(Arrays.toString(werte));

	}
}

package mehrdimensionale;

import java.util.Random;

public class Asymmetrisch {

	public static void main(String[] args) {
		// ein Array mit 3 int-Arrays erzeugen
		int[][] zahlen = new int[3][];

		Random rnd = new Random();

		// die Sub-Arrays erzeugen
		for (int i = 0; i < zahlen.length; i++) {
			zahlen[i] = new int[rnd.nextInt(5) + 2];
		}

		// Schreibzugriff auf die Array-Elemente:
		for (int zeile = 0; zeile < zahlen.length; zeile++) {

			// int [] zeilenWerte = zahlen[zeile];
			for (int spalte = 0; spalte < zahlen[zeile].length; spalte++) {
				int zahl = rnd.nextInt(1000) + (zeile + 1) * 1000;
				// den Wert im Array eintragen
				zahlen[zeile][spalte] = zahl;
			}
		}

		// Iteration mit foreach
		for (int[] zeilenWerte : zahlen) {
			for (int wert : zeilenWerte) {
				System.out.printf("%d ", wert);
			}
			// nach jeder Zeile: ein newline
			System.out.println();
		}
	}

}

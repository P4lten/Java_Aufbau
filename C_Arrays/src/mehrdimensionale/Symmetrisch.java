package mehrdimensionale;

import java.util.Random;

public class Symmetrisch {

	public static void main(String[] args) {
		int[][] zahlen = new int[3][4];

		Random rnd = new Random();
		// Schreibzugriff auf die Array-Elemente:
		for (int zeile = 0; zeile < zahlen.length; zeile++) {
			
			//int [] zeilenWerte = zahlen[zeile];
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

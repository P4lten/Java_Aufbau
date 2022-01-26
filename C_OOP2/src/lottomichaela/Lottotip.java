package lottomichaela;

import java.util.Arrays;
import java.util.Random;

public class Lottotip {
	// Attribut(e)
	private int[] tippZahlen = new int[6];
	// statt zahl1, zahl2,...
	// private int tippZahl1, tippZahl2, ...;

	// das Array zurückliefern (getter für das Array)
	
	
	public int[] getTippZahlen() {
		// REferenz auf unser Array zurückliefern
		// return tippZahlen;
		// eine Kopie des Arrays zurückliefern
		return Arrays.copyOf(tippZahlen, tippZahlen.length);
	}
	
	// Methoden
	public void quicktipp() {
		// Zufallszahlen ermitteln, bis im Array 6 unterschiedliche Zahlen stehen

		// Variable für den Tipp-Index
		int index = 0;
		Random random = new Random();
		// solange nicht alle Elemente belegt sind
		while (index < tippZahlen.length) {
			// Zufallszahl für dieses Element holen
			int zahl = random.nextInt(45) + 1;
			// schaun ob die Zahl schon vorkommt
			boolean kommtVor = zahlImTipp(zahl, index);
			// wenn sie nicht vorkommt
			if (!kommtVor) {
				// Zahl eintragen und Index erhöhen
				tippZahlen[index++] = zahl;
			} else {
				// Infoausgabe, dass Zahl doppelt wäre
				System.out.println("Zahl wäre doppelt: " + zahl);
			}
		}
		// alles fertig => sortieren
		Arrays.sort(tippZahlen);
	}

	public void manuellerTipp() {
		throw new RuntimeException("Noch nicht implementiert");
		// TODO: Zahlen einlesen, bis im Array 6 unterschiedliche Zahlen stehen
	}

	public void ausgeben() {
		// alle Zahlen ausgeben
		System.out.print("Zahlen in diesem Tipp: ");
		for (int i = 0; i < tippZahlen.length; i++) {
			System.out.printf("%d ", tippZahlen[i]);
		}
		System.out.println();
	}

	public int[] gewinnPruefung(int[] gewinnZahlen) {
		// alle Zahlen, die im Array vorkommen, in einem Array zurückliefern
		// array für einen 6-er erzeugen
		int[] richtige = new int[tippZahlen.length];
		// Index für die richtigen
		int iRichtig = 0;
		// alle gewinnzahlen prüfen
		for (int i = 0; i < gewinnZahlen.length; i++) {
			// wenn die Zahl im Tipp vorkommt
			if (zahlImTipp(gewinnZahlen[i])) {
				// die Zahl in den richtigen eintragen
				richtige[iRichtig++] = gewinnZahlen[i];
			}
		}
		// jetzt ein passende großes Array erzeugen
		int[] erg = new int[iRichtig];
		// und alle richtigen hineinkopieren
		for (int i = 0; i < erg.length; i++) {
			erg[i] = richtige[i];
		}
		// ergebnis zurückliefern
		return erg;
	}

	// testen ob die zahl im Tipp vorkommt
	private boolean zahlImTipp(int zahl) {
		return zahlImTipp(zahl, tippZahlen.length - 1);
	}

	// testen ob die zahl im Tipp vorkommt, aber nur bis zum Index MaxIndex
	private boolean zahlImTipp(int zahl, int maxIndex) {
		for (int i = 0; i <= maxIndex; i++) {
			if (tippZahlen[i] == zahl) {
				return true;
			}
		}
		return false;
	}
}

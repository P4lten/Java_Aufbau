package oop1;

public class Bankverwaltung {

	public Bankkonto1[] konten = new Bankkonto1[10];
	private int anzahl = 0;

	public void anmelden(Bankkonto1 b) {
		if (anzahl == konten.length) {
			throw new IllegalStateException("Kein neues Konto mehr moeglich");
		}
		System.out.println("Name= " + b.getName() + ", Kontonummer= " + b.getKontonr());
		konten[anzahl++] = b;
	}

	public void alleAnzeigen() {

		if (anzahl > 1) {
			System.out.println("Es gibt " + anzahl + " Konten:");
		} else {
			System.out.println("Es gibt " + anzahl + " Konto:");
		}

		// nur soviele Personen anzeigen wie angemeldet wurden
		for (int i = 0; i < anzahl; i++) {
			System.out.println(konten[i].toString());
		}
	}

	public Bankkonto1 schließen(int nr) {
		int bkIndex = bankkontIndex(nr);

		if (bkIndex == -1) {
			throw new IllegalArgumentException("Konto mit Nummer " + nr + " nicht vorhanden!");
		}
		if (konten[bkIndex].getKontostand() < 5) {
			throw new IllegalArgumentException(
					"Konto mit Nummer " + nr + "kann die Schließungsgebühr von 5€ nicht bezahlen");
		} else {
			System.out
					.println("Der Restbetrag von Konto " + nr + " abzüglich 5 € Bearbeitungsgebühr wird ausbezahlt\n");
		}

		Bankkonto1 geloescht = konten[bkIndex];
		// die dahinter liegenden um einen Platz nach vorne verschieben
		for (int i = bkIndex + 1; i < anzahl; i++) {
			konten[i - 1] = konten[i];
		}
		konten[--anzahl] = null;
		// die Anzahl vermindern und das Element im Array initialisieren
		return geloescht;
	}

	private int bankkontIndex(int koNr) {
		int kontoIndex = -1;
		// im verwendeten Bereich suchen
		for (int i = 0; i < anzahl; i++) {
			if (konten[i].getKontonr() == koNr) {
				return i;
			}

		}
		// -1 bedeutet "nicht gefunden"
		return kontoIndex;
	}

	public void ueberweisung(int KoNrSender, int KoNrEmpfänger, double betrag) {
		System.out.println(konten[1]);
		if (KoNrSender > anzahl && KoNrEmpfänger > anzahl) {
			throw new IllegalArgumentException("Konto existiert nicht!");
		} else {
			for (int i = 0; i < anzahl; i++) {
				if (konten[i].getKontonr() == KoNrSender) {
					konten[i].abheben(betrag);
				} 
				if (konten != null && konten[i].getKontonr() == KoNrEmpfänger) {
					konten[i].einzahlen(betrag);
				}
			}
		}
	}

}

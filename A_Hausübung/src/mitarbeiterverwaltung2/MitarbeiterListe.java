package mitarbeiterverwaltung2;

import java.util.ArrayList;
import java.util.List;

public class MitarbeiterListe<T> implements MitarbeiterListeItf {
	private List<Mitarbeiter> gruppe = new ArrayList<>();

//Hinzufügen eines Mitarbeiters / Managers / Experten
	public void addMitarbeiter(Mitarbeiter m) {
		gruppe.add(m);
	}

//Gehaltserhöhung für alle Mitarbeiter durchführen
	public void erhöheAlleGehalt(float pct) {
		for (int i = 0; i < gruppe.size(); i++) {
			gruppe.get(i).calcErhoehung(pct);
		}

	}

//Gehaltserhöhung für 1 Mitarbeiter durchführen
	public void erhoehungG(int maNummer, float prozent) {
		if (maNummer > gruppe.size()) {
			throw new IllegalArgumentException("Es gibt nur " + gruppe.size() + " Mitarbeiternummern");
		} else {
			for (int i = 0; i < gruppe.size(); i++) {
				if (gruppe.get(i).getMitarbeiterId() == maNummer) {
					gruppe.get(i).calcErhoehung(prozent);
				} else {
					throw new IllegalArgumentException("Mitarbeiter mit der Nummer " + maNummer + " nicht gefunden");

				}
			}
		}
	}

//Ausgabe eines Mitarbeiters
	public void anzeigen(int maNummer) {
		if (maNummer > gruppe.size()) {
			throw new IllegalArgumentException("Es gibt nur " + gruppe.size() + " Mitarbeiternummern");
		} else {
			for (int i = 0; i < gruppe.size(); i++) {
				if (gruppe.get(i).getMitarbeiterId() == maNummer) {
					System.out.println(gruppe.get(i).mitarbeiterblatt());
				} else {
					throw new IllegalArgumentException("Mitarbeiter mit der Nummer " + maNummer + " nicht gefunden");

				}
			}

		}
	}

//Anzeige aller Mitarbeiter, sortiert nach Name
	public void sortiertName() {
		ByNameComperable comparator = new ByNameComperable();
		java.util.Collections.sort(gruppe, comparator);
		alleAnzeigen();

	}

//Anzeige aller Mitarbeiter, sortiert nach Typ/Eintrittsdatum
	public void sortiertNachTyp() {
		ByClassAndDate comparator = new ByClassAndDate();
		java.util.Collections.sort(gruppe, comparator);
		alleAnzeigen();
	}

//Austritt eines Mitarbeiters (Identifizierung über Mitarbeiter-Nummer)
	public Mitarbeiter abmelden(int maNummer) {
		// den Index der Person holen
		int pIndex = personenIndex(maNummer);
		// nicht gefunden -> Fehler
		if (pIndex < 0) {
			throw new IllegalArgumentException("Mitarbeiter mit Nummer " + maNummer + " existiert nicht");
		}
		System.out.println("Entfernter Mitarbeiter");
		System.out.println(gruppe.get(pIndex).mitarbeiterblatt());
		return gruppe.remove(pIndex);

	}

	private void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen: \n", gruppe.size());
		// mit foreach über alle Elemente iterieren
		for (Mitarbeiter mitarbeiter : gruppe) {
			System.out.printf("%s\n", mitarbeiter);
		}
	}

	private int personenIndex(int nr) {
		// alle Elemente über den Index iterieren
		for (int i = 0; i < gruppe.size(); i++) {
			// statt Index-Zugriff mit Operator [] (gruppe[i])
			// mit gruppe.get(i) die Person an diesem Index holen
			Mitarbeiter m = gruppe.get(i);
			// wenn die Nummer gleich der Nummer dieser Person ist
			if (m.getMitarbeiterId() == nr) {
				return i;
			}
		}
		// Nummer kam nicht vor => -1 zurückliefern
		return -1;
	}

}

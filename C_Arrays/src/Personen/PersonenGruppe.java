package Personen;

/**
 * Diese Klasse kapselt eine Gruppe von Personen-Onjekten in einem Array
 * @author Paltenberg
 *
 */
public class PersonenGruppe {
	// Array für eine Personen-Gruppe
	private Person[] gruppe = new Person[5];
	// die Anzahl der Personen in der Gruppe
	private int anzahl = 0;

	/**
	 * eine Person anmelden. Die Person wird im Array am nächsten feien Platz
	 * hinzugefügt
	 * @param p die Person, die angemeldet werden soll
	 */
	
	public void anmelden(Person p) {
		// wenn kein Platz mehr ist
		if (anzahl == gruppe.length) {
			throw new IllegalStateException("Die Gruppe ist schon voll!");
		}

		// die Person eintagen
//		gruppe[anzahl] = p;
//		anzahl++;

		gruppe[anzahl++] = p;
	}

	
	/**
	 * Eine Person anmelden. Es wird eine Persinobjekt erzeugt und im Array am
	 * nächsten freien Platz hinzugefügt
	 * 
	 * @param name der Name der Person
	 * @param alter das Alter der Person
	 */
	
	public void anmelden(String name, int alter) {
		// neues Objekt erzeugen

		// und anmelden
		anmelden(new Person(name, alter));
	}

	public void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen:\n", anzahl);
		// nur soviele Personen anzeigen wie angemeldet wurden
		for (int i = 0; i < anzahl; i++) {
			System.out.printf("\t %s\n", gruppe[i].toString());
		}
	}
	
	
	public void anzeigen(int nr) {
		int pIndex = personenIndex(nr);
		if(pIndex == -1) {
			System.out.printf("Person mit nummer %d nicht vorhanden\n" , nr);
		}else {
			System.out.printf("Person gefunden %s\n", gruppe [pIndex].toString());
		}
	}
	
	
	/**
	 * eine Person abmelden. Die Person wird aus dem Array entfernt. Dahinterliegende Objekte werden
	 * nach vorne verschoben
	 * @param nr die Nummer Person die abgemeldet wird
	 * @return das Person-Objekt
	 */

	public Person abmelden(int nr) {
		int pIndex = personenIndex(nr);
		// nicht gefunden => Exception werfen
		if (pIndex == -1) {
			throw new IllegalArgumentException("Person mit Nummer " + nr + " nicht vorhanden!");

		}
		
		Person geloescht = gruppe[pIndex];
		// die dahinter liegenden um einen Platz nach vorne verschieben
		for (int i = pIndex + 1; i < anzahl; i++) {
			gruppe[i -1] = gruppe [i]; 
		}
		
		gruppe[--anzahl] = null;
		//die Anzahl vermindern und das Element im Array initialisieren
		return geloescht;
	}

	
	
	
	private int personenIndex(int nr) {
		// -1 bedeutet "nicht gefunden"
		int pIndex = -1;
		// im verwendeten Bereich suchen
		for (int i = 0; i < anzahl; i++) {
			if (gruppe[i].getNr() == nr) {
				return i;
			}

		}
		// -1 bedeutet "nicht gefunden"
		return -1;
	}

}

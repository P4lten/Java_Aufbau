package listen.personen;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PersonenGruppeListe implements PersonenGruppeItf {

	// Liste für belibig viele Personen
	private List<Person> gruppe = new ArrayList<>();

	@Override
	public void anmelden(Person p) {
		// die Person am Ende einfügen
		gruppe.add(p);

	}

	@Override
	public void anmelden(String name, int alter) {
		anmelden(new Person(name, alter));

	}

	@Override
	public void alleAnzeigen() {
		System.out.printf("In der Gruppe sind %d Personen: \n", gruppe.size());
		//mit foreach über alle Elemente iterieren
		for (Person person : gruppe) {
			System.out.printf("\t%s\n", person);
		}
	}

	@Override
	public void anzeigen(int nr) {
		// den Index der gesuchten Person holen
				int pIndex = personenIndex(nr);
				if(pIndex == -1) {
//					throw new IllegalArgumentException("Person mit Nummer " + nr + "asdf");
					System.out.printf("Personen mit Nummer %d existiert nicht\n", nr);
				}else {
					Person p = gruppe.get(pIndex);
					System.out.printf("Person gefunden: %s\n", p);
				}
	}

	@Override
	public Person abmelden(int nr) {
	//variante 1: abmelden per Index
		// den Index der Person holen
		int pIndex = personenIndex(nr);
		// nicht gefunden -> Fehler
		if (pIndex < 0) {
			throw new IllegalArgumentException("Person mit Nummer "  + nr + " existiert nicht");
		}
		return gruppe.remove(pIndex);
		
	}
	
//	public Person abmelden(int nr) {
//		//Variante 2: abmelden mit Iterator
//		Iterator<Person> iterator = gruppe.iterator();
//		// Solange ein nächstes Element verfügbar ist
//		while (iterator.hasNext()) {
//			// das Element holen
//			Person p = iterator.next();
//			// Wenn es die gesuchte Person ist
//			if(p.getNr() == nr) {
//				//entfernen
//				iterator.remove();
//				return p;
//			}
//		}
//		// wenn wir hierher kommen haben wir die Person nicht gefunden
//		throw new IllegalArgumentException("Person mit Nummer "  + nr + " existiert nicht");
//	}
	
	
	public Person suchen(int nr) {
		// den Index zur Nummer suchen
		int pIndex = personenIndex(nr);
		// wenn vorhanden -> zurückliefern
		if(pIndex >= 0) {
			return gruppe.get(pIndex);
			// sonst: fehler auslösen
		}else {
			throw new IllegalArgumentException("Person mit Nummer " + nr + " existiert nicht"); 
		}
	}
	
	
	private int personenIndex(int nr) {
		// alle Elemente über den Index iterieren
		for (int i = 0; i < gruppe.size(); i++) {
			// statt Index-Zugriff mit Operator [] (gruppe[i])
			// mit gruppe.get(i) die Person an diesem Index holen
			Person p = gruppe.get(i);
			// wenn die Nummer gleich der Nummer dieser Person ist
			if (p.getNr() == nr) {
				return i;
			}
		}
		// Nummer kam nicht vor => -1 zurückliefern
		return -1;
	}
	
}

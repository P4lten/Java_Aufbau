package Personen;

public class PersonenMain2 {

	// die PersonenGruppe des Programms - ist static, dh es gibt nur eine Gruppe im gesamten Programm
	private static PersonenGruppe gruppe1 = new PersonenGruppe();
	public static void main(String[] args) {

	
		gruppe1.anmelden(new Person ("Max", 10));
		gruppe1.anmelden(new Person ("Moriz", 8));
		//Overload methode
		gruppe1.anmelden("Susi", 9);

		// alle Anzeigen
		gruppe1.alleAnzeigen();
		
		testeAnmeldung("Karli", 7);
		testeAnmeldung("Kurti", 57);
		testeAnmeldung("Karo", 9);
		
		gruppe1.alleAnzeigen();
		
		
		gruppe1.anzeigen(3);
		testeAbmeldung(3);
		
		gruppe1.anzeigen(3);
		gruppe1.alleAnzeigen();
		
		// Person löschen die nicht mehr vorhanden ist
		testeAbmeldung(3);
		
		
		
		
	}
	
	static void testeAbmeldung(int nr) {
		try {
			//die Methode ausführen, in der ein Fehler auftreten kann
			Person geloescht = gruppe1.abmelden(nr);
			System.out.printf("Person gelöscht %s\n", geloescht.toString());
		} catch (Exception e) {
			System.out.println("FEHLER beim Abmelden:" + e.toString());
		}
	}
	
	
	static void testeAnmeldung(String name, int alter) {
		try {
			//die Methode ausführen, in der ein Fehler auftreten kann
			gruppe1.anmelden(name, alter);
			System.out.printf("Person angemeldet: Name=%s\n", name);
		} catch (Exception e) {
			System.out.println("FEHLER bei anmeldung:" + e.toString());
		}
	}

}

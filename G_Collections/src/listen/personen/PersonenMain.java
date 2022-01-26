package listen.personen;

public class PersonenMain {

	// die PersonenGruppe des Programms - ist static, dh es gibt nur eine Gruppe im gesamten Programm
	// die ursprüngliche Implementierung mit dem Array
	// privat static PersonenItf gruppe1 = new PersonGruppeArray();
	// die neue Implementierung mit Liste
	private static PersonenGruppeItf gruppe1 = new PersonenGruppeListe();
	
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
		zeigeAlter(4);
		
		
		
	}
	
	static void testeAbmeldung(int nr) {
		try {
			System.out.printf("Melde Person mit Nr %d ab... \n", nr);
			//die Methode ausführen, in der ein Fehler auftreten kann
			Person geloescht = gruppe1.abmelden(nr);
			System.out.printf("...Person gelöscht %s\n", geloescht.toString());
		} catch (Exception e) {
			System.out.println("...FEHLER beim Abmelden:" + e.toString());
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
	
	static void zeigeAlter(int nr) {
		try {
			
			Person p = gruppe1.suchen(nr);
			System.out.printf("Person gefunden: %s\n", p.getAlter());
		} catch (Exception e) {
			System.out.println("FEHLER: Person nicht gefunden");
		}
	}

}

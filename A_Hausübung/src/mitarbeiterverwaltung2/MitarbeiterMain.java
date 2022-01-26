package mitarbeiterverwaltung2;

import java.time.LocalDate;

public class MitarbeiterMain {

	private static MitarbeiterListeItf gruppe1 = new MitarbeiterListe();

	public static void main(String[] args) {

		gruppe1.addMitarbeiter(new Experte("Peter", LocalDate.of(1994, 7, 18), LocalDate.of(2019, 01, 01), 3000,
				"Computerwissenschaften"));
		gruppe1.addMitarbeiter(new Manager("Alex", LocalDate.of(1990, 7, 01), LocalDate.of(2015, 02, 03), 4000, 1000));
		gruppe1.addMitarbeiter(new Experte("Johannes", LocalDate.of(1996, 1, 1), LocalDate.of(2021, 01, 01), 3000,
				"Computerwissenschaften"));
		gruppe1.addMitarbeiter(new Manager("Thomas", LocalDate.of(1992, 1, 06), LocalDate.of(2018, 1, 10), 3500, 1000));

		
		gruppe1.sortiertNachTyp();
		
		gruppe1.erhoehungG(1, 0.1f);
		
		gruppe1.anzeigen(1);
		
		gruppe1.erhöheAlleGehalt(0.5f);
		
		gruppe1.abmelden(1);
		
		gruppe1.sortiertName();
		
	}

}

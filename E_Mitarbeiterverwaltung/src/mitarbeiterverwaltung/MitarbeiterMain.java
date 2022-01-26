package mitarbeiterverwaltung;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;

public class MitarbeiterMain {

	public static void main(String[] args) {

		Mitarbeiter[] allMitarbeiter = new Mitarbeiter[] {
		new Experte("Peter", LocalDate.of(1994, 7, 18), LocalDate.of(2019, 01, 01), 3000, "Computerwissenschaften"),
		new Manager("Alex", LocalDate.of(1990, 7, 01), LocalDate.of(2015, 02, 03), 4000, 1000),
		new Experte("Johannes", LocalDate.of(1996, 1, 1), LocalDate.of(2021, 01, 01), 3000, "Computerwissenschaften"),
		new Manager("Thomas", LocalDate.of(1992, 1, 06), LocalDate.of(2018, 1, 10), 3500, 1000)
		};
		
		
		zeigeMitarbeiter("Alle Mitarbeiter unsortiert", allMitarbeiter);
		
		ByNameComperable comperator = new ByNameComperable();
		Arrays.sort(allMitarbeiter,comperator);
		zeigeMitarbeiter("Alle Mitarbeiter sortiert nach Name", allMitarbeiter);
		
		ByClassAndDate comperator2 = new ByClassAndDate();
		Arrays.sort(allMitarbeiter,comperator2);
		zeigeMitarbeiter("Alle Mitarbeiter sortiert nach Klasse und Eintrittsdatum", allMitarbeiter);
		
//		System.out.println(angestellter1.mitarbeiterblatt());
//		System.out.println(angestellter2.mitarbeiterblatt());
//		
//		
//		System.out.println("Anstellungsdauer = " + angestellter1.anstellungsDauer() + " Monate");
//		System.out.println("Jahresgehalt betraegt " + angestellter1.calcJahrGehalt() + "€");
//		System.out.println("Monatsgehalt betraegt " + angestellter1.calcMonatGehalt()+"€");
//		angestellter1.calcErhoehung(0.1f);
//		System.out.printf("Monatsgehalt betraegt %.2f€\n", angestellter1.calcMonatGehalt());
//		System.out.printf("Jahresgehalt betraegt %.2f€\n", angestellter1.calcJahrGehalt());
//
//		System.out.println();
//		
//		System.out.println("Anstellungsdauer = " + angestellter2.anstellungsDauer() + " Monate");
//		System.out.println("Jahresgehalt betraegt " + angestellter2.calcJahrGehalt() + "€");
//		System.out.printf("Bonus betraegt %.2f€ \n", angestellter2.getBonus());
//		System.out.println("Monatsgehalt betraegt " + angestellter2.calcMonatGehalt()+"€");
//		angestellter2.calcErhoehung(0.1f);
//		System.out.printf("Bonus betraegt %.2f€ \n", angestellter2.getBonus());
//		System.out.printf("Monatsgehalt betraegt %.2f€\n", angestellter2.calcMonatGehalt());
//		System.out.printf("Jahresgehalt betraegt %.2f€\n", angestellter2.calcJahrGehalt());

	}
	
	static void zeigeMitarbeiter(String info, Mitarbeiter[] allMitarbeiter) {
		System.out.println(info);
		// alle Tiere anzeigen
		for (Mitarbeiter miti : allMitarbeiter) {
			System.out.println(miti);
		}
		System.out.println();
	}

}

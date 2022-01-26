package animals.anonym;

import java.util.Arrays;
import java.util.Comparator;

/**
 * diese Klasse enthält ein Array von Animal-Objekten.
 * 
 * @author Michaela
 *
 */
public class AnimalList {

	private Animal[] allAnimals = new Animal[] {
			new Animal("Löwe", "Panthera leo", 250, false),
			new Animal("Waschbär", "Procyon", 12, false),
			new Animal("Bison", "Bison bison", 950, true),
			new Animal("Elefant", "Loxodonta", 4000, true),
			new Animal("Eisbär", "Ursus maritimus", 650, false),
			new Animal("Emu", "Dromaius novaehollandiae", 37, false)

	};

	// alle Tiere anzeigen
	public void showAll() {
		for (int i = 0; i < allAnimals.length; i++) {
			System.out.println(allAnimals[i]);
		}
		System.out.println();
	}

	// nur die Tiere anzeigen die den Filter entsprechen
	public void showAnimals(AnimalFilter myFilter) {
		for (int i = 0; i < allAnimals.length; i++) {
			//wenn das Filter-Objekt für dieses Tier ture liefert
			// wir führen hier einen Callback aus: die isTrueFor-Methode 
			// ein Filter-Objekt das vom Aufrufer übergeben wurde,
			// wird ausgeführt
			if (myFilter.isTrueFor(allAnimals[i])) {
				System.out.println(allAnimals[i]);
			}
		}
		System.out.println();
	}
	
	public void showAnimals(AnimalFilter myFilter, AnimalFilter myFilter2) {
		for (int i = 0; i < allAnimals.length; i++) {
			//wenn das Filter-Objekt für dieses Tier ture liefert
			// wir führen hier einen Callback aus: die isTrueFor-Methode 
			// ein Filter-Objekt das vom Aufrufer übergeben wurde,
			// wird ausgeführt
			if (myFilter.isTrueFor(allAnimals[i]) && myFilter2.isTrueFor(allAnimals[i])) {
				System.out.println(allAnimals[i]);
			}
		}
		System.out.println();
	}

	public void sortiere(Comparator<Animal> comparator) {
		System.out.println("Comparator Typ " + comparator.getClass().getName());
		Arrays.sort(allAnimals, comparator);
	}
	
}

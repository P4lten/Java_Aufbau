package animals.anonym;

import java.util.Comparator;

public class AnonymeKlasseDemo {

	public static void main(String[] args) {

		AnimalList animals = new AnimalList();
		animals.showAll();
		
		//Anonyme Interface Implementierung: Es wird eine 
		// Anonyme Klasse definiert und 1 Instanz von deiser Klasse erzeugt
		AnimalFilter filter1 = new AnimalFilter() {
			// Implementierung für die isTrueForMethode
			@Override
			public boolean isTrueFor(Animal a) {
				return a.isHerbivore();
			}
				// zusätzliche Funktionalität
				public String toString() {
					return "Filterung nach PFlanzenfresser";
				}
			
		}; // Strichpunkt schließt deklaration ab
		
		System.out.println("Pflanzenfresser: ");
		System.out.println("Filterobjekt: " + filter1);
		animals.showAnimals(filter1);
		
		
		// Anonyme Klasse ohne Hilfsvariable
		System.out.println("Fleischfresser: ");
		animals.showAnimals(new AnimalFilter() {
			
			
			@Override
			public boolean isTrueFor(Animal a) {
				// TODO Auto-generated method stub
				return !a.isHerbivore();
			}
			
			
			
		});
		
		// Tiere sortiert mit Comperator
		animals.sortiere(new Comparator<Animal>() {
			
			@Override
			public int compare(Animal o1, Animal o2) {
				// TODO Auto-generated method stub
				return o2.getWeight() - o1.getWeight();
			}
		});
		System.out.println("Tiere sortiert: ");
		animals.showAll();
		
		
		
		
		
		
		
		
		
	}

}

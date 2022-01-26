package animals.lambdaUndAnonym;

import java.util.Comparator;

public class LambdaExpressionsDemo {

	public static void main(String[] args) {

		AnimalList animals = new AnimalList();
		animals.showAll();
		
		//Anonyme Interface Implementierung: Es wird eine 
		// Anonyme Klasse definiert und 1 Instanz von deiser Klasse erzeugt
		
		
		AnimalFilter filter1 = (a) -> {
			// Implementierung für die isTrueForMethode
				return a.isHerbivore();
						
		}; // Strichpunkt schließt deklaration ab
		
		System.out.println("Pflanzenfresser: ");
		System.out.println("Filterobjekt: " + filter1.getClass().getName());
		animals.showAnimals(filter1);
		
		
		// Anonyme Klasse ohne Hilfsvariable
		System.out.println("Fleischfresser: ");
		animals.showAnimals(a -> !a.isHerbivore()); 		
			
			
	
		
//		// Tiere sortiert mit Comperator
//		animals.sortiere(new Comparator<Animal>() {
//			
//			@Override
//			public int compare(Animal o1, Animal o2) {
//				// TODO Auto-generated method stub
//				return o2.getWeight() - o1.getWeight();
//			}
//		});
		
		animals.sortiere((a1, a2) -> a2.getWeight() - a1.getWeight());
		System.out.println("Tiere sortiert: ");
		animals.showAll();
		
		animals.sortiere((a1,a2) -> {
			int ret =Boolean.compare(a1.isHerbivore(),a2.isHerbivore());
			if (ret == 0) {
				ret = Integer.compare(a1.getWeight(), a2.getWeight());
			}
			return ret;
		});
		System.out.println("Nach Pflanzenfresser und Gewicht sortiert: ");
		animals.showAll();
		
		
		
		
		
		
		
	}

}

package animals.funcInterfaces;

import java.util.Comparator;
import java.util.function.Predicate;

public class LambdaExpressionsDemo {

	public static void main(String[] args) {
		System.out.println("Lambda Expressions für Vordefinierte Functional Interfaces");
		AnimalList animals = new AnimalList();
		animals.showAll();

		// Predicate<Animal> statt AnimalFilter
		Predicate<Animal> filter1 = (a) -> {
			// Implementierung der Methode isTrueFor ohne Methoden-Gerüst
			return a.isHerbivore();
		};// Strichpunkt schließt Deklaration ab
		System.out.println("Pflanzenfresser:");
		System.out.println("Filterobjekt: " + filter1.getClass().getName());
		animals.showAnimals(filter1);

		// Fleischfresser mit not-Methode
		System.out.println("Fleischfresser:");

		// Lambda-Expression für Fleischfresser
		//animals.showAnimals(a -> !a.isHerbivore());
		// statt obiger Lambda-Expression: statische Methode aus dem Interface
		// (not bildet den Kehrwert des übergebenen Predicate)
//		Predicate<Animal> herbi = Animal::isHerbivore;
//		Predicate<Animal> carni = Predicate.not(herbi);
		// kürzer, ohne Hilfsvariablen
		animals.showAnimals(Predicate.not(Animal::isHerbivore));

		// Tiere sortieren mit Comparator
		// Lambda Expression
		// Statt Lamda-Expression, die eine Property der Klasse vergleicht
		//animals.sortiere((a1, a2) -> a2.getWeight() - a1.getWeight());
		// Methode comparing mit Angabe der Property "weight"
		// animals.sortiere(Comparator.comparing((Animal a) -> a.getWeight()).reversed());
		animals.sortiere(Comparator.comparing(Animal::getWeight).reversed());
		System.out.println("Tiere sortiert nach Gewicht absteigend:");
		animals.showAll();
		
		// nach Pflanzenfresser und Gewicht sortiert
		// statt Lambda Expression mit eigener Berechnung
//		animals.sortiere((a1, a2) -> {
//			int ret = Boolean.compare(a1.isHerbivore(), a2.isHerbivore());
//			// wenn gleich -> nach Gewicht aufsteigend 
//			if(ret== 0) {
//				ret = Integer.compare(a1.getWeight(), a2.getWeight());
//			}
//			return ret;
//		});
		animals.sortiere(Comparator.comparing(Animal::isHerbivore).thenComparing(Animal::getWeight));
		System.out.println("Nach Pflanzenfresser und Gewicht sortiert:");
		animals.showAll();

	}

}

package animals.streamAPI;

import java.util.Comparator;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public class StreamAPIOptionalDemo {

	private static Scanner input = new Scanner (System.in);
	public static void main(String[] args) {
		Animal[] allAnimals = new Animal[] {
				new Animal("Löwe", "Panthera leo", 250, false),
				new Animal("Waschbär", "Procyon", 12, false),
				new Animal("Bison", "Bison bison", 950, true),
				new Animal("Elefant", "Loxodonta", 4000, true),
				new Animal("Eisbär", "Ursus maritimus", 650, false),
				new Animal("Emu", "Dromaius novaehollandiae", 37, false)

		};
		
		System.out.println("Welcher Schwellwert für Gewicht?");
		int weight = input.nextInt();
		
		
		Optional<Animal> optAnimal = Stream.of(allAnimals)
				.filter(a ->a.getWeight() > weight)
				// sortierung
				.sorted(Comparator.comparing(Animal::getWeight))
				// davon das erste Element
				.findFirst();
		// liefert ein Optional-Wrapper-Objekt
		// liefert ein Optional-Wrapper-Objekt

		if(optAnimal.isPresent()) {
			// get liefert das Objekt
			Animal a = optAnimal.get();
			System.out.println("Leichtestes Tier: " + a);
		} else {
			System.out.println("Kein Tier vorhanden");
		}
		
		optAnimal = Stream.of(allAnimals)
				.filter(a ->a.getWeight() > weight)
				// terminal Operation min -> das kleinste Objekt gemäß diesem Komparator
				.min(Comparator.comparing(Animal::getWeight));
				
		optAnimal.ifPresentOrElse
				// "if-Zweig"
				(a -> System.out.println("Leichtestes Tier: " + a),
				// "else Zweig"
				() -> System.out.println("Kein Tier vorhanden"));
		
		// schwerstes Objekt, ohne Hilfsvariable
		Stream.of(allAnimals)
				.filter(a ->a.getWeight() < weight)
				// terminal Operation max -> das größte Objekt gemäß diesem Komparator
				.max(Comparator.comparing(Animal::getWeight))
				.ifPresentOrElse
				// "if-Zweig"
				(a -> System.out.println("Schwerste Tier: " + a),
				// "else Zweig"
				() -> System.out.println("Kein Tier vorhanden"));
	}

}

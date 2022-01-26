package animals.streamAPI;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamAPIDemo {

	public static void main(String[] args) {
		Animal[] allAnimals = new Animal[] {
				new Animal("Löwe", "Panthera leo", 250, false),
				new Animal("Waschbär", "Procyon", 12, false),
				new Animal("Bison", "Bison bison", 950, true),
				new Animal("Elefant", "Loxodonta", 4000, true),
				new Animal("Eisbär", "Ursus maritimus", 650, false),
				new Animal("Emu", "Dromaius novaehollandiae", 37, false)

		};
		
		// Stream für Array holen: Factory-Methode aus Stream
//		Stream<Animal> astr = Stream.of(allAnimals);
//		astr.filter(Predicate.not(Animal::isHerbivore));
//		astr.sorted(Comparator.comparing(Animal::getName));
//		astr.forEach(a -> System.out.println(a .getName()));
		
		// Stream für Array holen: Factory-Methode aus Stream
		Stream.of(allAnimals)
			// intermediate operations
			.filter(Predicate.not(Animal::isHerbivore))
			.sorted(Comparator.comparing(Animal::getName))
			// terminal Operation for each -> damit beginnt die Ausführung
			.forEach(a -> System.out.println(a .getName()));

		// alle schweren Tier in eine separate Liste
		// a) Listen-Objekt erzeugen
		List<Animal> someAnimals = new ArrayList<>();
		// b) Stream konfigurieren 
		Stream.of(allAnimals)
			.filter (a -> a.getWeight() > 200)
			.sorted()
			// terminal operation foreach: in die Liste einfügen
			//.forEach(a -> someAnimals.add(a))
			// c) ergebnis in die Liste einfügen
			.forEach(someAnimals::add);
		
		System.out.println("\nSchwere Tiere:");
		// Liste verarbeiten mit Stream
		someAnimals.stream().forEach(System.out::println);
		
		// alle leichten Tiere in separater Liste
		// a) ist nicht nötig, nur Deklaration 
		//List<Animal> someAnimals2 =
		var someAnimals2 =
				// b) Stream konfigurieren
				Stream.of(allAnimals).filter(a -> a.getWeight() <= 200)
				.sorted()
				// c) ergebnis vom Collector in eine Liste füllen lassen
				.collect(Collectors.toSet());
		
		// Liste iterieren mit forEach Methode
		System.out.println("\nLeichte Tiere :");
		someAnimals2.forEach(System.out::println);
		
		
		// Ergebnis in ein neues passend großes Array schreiben
		Animal[] someAnimals3 =
				Stream.of(allAnimals).filter(a -> a.getName().contains("s"))
				.sorted()
				// toArray(IntFunction) -> das Array wird von uns in der passenden Größe erzeugt
				// size enthält die benötigte Größe des Arrays
				.toArray(size -> new Animal[size]);
		
		System.out.println("\nTiere mit s im Namen");
		Stream.of(someAnimals3).forEach(System.out::println);
		
		
		// die Lat. Namen der Carnivoren in einer Collection von Strings
		var carnivores =
				Stream.of(allAnimals).filter(Predicate.not(Animal::isHerbivore))
				// damit ändert sich der Stream von Stream<Animal> zu Stream<String>
				.map(a -> a.getLatinName())
				.collect(Collectors.toSet());
		System.out.println("\nFleischfresser (lat. Namen):");
		carnivores.forEach(System.out::println);
		
	}

}

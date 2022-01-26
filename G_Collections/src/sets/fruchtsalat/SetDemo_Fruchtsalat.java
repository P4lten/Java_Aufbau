package sets.fruchtsalat;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo_Fruchtsalat {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		erzeugeFruchtsalat(new HashSet<>());

		erzeugeFruchtsalat(new TreeSet<>());
		
		erzeugeFruchtsalat(new TreeSet<>(String.CASE_INSENSITIVE_ORDER));
	}

	static void erzeugeFruchtsalat(Set<String> fruechte) {
		// elemente einfügen
	
		fruechte.add("Banane");
		fruechte.add("Apfel");
		fruechte.add("Birne");
		fruechte.add("Kiwi");

		// Iteration über Index ist nicht möglich
//				for (int i = 0; i < fruechte.size(); i++) {
//					// das Element am Index holen
//					String frucht = fruechte.get(i);
//					System.out.printf("%d. Frucht: %s \n", i+1, frucht);
//					
//				}
//				System.out.println("Welche Frucht dazugeben?");
//				String input = scanner.nextLine();

		// aber mit for-each

		for (String frucht : fruechte) {
			System.out.println(frucht);
		}
		System.out.println("Welche frucht hinzufügen");
		String input = scanner.nextLine();
		
		if(fruechte.contains(input)) {
			System.out.printf("%s ist drin\n", input);
		}else {
			System.out.printf("%s ist nicht drin\n", input);
		}
		
		
		// einfügen oder ersetzen
		if (fruechte.add(input)) {
			System.out.printf("%s wurde hinzugefügt\n", input);
		} else {
			System.out.printf("%s war schon drin\n", input);
		}

//				// einfügen wenn noch nicht drin
//				if (fruechte.contains(input)) {
//					System.out.println(input+ "ist schon drin");
//				}else {
//					//am Beginn einfügen alle anderen werden nach hinten verschoben
//					fruechte.add(0, input);
//				}

		System.out.println("Alle Früchte: ");
		// Iteration mit foreach
		for (String frucht : fruechte) {
			System.out.println(frucht);
		}

		System.out.println("Welche Frucht entfernen?");
		input = scanner.nextLine();
		boolean entfernt;
		// Entfernen Variante 1: -> je nach Set-Typ wird der Vergleich wird mit
		// hashCode/equals oder Comperable/Comperator vorgenommen
		if (fruechte.remove(input)) {
			System.out.println(input + " wurde entfernt");
			entfernt = true;
		} else {
			System.out.println(input + " war nicht drin");
			entfernt = false;
		}

		// wenn remove nichts bewirkt hat -> mit Iterator versuchen
		if (!entfernt) {
			// Entfernen Variante 2: mit Iterator
			Iterator<String> iterator = fruechte.iterator();
			// solange der Iterator ein weiteres Element zur Verfügung hat
			while (iterator.hasNext()) {
				// auf dieses Element positionieren und es holen
				String frucht = iterator.next();

				// wenn eingabe und Element gleich sind
				if (frucht.equalsIgnoreCase(input)) {
					// das Element über den Iterator entfernen
					iterator.remove();
					System.out.println(frucht + " wurde entfernt");
				} else {
					System.out.println(frucht + " bleibt drin");

				}
			}
		}

		System.out.println("Früchte nach dem Löschen");
		for (String frucht : fruechte) {
			System.out.println(frucht);

		}
		// Sortieren des setist nicht möglich - entweder ist es ungeordnet oder bereits sortiert
		//Collections.sort(fruechte);

		// toString der Listenklasse
		System.out.println("Alle Früchte:");
		System.out.println(fruechte/* .toString() */);

	}

}

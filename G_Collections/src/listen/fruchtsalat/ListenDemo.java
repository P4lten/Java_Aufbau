package listen.fruchtsalat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ListenDemo {

	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		// Implementierende Klasse kann dem Basisinterface zugewisen werden
		// wenn das Typargument übereinstimmt
		List<String> liste1 = new ArrayList<String>();
		liste1.add("ASDFA");
		erzeugeFruchtsalat(liste1);
	}

	static void erzeugeFruchtsalat(List<String> fruechte) {
		// Elemente am Ende der Liste hinzufügen
		fruechte.add("Banane");
		fruechte.add("Apfel");
		fruechte.add("Birne");
		fruechte.add("Kiwi");
		
		// Iteration über Index
		for (int i = 0; i < fruechte.size(); i++) {
			// das Element am Index holen
			String frucht = fruechte.get(i);
			System.out.printf("%d. Frucht: %s \n", i+1, frucht);
			
		}
		System.out.println("Welche Frucht dazugeben?");
		String input = scanner.nextLine();
		
		// einfügen wenn noch nicht drin
		if (fruechte.contains(input)) {
			System.out.println(input+ "ist schon drin");
		}else {
			//am Beginn einfügen alle anderen werden nach hinten verschoben
			fruechte.add(0, input);
		}
		
		System.out.println("Alle Früchte: ");
		//Iteration mit foreach
		for (String frucht : fruechte) {
			System.out.println(frucht);
		}
		
		System.out.println("Welche Frucht entfernen?");
		input = scanner.nextLine();
		boolean entfernt;
		//Entfernen Variante 1: -> der Vergleich wird mit equals vorgenommen
		if (fruechte.remove(input)) {
			System.out.println(input + " wurde entfernt");
			entfernt = true;
		}else {
			System.out.println(input + " war nicht drin");
			entfernt = false;
		}
		
		// wenn remove nichts bewirkt hat -> mit Iterator versuchen
		if(!entfernt) {
			//Entfernen Variante 2: mit Iterator
			Iterator<String> iterator = fruechte.iterator();
			//solange der Iterator ein weiteres Element zur Verfügung hat
			while (iterator.hasNext()) {
				// auf dieses Element positionieren und es holen
				String frucht = iterator.next();
				
				// wenn eingabe und Element gleich sind
				if (frucht.equalsIgnoreCase(input)){
					// das Element über den Iterator entfernen
					iterator.remove();
					System.out.println(frucht + " wurde entfernt");
				}else {
					System.out.println(frucht + " bleibt drin");
					
				}
			}
		}
		
		System.out.println("Früchte nach dem Löschen");
		for (String frucht : fruechte) {
			System.out.println(frucht);
			
		}
		
		Collections.sort(fruechte);
		
		// toString der Listenklasse
		System.out.println("Früchte sortiert:");
		System.out.println(fruechte/*.toString()*/);
		
	}
	
	
}

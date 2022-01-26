package maps.simpleMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class MapDemo1 {

	static Scanner input = new Scanner(System.in);
	public static void main(String[] args) {
		// zuordnung von mitarbeiter-Nr zu Name des Mitarbeiters
		Map<Integer, String> alleMitarbeiter = new HashMap<>();

		alleMitarbeiter.put(66, "Dieter Kolm");
		alleMitarbeiter.put(51, "Reiner Zufall");
		alleMitarbeiter.put(15, "Peter Altenberger");
		alleMitarbeiter.put(23, "James Jamboa");
		
		printTable("Alle Mitarbeiter: ", alleMitarbeiter);
		
		// Mitarbeiter mit Nr 15 wurde falsch geschrieben -> ausbessern
		// put ersetzt den Eintrag, wenn der Key schon drin ist
		alleMitarbeiter.put(15, "Peter Altenberg");
		printTable_Entries("Alle Mitarbeiter nach der Korrektur", alleMitarbeiter);
		
		
		System.out.println("Welchen Mitarbeiter suchen?");
		
		
		Integer nr = input.nextInt();
		// den Mitarbeiter holen
		String maName = alleMitarbeiter.get(nr);
		// wenn maName != null, gibt es ein Element mit dem Schlüssel
		if (maName != null) {
			System.out.println("Mitarbeiter gefunden: " + maName);
		}else {
			System.out.println("Mitarbeiter existiert nicht");
		}
		
		
		// Mitarbeiter löschen
		System.out.println("Welchen Mitarbeiter löschen?");
		nr = input.nextInt();
		
		// wenn die Nummer vorhanden ist -> löschen
		if (alleMitarbeiter.containsKey(nr)) {
			alleMitarbeiter.remove(nr);
			System.out.printf("Mitarbeiter entfernt, die Map enthält jetzt %d Einträge\n", alleMitarbeiter.size());
		} else {
			System.out.println("Mitarbeiter existiert nicht");
		}
		
		printTable_Entries("Nach dem Löschen: ", alleMitarbeiter);
		
		
	}

	
	
	static void printTable(String text, Map<Integer, String> mitarbeiter) {
		System.out.println(text);

		// Überschrift
		System.out.println("Nr.   Name");
		// für jedes Key-Element:
		for (Integer key : mitarbeiter.keySet()) {
			// des Value dazu holen
			String maName = mitarbeiter.get(key);
			System.out.printf("%5d %s\n", key, maName);
		}
		
		System.out.println();
	}
	
	static void printTable_Entries(String text, Map<Integer, String> mitarbeiter) {
		System.out.println(text);

		// Überschrift
		System.out.println("Nr.   Name");
		// für jeden Eintrag:
		for (Map.Entry<Integer, String> entry : mitarbeiter.entrySet()) {
			Integer key = entry.getKey();
			String maName = entry.getValue();
			System.out.printf("%5d %s\n", key, maName);
		}
		
		
		
		
//		for (Integer key : mitarbeiter.keySet()) {
//			// des Value dazu holen
//			String maName = mitarbeiter.get(key);
//			System.out.printf("%5d %s\n", key, maName);
//		}
		
		System.out.println();
	}
}

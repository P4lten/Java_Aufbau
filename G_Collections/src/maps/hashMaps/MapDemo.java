package maps.hashMaps;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MapDemo {

	public static void main(String[] args) {

		// eigene Klasse als Key-Typ verwenden
		Map<PersonalNr, String> alleMitarbeiter = new HashMap<>();
		alleMitarbeiter.put(new PersonalNr(1, 5), "Peter Maier");
		alleMitarbeiter.put(new PersonalNr(5, 1), "Gabi Müller");
		alleMitarbeiter.put(new PersonalNr(2, 4), "Karin Berger");
		alleMitarbeiter.put(new PersonalNr(2, 3), "Kurt Schwarz");

		printTable("Alle Mitarbeiter:", alleMitarbeiter);
		// Peter Maier haben wir falsch geschrieben --> ausbessern:
		alleMitarbeiter.put(new PersonalNr(1, 5), "Peter Mayer");
		printTable("Nach der Korrektur:", alleMitarbeiter);

		// Eintrag suchen nach Key
		PersonalNr key = readPersonalNr("Welchen Mitarbeiter suchen?");
		if (alleMitarbeiter.containsKey(key))
			System.out.printf("PersonalNr %s ==> Mitarbeiter '%s'\n", key, alleMitarbeiter.get(key));
		else
			System.out.printf("PersonalNr %s wurde nicht gefunden\n", key);
		System.out.println();

		key = readPersonalNr("Welchen Mitarbeiter löschen? ");
		// Eintrag löschen, falls vorhanden
		if (alleMitarbeiter.containsKey(key))
			alleMitarbeiter.remove(key);
		else
			System.out.printf("PersonalNr %s wurde nicht gefunden\n", key);
		System.out.println();
		printTable("Nach dem Löschen:", alleMitarbeiter);

	}

	public static void printTable(String text, Map<PersonalNr, String> mitarbeiter) {
		System.out.println(text);
		System.out.println("Nr. \tName");
		System.out.println("-----\t------------");

		for (Map.Entry<PersonalNr, String> pair : mitarbeiter.entrySet())
			System.out.printf("%s\t%s\n", pair.getKey(), pair.getValue());

		System.out.println();

	}

	private static Scanner scanner = new Scanner(System.in);
	private static PersonalNr readPersonalNr(String info) {
		try {
			System.out.println(info);
			System.out.print("Eingabe (Format Abteilung-Nummer, z.B. 2-3): ");
			String[] input = scanner.nextLine().split("-");
			if (input.length != 2) {
				throw new IllegalArgumentException("Fehlerhaftes Eingabeformat");
			}
			int abteilung = Integer.parseInt(input[0]);
			int nummer = Integer.parseInt(input[1]);
			return new PersonalNr(abteilung, nummer);
		} catch (Exception e) {
			return readPersonalNr("Fehlerhafte Eingabe, neuer Versuch. " + info);
		}
	}

}


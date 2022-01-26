package zeitPackage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UhrezeitProgramm2 {

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Uhrzeit beginn, ende;
		beginn = zeitEinlesen("Eingabe Beginnzeit");
		ende = zeitEinlesen("Eingabe Endezeit");

		System.out.println("Dauer von ");
		beginn.anzeigen();
		System.out.print(" bis ");
		ende.anzeigen();
		
		// Dauer berechnen
		int dauer = beginn.calcDifferenz(ende);
		System.out.printf("\nDauer: %d Minuten\n", dauer);

	}

	static Uhrzeit zeitEinlesen(String info) {
		System.out.println(info);

		try {
			int s, m;
			System.out.print("Stunde: ");
			s = input.nextInt();
			System.out.print("Minute: ");
			m = input.nextInt();
			// den eingabepuffer leeren
			input.nextLine();

			Uhrzeit zeit = new Uhrzeit();
			zeit.setzen(s, m);
			return zeit;

		} catch (IllegalArgumentException e) {
			// Den Fehler anzeigen
			System.out.println("Es ist ein Fehler aufgetreten: ");
			input.nextLine();
			// die Methode rekursiv noch einmal aufrufen
			return zeitEinlesen(info);
		}catch  (InputMismatchException e) {
			System.out.println("Es ist ein Fehler aufgetreten: " + e.getMessage());
			return zeitEinlesen(info);

		}
	}

}

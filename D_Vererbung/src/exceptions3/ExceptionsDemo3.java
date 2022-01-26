package exceptions3;

import java.util.Scanner;

public class ExceptionsDemo3 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1. Berechnung (Version 3):");
		runCalculation();
		System.out.println("2. Berechnung (Version 3):");
		runCalculation();
	}

	private static void runCalculation() {
		try {
			char op;
			int number1, number2, result;
			// Operator und Operanden einlesen
			System.out.print("1. Operand: ");
			number1 = Integer.parseInt(scanner.nextLine());
			System.out.print("Welche Operation (+ - * /)? ");
			op = scanner.nextLine().charAt(0);
			System.out.print("2. Operand: ");
			number2 = Integer.parseInt(scanner.nextLine());
			// Berechnung ausführen
			Calculator calc = new Calculator();
			result = calc.calculate(op, number1, number2);

			// Ergebnis anzeigen
			System.out.printf("%d%c%d=%d %n", number1, op, number2, result);

			// Ergebnisse anzeigen
		} catch (NumberFormatException e) {
			// Fehlerhafte Eingabe einer Zahl
			System.out.println("Fehlerhafte Eingabe: " + e.getMessage());
			
			// bei uncheckded Exception ist dieser catch-Block optional (aber sinnvoll)
		} catch (CalculationException e) {
			// die eigene Fehlerklasse behandeln
			System.out.println("Fehler in der Berechnung: " + e.toString());
			if (e.getCause() != null) {
				System.out.println("\tGrund: " + e.getCause().toString());
			}
			// e.printStackTrace();
		}
	}
}

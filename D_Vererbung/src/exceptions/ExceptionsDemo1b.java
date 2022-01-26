package exceptions;

import java.util.Scanner;

public class ExceptionsDemo1b {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.println("1. Berechnung: (Version 1b)");
		runCalculation();
		System.out.println("2. Berechnung: (Version 1b)");
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

			// gkeucger Block für mehrere Exceptionklassen kann auch mit
			// gemeinsamer Basisklasse definiert werden
		} catch (RuntimeException e) {
			// alle Laufzeitfehler
			System.out.println("Es ist ein Fehler aufgetreten: " + e.toString());

		}
	}
}

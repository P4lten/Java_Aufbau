package übung2;


import java.util.Scanner;

public class Übung2 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		int result = 0;
		int number1;
		int number2;

		System.out.println("Bitte Gleichung eingeben: ");

		number1 = scan.nextInt();
		String symbol = scan.next();
		number2 = scan.nextInt();

		switch (symbol) {
		case "+" -> result += add(number1, number2);
		case "-" -> result += minus(number1, number2);
		case "/" -> result += divide(number1, number2);
		case "*" -> result += multiply(number1, number2);

		default -> throw new IllegalArgumentException("Unexpected symbol: " + symbol);
		}

		if (!(symbol.equals("="))) {
			do {
				symbol = scan.next();
				if (symbol.equals("=")) {
					break;
				}
				number2 = scan.nextInt();

				switch (symbol) {
				case "+" -> result = add(result, number2);
				case "-" -> result = minus(result, number2);
				case "/" -> result = divide(result, number2);
				case "*" -> result = multiply(result, number2);

				default -> throw new IllegalArgumentException("Unexpected symbol: " + symbol);
				}
			} while (!(symbol.equals("=")));
		}

		System.out.println("Ergebniss= " + result);

		int[] primzahlen = findPrimzahl(result);

		System.out.println(result + "= ");
		
		primfaktorzerlegung(primzahlen, result);

		scan.close();
	}

	static int add(int a, int b) {
		int add = a + b;
		return add;
	}

	static int minus(int a, int b) {
		int minus = a - b;
		return minus;
	}

	static int divide(int a, int b) {
		int divide = a / b;
		return divide;
	}

	static int multiply(int a, int b) {
		int multiply = a * b;
		return multiply;
	}

	static int[] findPrimzahl(int arrayLength) {

		int[] array;
		array = new int[arrayLength];
		int i = 2;// Prim = true wenn i eine Primzahl ist, fangen bei 2 an weil wir 1 nicht
					// brauchen
		int counter = 0;
		boolean Prim = true; // Prim = true wenn i eine Primzahl ist
		while (i < arrayLength) {
			for (int j = 2; j < i - 1; j++) {

				if (i % j == 0) {

					Prim = false;
				}
			}

			if (Prim) {

				array[counter] = i;
				counter++;
//				System.out.println("Prim= " + i); // zur Überprüfung
			} else {

				Prim = true;
			}
			i++;

		}
		return array;

	}
	// gibt die primfaktorenzerlegung aus
	static void primfaktorzerlegung(int[] array, int zahl) {

		int ergebniss = zahl;
		int i = 0;

		while (i < array.length && array[i] != 0) {
			if (ergebniss % array[i] == 0) {
				ergebniss = ergebniss / array[i];
				System.out.print(array[i]);
				if (ergebniss != 1) { // um das Vorgegebene Format einzuhalten
					System.out.print("*");
				}
			} else {
				i++;

			}

		}

	}
}

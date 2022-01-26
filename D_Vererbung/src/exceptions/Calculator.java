package exceptions;

public class Calculator {
	public int calculate(char op, int number1, int number2) {

		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// TODO: handle division by zero
			return number1 / number2;
		case '*':
			return number1 * number2;
		default:
			System.out.println("Ungueltiger Operator");
			//return 0; // nicht normal weiterlaufen, sondern Fehler werfen
			throw new IllegalArgumentException("Ungültiger Operator");
		}
	}
}
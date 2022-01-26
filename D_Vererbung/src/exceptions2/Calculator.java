package exceptions2;

public class Calculator {
	public int calculate(char op, int number1, int number2)
			// "specify": beim Aufruf der Methode calculate kann eine CalculationException auftreten
			// => der Aufrufer muss die CalculationException fangen ider selbst eine
			// throws-Deklaration angeben

			throws CalculationException {
		// Berechnung je nach Operator
		switch (op) {
		case '+':
			return number1 + number2;
		case '-':
			return number1 - number2;
		case '/':
			// handle division by zero
			try {
			return number1 / number2;
			}catch(ArithmeticException e) {
				// wenn eine ArithmeticException auftritt, => einpacken und weiterwerfen
				throw new CalculationException("Fehler in der Division ", e);
			}
		case '*':
			return number1 * number2;
		default:
			System.out.println("Ungueltiger Operator");
			// return 0; // nicht normal weiterlaufen, sondern Fehler werfen
			throw new CalculationException("Ungültiger Operator " + op);
		}
	}
}
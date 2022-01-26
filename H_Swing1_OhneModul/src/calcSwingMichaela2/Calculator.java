package calcSwingMichaela2;

public class Calculator {
	
	// Felder für die Berechnung
		// Operand (die Zahl)
		private Integer number;
		// Operator
		private String operator;
		// Ergebnis
		private Integer result;
		// akt. Berechnung
		private String currentCalculation = "";
		// Flag ob zuletzt ein Operator dran war
		private boolean isOperator;

		
		public Integer getNumber() {
			return number;
		}
		
		public String getOperator() {
			return operator;
		}
		
		public Integer getResult() {
			return result;
		}
		
		public String getCurrentCalculation() {
			return currentCalculation;
		}
		
		public void processNumber(int number) {
			this.number = number;
			// zuletzt war kein Operator dran
			isOperator = false;
		}
		
		
		public void processOperator(String newOperator) {
			if (newOperator.equals("C")) {
				operator = null;
				number = result = null;
				currentCalculation = "";

			} else {

				if (isOperator && !this.operator.equals("=")) {
					throw new IllegalStateException("Es wurde eine Zahl erwartet");
				}

				if (number == null) {
					throw new IllegalArgumentException("Es wurde keine Zahl angegeben");
				}

				isOperator = true;

				// wenn vorher der Ergebnis-Operator dran war -> die Berechnungszeichenfolge
				// zurücksetzen
				if (operator != null && operator.equals("=")) {
					currentCalculation = "";
				}
				// die neuen Eingaben anhängen
				currentCalculation += number + newOperator;

				// wenn noch kein Operator vorhanden ist -> Zahl als Ergebnis verwenden
				if (operator == null) {
					result = number;
				} else {
					// sonst: Ergebnis berechnen
					result = calculate(operator, result, number);
				}

				// wenn jetzt der Ergebnisoperator dran ist: bisheriges Ergebnis als Zahl merken
				if (newOperator.equals("=")) {
					number = result;
				} else {
					// sonst: die Zahl zurücksetzen
					number = null;
				}
				operator = newOperator;

			}

		}
		
		private static int calculate(String op, int n1, int n2) {
			int newResult;

			switch (op) {
			case "+":
				newResult = n1 + n2;
				break;
			case "-":
				newResult = n1 - n2;
				break;
			case "/":
				newResult = n1 / n2;
				break;
			case "*":
				newResult = n1 * n2;
				break;
			case "%":
				newResult = n1 % n2;
				break;
			case "=":
				newResult = n1;
				break;
			default:
				throw new IllegalArgumentException("Unbekannter Operator " + op);
			}

			return newResult;

		}
		
		
		
		
}

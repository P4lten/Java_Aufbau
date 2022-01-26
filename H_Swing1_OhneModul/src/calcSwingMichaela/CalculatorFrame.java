package calcSwingMichaela;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class CalculatorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	// Felder für die Controls
	private JButton[] btnDigits;
	private JButton[] btnOperators;

	private JLabel lblTitle, lblCurrentCalculation, lblResult, lblNumber, lblStatus;

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

	public CalculatorFrame() {
		super("Taschenrechner mit Swing");

		setSize(330, 600);
		setLayout(null);

		int x = 30, y = 30, width = 250, height = 25;

		add(lblTitle = createLabel("Taschenrechner", x, y, width, height));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		y += height + 20;

		// akt. Berechnung
		add(lblCurrentCalculation = createLabel("(Berechnung)", x, y, width, height));
		lblCurrentCalculation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCurrentCalculation.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

		// Zwischen Ergebnis
		y += height + 5;
		add(lblResult = createLabel("(Ergebnis)", x, y, width, height));
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);

		// Operand (akt. Zahl)
		y += height + 5;
		add(lblNumber = createLabel("", x, y, width, height));
		lblNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		// lblOperand.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		y += height + 5;

		height = width = 60;
		// Buttons für Ziffern
		btnDigits = new JButton[10];
		for (int i = 0; i < btnDigits.length; i++) {
			btnDigits[i] = new JButton(Integer.toString(i));
			btnDigits[i].setActionCommand(Integer.toString(i));
			btnDigits[i].addActionListener(this::verarbeiteZiffer);
		}
		// Buttons für Operatoren
		String[] operatoren = { "+", "-", "*", "/", "%", "=", "C" };
		btnOperators = new JButton[operatoren.length];
		for (int i = 0; i < btnOperators.length; i++) {
			btnOperators[i] = new JButton(operatoren[i]);
			btnOperators[i].setActionCommand(operatoren[i]);
			btnOperators[i].addActionListener(this::verarbeiteOperator);
		}

		// Ziffern, Operatoren in Zeilen/Spalten einteilen
		JButton[][] rows = new JButton[][] {
				{ btnOperators[0], btnOperators[1], btnOperators[2], btnOperators[3] },
				{ btnDigits[7], btnDigits[8], btnDigits[9], btnOperators[4] },
				{ btnDigits[4], btnDigits[5], btnDigits[6], btnOperators[5] },
				{ btnDigits[1], btnDigits[2], btnDigits[3], btnOperators[6] },
				{ null, btnDigits[0], null, null },
		};
		// alle buttons anzeigen
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				JButton btn = rows[i][j];
				if (btn != null) {
					add(btn);
					btn.setSize(width, height);
					btn.setLocation(x + j * (width + 5), y);
				}
			}
			y += height + 5;

		}

		y += 10;
		// Status-Anzeige
		add(lblStatus = createLabel("(Status)", x, y, 300, height));
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JLabel createLabel(String text, int x, int y, int w, int h) {
		JLabel lbl = new JLabel(text);
		lbl.setLocation(x, y);
		lbl.setSize(w, h);

		return lbl;
	}

	// als mögliche Handler-Methode für ActionListener definieren
	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String digit = evt.getActionCommand();
			showInfo("Verarbeite Ziffer " + digit);
			// die neue Zahl ermitteln
			number = Integer.parseInt(lblNumber.getText() + digit);
			isOperator = false;
			// und im Label anzeigen
			lblNumber.setText(String.valueOf(number));
		} catch (Exception e) {
			showError(e, "Fehler beim Verarbeiten der Ziffer");
		}
	}

	private void verarbeiteOperator(ActionEvent evt) {
		try {
			String newOperator = evt.getActionCommand();
			showInfo("Verarbeite Operator " + newOperator);

			// Lösch-Operator -> alles zurücksetzen
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
			// neue Werte anzeigen
			lblNumber.setText(number == null ? "" : String.valueOf(number));
			lblResult.setText(result == null ? "" : String.valueOf(result));
			lblCurrentCalculation.setText(currentCalculation);

		} catch (Exception e) {
			showError(e, "Fehler beim Verarbeiten des Operators");
		}
	}

	private void showError(Exception e, String text) {
		System.err.println(text);
		e.printStackTrace();
		lblStatus.setText(e.getMessage());
	}

	private void showInfo(String text) {
		System.out.println(text);
		lblStatus.setText(text);
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

	public static void main(String[] args) {
		new CalculatorFrame().setVisible(true);

	}

	private int onCalculate(String op, int n1, int n2) {
		int newResult;
		System.out.println("onCalculate Operator " + op);

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
		lblStatus.setText("Operator " + op) ;
		lblResult.setText(String.valueOf(n2));

		return newResult;

	}
	private int onCalculatePrev(String op, int n1, int n2) {
		int newResult;

		switch (op) {
		case "+":
			System.out.println("onCalculate Operator " + op);
			newResult = n1 + n2;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		case "-":
			System.out.println("onCalculate Operator " + op);
			newResult = n1 - n2;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		case "/":
			System.out.println("onCalculate Operator " + op);
			newResult = n1 / n2;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		case "*":
			System.out.println("onCalculate Operator " + op);
			newResult = n1 * n2;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		case "%":
			System.out.println("onCalculate Operator " + op);
			newResult = n1 % n2;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		case "=":
			System.out.println("onCalculate Operator " + op);
			newResult = n1;
			lblStatus.setText("Operator " + op) ;
			lblResult.setText(String.valueOf(n2));
			break;
		default:
			throw new IllegalArgumentException("Unbekannter Operator " + op);
		}

		return newResult;

	}
	
	

}

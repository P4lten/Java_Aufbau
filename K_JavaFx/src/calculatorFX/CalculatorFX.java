/**
 Kann die size von den Buttons nicht aendern deswegen sind sie so klein...
 */

package calculatorFX;

import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class CalculatorFX extends GridPane {

	private Button btnDigits[], btnOperators[];

	private Label lblTitle, lblCurrentCalculation, lblResult, lblNumber, lblStatus;

	private Calculator calc = new Calculator();

	public CalculatorFX() {
		// Abstand zwischen Controls
		setHgap(10);
		setVgap(10);

		// Abstand zum Rand
		setPadding(new Insets(20));

		// Controls erzeugen
		int row = 0;

		
		
		// Titel
		lblTitle = new Label("Calculator FX");
		add(lblTitle,
				// Spalte und Zeile
				0, row,
				// Anzahl Spalten / Zeilen
				4, 1);
		lblTitle.setFont(Font.font(20));

		setHalignment(lblTitle, HPos.CENTER);
		

		lblCurrentCalculation = new Label("Berechnung");
		add(lblCurrentCalculation, 0, row + 1, 4, 1);
		lblCurrentCalculation.setTextAlignment(TextAlignment.RIGHT);
		setHalignment(lblCurrentCalculation, HPos.RIGHT);
		
//		// Zwischen Ergebnis

		lblResult = new Label("Ergebnis");
		add(lblResult, 0, row + 2, 4, 1);
		setHalignment(lblResult, HPos.RIGHT);
		
		// Operand (akt. Zahl)
		lblNumber = new Label("");
		add(lblNumber, 0, row + 3, 4, 1);
		setHalignment(lblNumber, HPos.RIGHT);
		
		row += 4;

		// Buttons für Ziffern
		btnDigits = new Button[10];
		for (int i = 0; i < btnDigits.length; i++) {
			btnDigits[i] = new Button(Integer.toString(i));
			btnDigits[i].setUserData(Integer.toString(i));
			btnDigits[i].setOnAction(this::verarbeiteZiffer);

		}
		// Buttons für Operatoren
		String[] operatoren = { "+", "-", "*", "/", "%", "=", "C" };
		btnOperators = new Button[operatoren.length];
		for (int i = 0; i < btnOperators.length; i++) {
			btnOperators[i] = new Button(operatoren[i]);
			btnOperators[i].setUserData(operatoren[i]);
			btnOperators[i].setOnAction(this::verarbeiteOperator);
		}

		// Ziffern, Operatoren in Zeilen/Spalten einteilen
		Button[][] rows = new Button[][] { { btnOperators[0], btnOperators[1], btnOperators[2], btnOperators[3] },
				{ btnDigits[7], btnDigits[8], btnDigits[9], btnOperators[4] },
				{ btnDigits[4], btnDigits[5], btnDigits[6], btnOperators[5] },
				{ btnDigits[1], btnDigits[2], btnDigits[3], btnOperators[6] }, { null, btnDigits[0], null, null }, };
		// alle buttons anzeigen
		for (int i = 0; i < rows.length; i++) {
			for (int j = 0; j < rows[i].length; j++) {
				Button btn = rows[i][j];
				if (btn != null) {
					add(btn, j, row);
					btn.setPrefSize(50, 50);
					
				}
			}
			row++;

		}
		lblStatus = new Label("Status");
		add(lblStatus, 0, row + 10, 4, 1);
		btnDigits[1].setUserData(Integer.toString(1));
	}

	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String digit = ((Button) evt.getSource()).getText();
			showInfo("Verarbeite Ziffer " + digit);

			calc.processNumber(Integer.parseInt(lblNumber.getText() + digit));
			// und im Label anzeigen
			lblNumber.setText(String.valueOf((Integer.parseInt(lblNumber.getText() + digit))));
		} catch (Exception e) {
			showError(e, "Fehler beim Verarbeiten der Ziffer");
		}
	}

	private void verarbeiteOperator(ActionEvent evt) {
		try {
			String newOperator = ((Button) evt.getSource()).getText();
			showInfo("Verarbeite Operator " + newOperator);
			calc.processOperator(newOperator);

			// neue Werte anzeigen
			lblNumber.setText(calc.getNumber() == null ? "" : String.valueOf(calc.getNumber()));
			lblResult.setText(calc.getResult() == null ? "" : String.valueOf(calc.getResult()));
			lblCurrentCalculation.setText(calc.getCurrentCalculation());

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
}

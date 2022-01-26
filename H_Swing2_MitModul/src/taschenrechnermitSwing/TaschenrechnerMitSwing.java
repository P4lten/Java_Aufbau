package taschenrechnermitSwing;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Arrays;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class TaschenrechnerMitSwing extends JFrame {

	private static final long serialVersionUID = 1L;

	final JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnAdd, btnMinus, btnDivide, btnMultiply,
			btnModulo, btnEquals, btnClear;
	final JLabel lblInfo, lblInfo2, lblInfo3;

	private String op, ziffer;
	int counter = 0;
	double erg;
	double zwischenergebniss;
	int i = 0;
	int j = 1;
	double arrayDouble[];
	String arrayString[];
			
	public TaschenrechnerMitSwing() {
		super("Einfacher Taschenrechner");
		setSize(320, 600);
		setLayout(null);
		int x = 30, y = 50, width = 50, height = 25;

		btn1 = createButton("1", "1", x, y + (3 * height) + 30 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn1.addActionListener(this::verarbeiteZiffer);
		add(btn1);

		btn2 = createButton("2", "2", x + width + 10, y + (3 * height) + 30 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn2.addActionListener(this::verarbeiteZiffer);
		add(btn2);

		btn3 = createButton("3", "3", x + (2 * width) + 20, y + (3 * height) + 30 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn3.addActionListener(this::verarbeiteZiffer);
		add(btn3);

		btn4 = createButton("4", "4", x, y + (2 * height) + 20 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn4.addActionListener(this::verarbeiteZiffer);
		add(btn4);

		btn5 = createButton("5", "5", x + width + 10, y + (2 * height) + 20 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn5.addActionListener(this::verarbeiteZiffer);
		add(btn5);

		btn6 = createButton("6", "6", x + (2 * width) + 20, y + (2 * height) + 20 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn6.addActionListener(this::verarbeiteZiffer);
		add(btn6);

		btn7 = createButton("7", "7", x, y + height + 10 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn7.addActionListener(this::verarbeiteZiffer);
		add(btn7);

		btn8 = createButton("8", "8", x + width + 10, y + height + 10 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn8.addActionListener(this::verarbeiteZiffer);
		add(btn8);

		btn9 = createButton("9", "9", x + (2 * width) + 20, y + height + 10 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn9.addActionListener(this::verarbeiteZiffer);
		add(btn9);

		btn0 = createButton("0", "0", x + width + 10, y + (4 * height) + 40 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btn0.addActionListener(this::verarbeiteZiffer);
		add(btn0);

		btnAdd = createButton("+", "+", x, y + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnAdd.addActionListener(this::verarbeiteOperator);
		add(btnAdd);

		btnModulo = createButton("%", "%", x + (3 * width) + 30, y + height + 10 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnModulo.addActionListener(this::verarbeiteOperator);
		add(btnModulo);

		btnMinus = createButton("-", "-", x + width + 10, y + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnMinus.addActionListener(this::verarbeiteOperator);
		add(btnMinus);

		btnDivide = createButton("/", "/", x + (3 * width) + 30, y + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnDivide.addActionListener(this::verarbeiteOperator);
		add(btnDivide);

		btnMultiply = createButton("*", "*", x + (2 * width) + 20, y + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnMultiply.addActionListener(this::verarbeiteOperator);
		add(btnMultiply);

		btnEquals = createButton("=", "=", x + (3 * width) + 30, y + (2 * height) + 20 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnEquals.addActionListener(this::verarbeiteOperatorGleich);
		add(btnEquals);

		btnClear = createButton("C", "C", x + (3 * width) + 30, y + (3 * height) + 30 + 200, width, height);
		// Methodenreferenz als Eventhandler
		btnClear.addActionListener(this::verarbeiteOperator);
		add(btnClear);

		lblInfo = new JLabel();
		lblInfo.setBounds(x + width + 25, y + 150, width + 80, height);
		lblInfo.setHorizontalAlignment(JTextField.RIGHT);
		Font font = new Font("Arial", 0, 15);
		lblInfo.setFont(font);
		add(lblInfo);

		lblInfo2 = new JLabel("Rechner");
		lblInfo2.setBounds(x + width + 25, y, width + 50, height);
		Font font2 = new Font("Arial", Font.BOLD + Font.ITALIC, 25);
		lblInfo2.setFont(font2);
		add(lblInfo2);

		lblInfo3 = new JLabel();
		lblInfo3.setBounds(x + width + 25, y + 170, width + 80, height);
		lblInfo3.setHorizontalAlignment(JTextField.RIGHT);
		Font font3 = new Font("Arial", 0, 15);
		lblInfo3.setFont(font3);
		add(lblInfo3);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JButton createButton(String text, String action, int x, int y, int w, int h) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		btn.setLocation(x, y);
		btn.setSize(w, h);

		return btn;
	}

	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			ziffer = evt.getActionCommand();
			int zahl = Integer.parseInt(ziffer);
			System.out.printf("Verarbeite die Ziffer %d\n", zahl);
			lblInfo.setText(lblInfo.getText() + zahl);
			counter++;
		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim Verarbeiten der Ziffer");
		}
	}

	private void verarbeiteOperator(ActionEvent evt) {
		try {
			op = evt.getActionCommand();
			if (op.equals("C")) {
				lblInfo.setText(" ");
				
			}else {
			System.out.printf("Verarbeite den Operator %s\n", op);
			lblInfo.setText(lblInfo.getText() + op);
		}
		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim Verarbeiten des Operators");
		}
	}

	private void verarbeiteOperatorGleich(ActionEvent evt) {
		try {
			String op = evt.getActionCommand();
			System.out.printf("Verarbeite den Operator %s\n", op);
			lblInfo.setText(lblInfo.getText() + op);
			rechnen();
		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim Verarbeiten des Operators");
		}
	}

	private void rechnen() {

		arrayDouble = holeZahl();
		arrayString = holeOp();
		
		

//		for (int j = 0; j < arrayDouble.length; j++) {
//			
//		}
		
		
		switch (arrayString[j]) {
		case "+":
			erg = arrayDouble[i] + arrayDouble[i + 1];
//			lblInfo3.setText(Double.toString(erg));
			arrayDouble[i+1] = erg;
			break;
		case "-":
			erg = arrayDouble[i] - arrayDouble[i + 1];
//			lblInfo3.setText(Double.toString(erg));
			arrayDouble[i+1] = erg;
			break;
		case "*":
			erg = arrayDouble[i] * arrayDouble[i + 1];
//			lblInfo3.setText(Double.toString(erg));
			arrayDouble[i+1] = erg;
			break;
		case "/":
			erg = arrayDouble[i] / arrayDouble[i + 1];
//			lblInfo3.setText(Double.toString(erg));
			arrayDouble[i+1] = erg;
			break;
		case "%":
			erg = arrayDouble[i] % arrayDouble[i + 1];
			arrayDouble[i+1] = erg;
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + op);
		}
		
		System.out.println("I= " + i);
		i++;
		System.out.println("J= " +j);
		if(i < arrayDouble.length-1) {
			j++;
			rechnen();
		}else {
			lblInfo3.setText(Double.toString(erg));

		}
	
	}

	private double[] holeZahl() {
		String text = lblInfo.getText();
		String delims = "[+\\-*/%=C ]+"; // wenn zeichen auftritt trennen wir den String
		String[] stringArrayNum = text.split(delims);
		double[] doubleString = Arrays.stream(stringArrayNum).mapToDouble(Double::parseDouble).toArray();
		System.out.println(Arrays.toString(doubleString));
		return doubleString;
	}
	
	
	private String [] holeOp() {
		String text = lblInfo.getText();
		String delims2 = "[1234567890]+";
		String[] stringArrayOp = text.split(delims2);
		System.out.println(Arrays.toString(stringArrayOp));
		return stringArrayOp;
	}

	private void zeigeFehler(Exception e, String title) {
		System.err.println("Fehler in ButtonsFenster.actionPerformed:");
		e.printStackTrace();
		// Message Box anzeigen
		JOptionPane.showConfirmDialog(this, e.getMessage(), title, JOptionPane.DEFAULT_OPTION, // Welche Buttons
																								// angezeigt werden
																								// sollen
				JOptionPane.ERROR_MESSAGE); // art der Message (Fehler, Info oder...)
	}
}

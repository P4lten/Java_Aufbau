package intro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class ButtonsFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;
	// Attribute für unsere Controls (= Components = Steuerelemente)
	final JButton btnKlick1, btnKlick2, btnKlick3;
	final JLabel lblInfo;

	public ButtonsFenster2() {
		super("Viele Buttons...");
		setSize(290, 600);
		setLayout(null);
		int x = 30, y = 50, width = 100, height = 25;
		
		
		btnKlick1 = createButton("Ziffer 1", "1", x, y, width, height);
		// Methodenreferenz als Eventhandler
		btnKlick1.addActionListener(this::verarbeiteZiffer);
		add(btnKlick1);

		// rechts daneben ( x ist um width und einen Abstand nach rechts verschoben)
		btnKlick2 = createButton("Ziffer 2", "2", x + width + 10, y, width, height);
		btnKlick2.addActionListener(this::verarbeiteZiffer);
		add(btnKlick2);

		// 2. Zeile ( y ist um height und einnen Abstand nach unten verschoben)
		btnKlick3 = createButton("Operator A", "A", x, y + height + 10, width, height);
		btnKlick3.addActionListener(this::verarbeiteOperator);
		add(btnKlick3);

		lblInfo = new JLabel("");
		lblInfo.setBounds(x, y + 2 * (height + 10), 2 * width + 10, height);
		//Textgröße definieren
		Font font = new Font("Arial", Font.BOLD + Font.ITALIC, 20);
		lblInfo.setFont(font);
		add(lblInfo);

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	private JButton createButton(String text, String action, int x, int y, int w, int h) {
		JButton btn = new JButton(text);
		btn.setActionCommand(action);
		btn.setLocation(x, y);
		btn.setSize(w, h);

		return btn;
	}

//	@Override
//	public void actionPerformed(ActionEvent evt) {
//		// eine Handler-Methode für alle Buttons
//		try {
//			switch (evt.getActionCommand()) {
//			case "1":
//			case "2":
//				verarbeiteZiffer(evt.getActionCommand());
//				break;
//			case "A":
//				verarbeiteOperator(evt.getActionCommand());
//				break;
//			default:
//				break;
//			}
//		} catch (Exception e) {
//			System.err.println("Fehler in ButtonsFenster.actionPerformed:");
//			e.printStackTrace();
//			JOptionPane.showConfirmDialog(this, e.getMessage(), "Fehler in actionPerformed", 
//					JOptionPane.DEFAULT_OPTION,// Welche Buttons angezeigt werden sollen
//					JOptionPane.ERROR_MESSAGE); // art der Message (Fehler, Info oder...)
//			
//		}
//
//	}

	// als mögliche Handler-Methode für ActionListener definieren
	private void verarbeiteZiffer(ActionEvent evt) {
		try {
			String ziffer = evt.getActionCommand();
			int zahl = Integer.parseInt(ziffer);
			System.out.printf("Verarbeite die Ziffer %d\n", zahl);
			lblInfo.setText(lblInfo.getText() + zahl);
		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim Verarbeiten der Ziffer");
		}
	}
	
	private void verarbeiteOperator(ActionEvent evt) {
		try {
			String op = evt.getActionCommand();
			System.out.printf("Verarbeite den Operator %s\n", op);
			lblInfo.setText(lblInfo.getText() + op);
		} catch (Exception e) {
			zeigeFehler(e, "Fehler beim Verarbeiten des Operators");
		}
	}
	
	private void zeigeFehler (Exception e, String title) {
		System.err.println("Fehler in ButtonsFenster.actionPerformed:");
		e.printStackTrace();
		// Message Box anzeigen
		JOptionPane.showConfirmDialog(this, e.getMessage(), title, 
				JOptionPane.DEFAULT_OPTION,// Welche Buttons angezeigt werden sollen
				JOptionPane.ERROR_MESSAGE); // art der Message (Fehler, Info oder...)
	}
}
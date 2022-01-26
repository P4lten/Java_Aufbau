package intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ErstesFenster {

	public static void main(String[] args) {
		// Swing mit "Spagetti-Code"

		JFrame fenster = new JFrame("Mein erstes Swingfenster (modular)");

		fenster.setSize(400, 300);
		// absolute Positionierung, dh. wir müssen selber Größe und Position der
		// Steuerelemente festlegen
		fenster.setLayout(null);

		// weitere Steuerelemente
		JLabel lblName = new JLabel("Dein Name");
		// set Loccatuion und set Bounds in einem
		lblName.setBounds(30, 50, 70, 25);
		JTextField txtName = new JTextField();
		txtName.setBounds(110, 50, 150, 25);
		JButton btnAction = new JButton("Klick mich!");
		btnAction.setBounds(110, 100, 100, 25);

		JLabel lblInfo = new JLabel();
		lblInfo.setBounds(30, 140, 200, 50);

		fenster.add(lblName);
		fenster.add(txtName);
		fenster.add(btnAction);
		fenster.add(lblInfo);

		// beim Button eine ActionListener registrieren
		
		btnAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// den Namen aus der Textbox holen
				String name = txtName.getText();
				// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht
				System.out.println("Dein Name: " + name);
				lblInfo.setText("Dein Name: %s".formatted(name));
			}
		});

		

		// das Fenster wirklich schließen wenn "Close" betätigt wird
		// weil es das Hauptfenster ist, wird damit das Praogramm beendet
		fenster.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// das Fenster anzeigen, das Programm läuft weiter bis das Fenster geschlossen
		// wird
		fenster.setVisible(true);

	}

}

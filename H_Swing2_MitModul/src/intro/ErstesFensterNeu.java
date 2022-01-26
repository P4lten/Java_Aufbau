package intro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

// Ausführen von der Commandline (modular)
// -p ... --modul-path ... Pfad zu den Module-Class-Files
// -m ... Main Klasse (mit Modulnamen/Klassenname)
// java -p H_Swing2_MitModul\bin -m swingDemos/intro.ErstesFensterNeu

// das Hauptfenster von JFrame ableiten, 
public class ErstesFensterNeu extends JFrame {

	private static final long serialVersionUID = 1L;

	public ErstesFensterNeu() {
		super("Erstes Swingfenster neu (modular)");

		this.setSize(400, 300);

		setLayout(null);

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

		this.add(lblName);
		add(txtName);
		add(btnAction);
		add(lblInfo);

		// beim Button eine ActionListener registrieren
		// Statt anonymer Implementierung
//		btnAction.addActionListener(new ActionListener() {
//
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// den Namen aus der Textbox holen
//				String name = txtName.getText();
//				// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht
//				System.out.println("Dein Name: " + name);
//				lblInfo.setText("Dein Name: %s".formatted(name));
//			}
//		});

		// Lambda expression
		btnAction.addActionListener(

				(ActionEvent e) -> {
					// den Namen aus der Textbox holen
					String name = txtName.getText();
					// Ausgabe für Debug-Zwecke, der Enduser sieht das nicht
					System.out.println("Dein Name: " + name);
					lblInfo.setText("Dein Name: %s".formatted(name));
				});

		// das Fenster wirklich schließen wenn "Close" betätigt wird
		// weil es das Hauptfenster ist, wird damit das Praogramm beendet
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public static void main(String[] args) {
		// Swing mit eigener Hauptfensterklasse
		ErstesFensterNeu fenster = new ErstesFensterNeu();
//		JFrame fenster = new JFrame("Mein erstes Swingfenster");

		// das Fenster anzeigen, das Programm läuft weiter bis das Fenster geschlossen
		// wird
		fenster.setVisible(true);

	}

}

package intro2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class IntroFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;

	final JButton btnOpen, btnSaveAs, btnExit;

	final JList<String> lbEreignisse;
	final DefaultListModel<String> modelEreignisse;

	final static String ACTION_OPEN = " OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT";

	public IntroFenster2() {
		super("Filedialog etc.");
		setSize(400, 300);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		IntroHandler meinHandeler = new IntroHandler(this);

		// ein PAnel mit Flow-Layout erstellen
		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// Die Buttons erzeugen und einfügen
		// ...
		pnlButtons.add(btnOpen = new JButton("Öffnen"));
		btnOpen.setActionCommand(ACTION_OPEN);
		//statt Lambda Expression, die die Methode onOpen aufruft
		// btnOpen.addActionListener((ActionEvent e) -> meinHandeler.onOpen(e));
		// Als Actionlistener Methodenreferenz zu einer passenden Methode angeben
		btnOpen.addActionListener(meinHandeler::onOpen);

		// Speicher unter
		pnlButtons.add(btnSaveAs = new JButton("Speichern unter"));
		btnSaveAs.setActionCommand(ACTION_SAVEAS);
		btnSaveAs.addActionListener(meinHandeler::onSave);

		pnlButtons.add(btnExit = new JButton("Beenden"));
		btnExit.setActionCommand(ACTION_EXIT);
		btnExit.addActionListener(meinHandeler::onExit);

		// das Panel an passender Position einfügen
		add(pnlButtons, BorderLayout.NORTH);

		// in einem ScrollPane eine Listbox anzeigen
		// ein ListModel , das das Einfügen von Zeichenfolgen erlaubt
		modelEreignisse = new DefaultListModel<>();
		// die Listbox soll dieses ListModel verwenden
		lbEreignisse = new JList<>(modelEreignisse);
		JScrollPane scroll = new JScrollPane(lbEreignisse);
		add(scroll/* , BorderLayout.CENTER */);
		modelEreignisse.addElement("Fenster-Erzeugung abgeschlossen!");
//		lbEreignisse.getModel().

		// damit unsere Frage beim beenden angezeigt wird .´-> nichts automatisch
		// ausführen
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		// statdessen das close selber auslösen, wenn nötig ist
		// anonymer implementierung von WindowAdapter (Hilfsklasse fürs Implementieren
		// von WindowListener)
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				// beim Exit-Button einen Klick auslösen
				btnExit.doClick();
			}
		});

	}

	public static void main(String[] args) {

		new IntroFenster2().setVisible(true);

	}

}

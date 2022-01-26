package menues;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

public class IntroFenster2 extends JFrame {

	private static final long serialVersionUID = 1L;

//	final JButton btnOpen, btnSaveAs, btnExit;
	
	final JMenuItem miOpen, miSaveAs, miExit;
	

	final JList<String> lbEreignisse;
	final DefaultListModel<String> modelEreignisse;

	final static String ACTION_OPEN = " OPEN", ACTION_SAVEAS = "SAVEAS", ACTION_EXIT = "EXIT";

	public IntroFenster2() {
		super("Filedialog etc.");
		setSize(400, 300);
		getContentPane().setBackground(Color.LIGHT_GRAY);

		IntroHandler meinHandler = new IntroHandler(this);
		
		//statt button-Panel ein Menü hinzufügen
		JMenu mnuDatei = new JMenu("Datei");
		mnuDatei.setMnemonic(KeyEvent.VK_D);
		
//		miOpen = new JMenuItem("Öffnen");
//		mnuDatei.add(miOpen);
//		miOpen.addActionListener(meinHandler::onOpen);
		
		// Tastenkombination aus o bei gedrückter Steuerung-Taste (Ctrl+O)
		KeyStroke stroke = KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK);
		mnuDatei.add(miOpen = createMenuItem("Öffnen", ACTION_OPEN, KeyEvent.VK_F, stroke));
		miOpen.addActionListener(meinHandler::onOpen);

		mnuDatei.add(miSaveAs = createMenuItem("Speichern unter...", ACTION_SAVEAS, KeyEvent.VK_U, null));
		miSaveAs.addActionListener(meinHandler::onSave);
	
		mnuDatei.add(miExit = createMenuItem("Beenden", ACTION_EXIT, KeyEvent.VK_E, null));
		miExit.addActionListener(meinHandler::onExit);
		
		//...
		
		// die Menüleiste erzeugen
		JMenuBar menuBar = new JMenuBar();
		// das Datei-Menü hinzufügen
		menuBar.add(mnuDatei);
		// im Frame die Menüleiste anzeigen
		setJMenuBar(menuBar);
		
		
		
		
		
		

		/* // ein PAnel mit Flow-Layout erstellen
		JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		// Die Buttons erzeugen und einfügen
		// ...
		pnlButtons.add(btnOpen = new JButton("Öffnen"));
		btnOpen.setActionCommand(ACTION_OPEN);
		//statt Lambda Expression, die die Methode onOpen aufruft
		// btnOpen.addActionListener((ActionEvent e) -> meinHandeler.onOpen(e));
		// Als Actionlistener Methodenreferenz zu einer passenden Methode angeben
		btnOpen.addActionListener(meinHandler::onOpen);

		// Speicher unter
		pnlButtons.add(btnSaveAs = new JButton("Speichern unter"));
		btnSaveAs.setActionCommand(ACTION_SAVEAS);
		btnSaveAs.addActionListener(meinHandler::onSave);

		pnlButtons.add(btnExit = new JButton("Beenden"));
		btnExit.setActionCommand(ACTION_EXIT);
		btnExit.addActionListener(meinHandler::onExit);

		// das Panel an passender Position einfügen
		add(pnlButtons, BorderLayout.NORTH);*/

		
		
		
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
				// beim Exit-Menü-Item einen Klick auslösen
				miExit.doClick();
			}
		});

	}
	
	private JMenuItem createMenuItem(String text, String action, int mnemomic, KeyStroke shortcut) {
		JMenuItem mi = new JMenuItem(text, mnemomic);
		mi.setActionCommand(action);
		// Tastaturshortcut setzen, falls vorhanden
		if(shortcut != null) {
			mi.setAccelerator(shortcut);
		}
		return mi;
	}

	public static void main(String[] args) {

		new IntroFenster2().setVisible(true);

	}

}

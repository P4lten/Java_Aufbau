package editorSwingMichaela;

import java.awt.event.*;
import javax.swing.*;

/**
 * Fenster-Klasse mit Menü und JTextArea
 * 	 
 * @author Michaela
 *
 */
public class SimpleEditor2 extends JFrame {
	private static final long serialVersionUID = 1L;
	public static final String NEW = "NEW";
	public static final String OPEN = "OPEN";
	public static final String SAVE = "SAVE";
	public static final String SAVE_AS = "SAVEAS";
	public static final String EXIT = "EXIT";
	
	public static final String COPY = "COPY";
	public static final String PASTE = "PASTE";
	public static final String CUT = "CUT";
	
	
	private AbstractButton exitItem;
	

	/**
	 * initialisiert das hauptfenster für den Editor und zeigt es an
	 * @param fileName der name des anzuzeigenden Files oder null
	 */
	public SimpleEditor2(String fileName) {
		super("Unbenannt");
		this.setSize(600, 400);
		this.setLocation(200, 100);

		// TextArea mit diesem Document erzeugen
		JTextArea textArea = new JTextArea();
		// Text-Area in einer Scrollpane anzeigen
		this.add("Center", new JScrollPane(textArea));

		// Menü-Handler erzeugen
		MyActionHandler handler = new MyActionHandler(this, textArea);
		textArea.getDocument().addDocumentListener(handler);
		// Menü erzeugen
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("Datei");
		fileMenu.add(createMenuItem("Neu", NEW, handler, KeyEvent.VK_N, KeyStroke.getKeyStroke('N', InputEvent.CTRL_DOWN_MASK )));
		fileMenu.add(createMenuItem("Öffnen", OPEN, handler, KeyEvent.VK_O, KeyStroke.getKeyStroke('O', InputEvent.CTRL_DOWN_MASK )));
		fileMenu.add(createMenuItem("Speichern", SAVE, handler, KeyEvent.VK_S, KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK )));
		fileMenu.add(createMenuItem("Speichern unter", SAVE_AS, handler));
		fileMenu.add(exitItem = createMenuItem("Beenden", EXIT, handler));
		mb.add(fileMenu);

		
		JMenu editMenu = new JMenu("Bearbeiten");
		editMenu.add(createMenuItem("Kopieren", COPY, handler, KeyEvent.VK_K, KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK )));
		editMenu.add(createMenuItem("Einfügen", PASTE, handler, KeyEvent.VK_F, KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK )));
		editMenu.add(createMenuItem("Ausschneiden", CUT, handler, KeyEvent.VK_C, KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK )));
		mb.add(editMenu);

		this.setJMenuBar(mb);

		// anzeigen
		setVisible(true);

		
		try{
			// ggf File öffnen
			if(fileName != null)
				handler.openFile(fileName);
		} catch (Exception e) {
			JOptionPane.showConfirmDialog(this, e.getMessage(),
					"Fehler beim Öffnen", JOptionPane.DEFAULT_OPTION);
		}

		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				exitItem.doClick();
			}
		});
	}

	private JMenuItem createMenuItem(String text, String actionCommand,
			MyActionHandler handler, int mnemonic, KeyStroke accelerator) {
		JMenuItem item = createMenuItem(text, actionCommand, handler);
		item.setMnemonic(mnemonic);
		item.setAccelerator(accelerator);
		return item;
	}

	private JMenuItem createMenuItem(String text, String actionCommand,
			MyActionHandler handler) {
		JMenuItem item = new JMenuItem(text);
		item.setActionCommand(actionCommand);
		item.addActionListener(handler);
		return item;
	}
	
	public static void main(String[] args) {
		if (args.length > 0)
			new SimpleEditor2(args[0]);
		else
			new SimpleEditor2(null);
	}

}

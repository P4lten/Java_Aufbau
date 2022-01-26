package editorSwingMichaela;

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * Fenster-Klasse mit Menü und JTextArea
 * 
 * @author Michaela
 *
 */
public class SimpleEditor1 extends JFrame {
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
	 * 
	 * @param fileName der name des anzuzeigenden Files oder null
	 */
	public SimpleEditor1(String fileName) {
		super("Unbenannt");
		this.setSize(600, 400);
		this.setLocation(200, 100);

		// TextArea mit diesem Document erzeugen
		JTextArea textArea = new JTextArea();
		// Text-Area in einer Scrollpane anzeigen
		this.add("Center", new JScrollPane(textArea));

		// Action-Handler erzeugen
		MyActionHandler handler = new MyActionHandler(this, textArea);
		textArea.getDocument().addDocumentListener(handler);
		// Buttons erzeugen
		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(createButton("Neu", NEW, handler, KeyEvent.VK_N));
		buttonPanel.add(createButton("Öffnen", OPEN, handler, KeyEvent.VK_O));
		buttonPanel.add(createButton("Speichern", SAVE, handler, KeyEvent.VK_S));
		buttonPanel.add(createButton("Speichern unter", SAVE_AS, handler, 0));
		buttonPanel.add(exitItem = createButton("Beenden", EXIT, handler, 0));

		this.add("South", buttonPanel);

		// anzeigen
		setVisible(true);

		try {
			// ggf File öffnen
			if (fileName != null)
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

	private JButton createButton(String text, String actionCommand, MyActionHandler handler, int mnemonic) {
		JButton item = new JButton(text);
		item.setActionCommand(actionCommand);
		item.addActionListener(handler);
		item.setMnemonic(mnemonic);
		return item;
	}

	public static void main(String[] args) {
		if (args.length > 0)
			new SimpleEditor1(args[0]);
		else
			new SimpleEditor1(null);
	}
}

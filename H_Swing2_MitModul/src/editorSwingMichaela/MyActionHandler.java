package editorSwingMichaela;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

class MyActionHandler implements ActionListener, DocumentListener {
	private JFrame editorFenster;
	private JTextArea textArea;

	private JFileChooser fileDlg;

	private EditorDocument document = new EditorDocument();

	/**
	 * Action-Handler initialisieren
	 * 
	 * @param fenster das Fenster, dem der Handler zugeordnet ist
	 * @param doc     das EditorDokument, dem der Handler zugeordnet ist
	 */
	public MyActionHandler(JFrame fenster, JTextArea ta) {
		editorFenster = fenster;
		textArea = ta;
		fileDlg = new JFileChooser();
	}

	/**
	 * Verzweigt je nach augewähltem Befehl
	 */
	public void actionPerformed(ActionEvent ae) {
		if (ae.getActionCommand().equals(SimpleEditor1.NEW)) {
			doNew();
		} else if (ae.getActionCommand().equals(SimpleEditor1.OPEN)) {
			doOpen();
		} else if (ae.getActionCommand().equals(SimpleEditor1.SAVE)) {
			doSave();
		} else if (ae.getActionCommand().equals(SimpleEditor1.SAVE_AS)) {
			doSaveAs();
		} else if (ae.getActionCommand().equals(SimpleEditor1.EXIT)) {
			doExit();
		} 
		else if (ae.getActionCommand().equals(SimpleEditor1.COPY)) {
			doCopy();
		} else if (ae.getActionCommand().equals(SimpleEditor1.PASTE)) {
			doPaste();
		} else if (ae.getActionCommand().equals(SimpleEditor1.CUT)) {
			doCut();
		}
	}

	/**
	 * Handler für Befehl NEW
	 * 
	 * @return true, wenn alles ok
	 */
	private boolean doNew() {
		// ggf Speichern
		if (askForSave()) {
			// neues Dokument anzeigen
			newFile();
			// titel aktualisieren
			editorFenster.setTitle("Unbenannt");
			return true;
		} else
			// Speichern abgebrochen
			return false;

	}

	/**
	 * Handler für Befehl OPEN
	 * 
	 * @return true, wenn alles ok
	 */
	private boolean doOpen() {

		try {
			// ggf speichern
			if (askForSave()) {

				// Filenamen einlesen
				String filename = selectOpenFileName();
				// wenn vorhanden
				if (filename != null) {
					// das File anzeigen
					openFile(filename);
					// titel aktualisieren
					editorFenster.setTitle(filename);
					// true: es wurde ein File geöffnet
					return true;
				}
			}
		} catch (IOException e) {
			// Fehler ausgeben
			JOptionPane.showConfirmDialog(editorFenster, e.getMessage(),
					"Fehler beim Öffnen", JOptionPane.DEFAULT_OPTION);

		}
		return false;
	}

	/**
	 * Handler für Befehl SAVE
	 * 
	 * @return true, wenn alles ok
	 */
	private boolean doSave() {
		// kein Filename vorhanden
		if (document.getFileName() == null) {
			return doSaveAs(); // Speichern unter aufrufen
		} else if (document.isChanged()) {
			try {
				saveFile();
				return true;
			} catch (IOException e) {
				// Fehler ausgeben
				JOptionPane.showConfirmDialog(editorFenster, e.getMessage(),
						"Fehler beim Speichern", JOptionPane.DEFAULT_OPTION);
			}
		}
		return false;
	}

	/**
	 * Handler für Befehl SAVEAS
	 * 
	 * @return true, wenn alles ok
	 */
	private boolean doSaveAs() {
		// filenamen einlesen
		String filename = selectSaveFileName();
		if (filename != null) {
			try {
				document.setChanged(true);
				saveFile(filename);
				editorFenster.setTitle(filename);
				// alles gutgegangen
				return true;
			} catch (IOException e) {
				// Fehler anzeigen
				JOptionPane.showConfirmDialog(editorFenster, e.getMessage(),
						"Fehler beim Speichern unter ...",
						JOptionPane.DEFAULT_OPTION);
			}
		}
		return false;
	}

	/**
	 * Handler für Befehl EXIT
	 * 
	 * @return true, wenn alles ok
	 */
	private boolean doExit() {
		System.out.println("doExit");
		if (askForSave()) {
			// Hauptfenster schließen
			editorFenster.dispose();
			return true;
		} else
			return false;
	}

	/**
	 * Fragt ggf ob gespeichert werden soll
	 * 
	 * @return true, wenn fortgesetzt werden soll; false wenn abgebrochen wurde oder
	 *         ein Fehler aufgetreten ist
	 */
	private boolean askForSave() {

		if (document.isChanged()) {
			// Messagebox anzeigen
			int result = JOptionPane.showConfirmDialog(editorFenster,
					"Möchten Sie die Änderungen speichern?", "Editor",
					JOptionPane.YES_NO_CANCEL_OPTION);
			// je nach Benutzer-Auswahl
			switch (result) {
			case JOptionPane.YES_OPTION: // Ja
				return doSave(); // --> Speichern
			case JOptionPane.NO_OPTION: // Nein
				return true; // --> OK, true zurückliefern
			case JOptionPane.CANCEL_OPTION: // abbrechen:
			default:
				return false; // Abbruch -> false zurückliefern
			}
		} else { // unchanged
			return true;
		}
	}

	private void doCut() {
		textArea.cut();
	}

	private void doCopy() {
		textArea.copy();
	}

	private void doPaste() {
		textArea.paste();
	}

	/**
	 * File öffnen und den Inhalt in der Textarea anzeigen
	 * 
	 * @param file Name des Files, das geöffnet werden soll
	 * @throws IOException wenn das File nicht geöffnet oder gelesen werden kann
	 */
	public void openFile(String file) throws IOException {

		String text = document.open(file);
		// den Text in der Textarea setzen
		textArea.setText(text);
		// changed-Flag zurücksetzen
		document.setChanged(false);

	}

	/**
	 * speichert den Inhalt der Textarea in ein File
	 * 
	 * @param file Name des Files, das gespeichert werden soll
	 * @throws IOException wenn das File nicht geöffnet oder geschrieben werden kann
	 */
	public void saveFile(String file) throws IOException {

		document.save(textArea.getText(), file);
	}

	/**
	 * speichert den Inhalt der Textarea in das File, das zuvor beim Öffnen oder
	 * Speichern angegeben wurde
	 * 
	 * @throws IOException wenn das File nicht geöffnet oder geschrieben werden kann
	 */
	public void saveFile() throws IOException {
		document.save(textArea.getText());
	}

	/**
	 * setzt den Inhalt der Textarea und den Filenamen zurück
	 */
	public void newFile() {
		document.reset();
		textArea.setText("");
	}

	// Methoden aus DocumentListener implementieren um Änderungen in der Textarea zu
	// registrieren
	@Override
	public void changedUpdate(DocumentEvent e) {
		document.setChanged(true);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		document.setChanged(true);

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		document.setChanged(true);

	}

	private String selectOpenFileName() {
		int erg = fileDlg.showOpenDialog(editorFenster);
		// wenn der User den OK-Button geklickt hat
		if (erg == JFileChooser.APPROVE_OPTION) {
			// das File, das der User ausgewählt hat, holen
			File file = fileDlg.getSelectedFile();
			if (file.exists()) {
				// jetzt könnten wir das File öffnen und lesen
				System.out.println("Ausgewähltes File: " + file.getAbsolutePath());
				return file.getAbsolutePath();

			} else {
				System.out.println("Der Benutzer hat OK gelickt, aber kein existierendes File ausgewählt");
				// hier könnte man dem User eine Meldung anzeigen und die
				// Methode rekursiv wieder aufrufen
				return selectOpenFileName();
			}
		}
		return null;
	}

	private String selectSaveFileName() {
		int erg = fileDlg.showSaveDialog(editorFenster);
		if (erg == JFileChooser.APPROVE_OPTION) {
			File file = fileDlg.getSelectedFile();
			if (file.exists()) {
				System.out.println("Der Benutzer möchte ein existierendes File überschreiben");
				// hier könnte man dem User eine Meldung anzeigen und die
				// Methode rekursiv wieder aufrufen
			}

			System.out.println("Ausgewähltes File zum Speichern: " + file.getAbsolutePath());
			return file.getAbsolutePath();
		}
		return null;
	}

}

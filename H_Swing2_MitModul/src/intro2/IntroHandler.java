package intro2;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class IntroHandler {

	private IntroFenster2 meinFenster;

	private JFileChooser fileDialog;

	public IntroHandler(IntroFenster2 fenster) {
		meinFenster = fenster;
		// File-Auswahldialog mit dem akt. Verzeichnis aus Ausgangspunkt
		fileDialog = new JFileChooser(".");
	}

	public void onOpen(ActionEvent evt) {
		System.out.println("onOpen, command = " + evt.getActionCommand());
		String selFile = selectOpenFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			// Info in der Listbox hinzufügen
			meinFenster.modelEreignisse.addElement("File öffnen" + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt");
		}
	}

	public void onSave(ActionEvent evt) {
		System.out.println("onSave, command = " + evt.getActionCommand());
		String selFile = selectSaveFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			meinFenster.modelEreignisse.addElement("File speichern" + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt");
		}
	}

	public void onExit(ActionEvent evt) {
		System.out.println("oEnxit,command = " + evt.getActionCommand());
		int auswahl = JOptionPane.showConfirmDialog(meinFenster, "Möchtest du wirklich beenden?", "Beenden",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (auswahl == JOptionPane.YES_OPTION) {
			// das Hauptfenster schließen
			meinFenster.dispose();
		} else {
			System.out.println("Beenden abgebrochen");
			meinFenster.modelEreignisse.addElement("Beenden wurde abgebrochen");
		}
	}

	private String selectOpenFilename() {
		int auswahl = fileDialog.showOpenDialog(meinFenster);
		// wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt " + file.getAbsolutePath());
			if (file.exists()) {
				// als absoluten Pfad zurückliefern
				int auswahl2 = JOptionPane.showConfirmDialog(meinFenster,
						"Das Fil existiert bereits, möchtest du es Überschreiben?", "Speichern",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (auswahl2 == JOptionPane.YES_OPTION) {
					System.out.println("File existiert, der Benutzer möchte das File überschreiben ");
					return file.getAbsolutePath();
				} else {
					// methode rekursiv aufrufen
					return selectSaveFilename();

				}
			} else {
				// FIle existiert nicht -> Fehler anzeigen und Methode rekursiv aufrufen
				JOptionPane.showConfirmDialog(meinFenster, "Das File existiert nicht, bitte wähle ein anderes",
						"Öffnen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return selectOpenFilename();
			}
		} else {
			// es wurde Abbrechen gedrückt
			System.out.println("Der Benutzer hat Abbrechen gedrückt");
			return null;// kein File ausgewählt
		}
	}

	private String selectSaveFilename() {
		int auswahl = fileDialog.showSaveDialog(meinFenster);
		// wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt " + file.getAbsolutePath());
			// als absoluten Pfad zurückliefern
			return file.getAbsolutePath();
		} else {
			// es wurde Abbrechen gedrückt
			System.out.println("Der Benutzer hat Abbrechen gedrückt");
			return null;// kein File ausgewählt
		}
	}

}

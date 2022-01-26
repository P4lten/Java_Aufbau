package editorswing;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EditorHandler {

	private EditorSwing meinFenster;

	private JFileChooser fileDialog;

	public EditorHandler(EditorSwing fenster) {
		meinFenster = fenster;
		fileDialog = new JFileChooser(".");
	}

	public void onOpen(ActionEvent evt) {
		System.out.println("onOpen, command = " + evt.getActionCommand());
		String selFile = selectOpenFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);
			meinFenster.setTitle(selFile);

			// warum funktioniert das if Statement nicht???
			if (meinFenster.textArea.getText().isEmpty()) {
				try (BufferedReader reader = new BufferedReader(new FileReader(selFile, Charset.forName("UTF-8")))) {
					String line;
					String content = " ";
					while ((line = reader.readLine()) != null) {
						meinFenster.textArea.append(line + "\n");
						content += line;
					}
					System.out.println("Gelesen:");
					System.out.println(content);

				} catch (Exception e) {
					System.out.println("Fehler beim Einlesen" + e);
				}
				meinFenster.modelEreignisse.addElement("File öffnen" + selFile);
			} else {
				int auswahl = JOptionPane.showConfirmDialog(meinFenster,
						"Dokument wurde noch nicht gespeichert, trotzdem öffnen?", "Öffnen", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (auswahl == JOptionPane.YES_OPTION) {
					// das Hauptfenster schließen
					meinFenster.textArea.setText(null);
					try (BufferedReader reader = new BufferedReader(
							new FileReader(selFile, Charset.forName("UTF-8")))) {
						String line;
						String content = " ";
						while ((line = reader.readLine()) != null) {
							meinFenster.textArea.append(line + "\n");
							content += line;
						}
						System.out.println("Gelesen:");
						System.out.println(content);

					} catch (Exception e) {
						System.out.println("Fehler beim Einlesen" + e);
					}

					meinFenster.modelEreignisse.addElement("File öffnen" + selFile);
				} else {
					System.out.println("Öffnen abgebrochen");
					meinFenster.modelEreignisse.addElement("Öffnen wurde abgebrochen");
				}
			}

		} else {
			System.out.println("Es wurde kein File ausgewählt");
		}
	}

	public void onSaveAs(ActionEvent evt) {
		System.out.println("onSaveAs, command = " + evt.getActionCommand());
		String selFile = selectSaveFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(selFile))) {
				meinFenster.setTitle(selFile);
				String line;
				if ((line = meinFenster.textArea.getText()) != null && !line.isEmpty()) {
					// die Zeile ins File schreiben
					writer.write(line);
					// einen Zeilenumbruch schreiben
					writer.newLine();
				}
			} catch (IOException e) {
				System.out.println("Fehler beim Speichern: " + e);
			}
			meinFenster.modelEreignisse.addElement("File speichern" + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt");
		}
	}

	public void onSaveAt(ActionEvent evt) {
		System.out.println("onSaveAat, command = " + evt.getActionCommand());
		String selFile = selectSaveFilename();
		if (selFile != null) {
			System.out.println("File ausgewählt: Pfad =" + selFile);

			try (BufferedWriter writer = new BufferedWriter(new FileWriter(selFile))) {
				meinFenster.setTitle(selFile);
				String line;
				if ((line = meinFenster.textArea.getText()) != null && !line.isEmpty()) {
					// die Zeile ins File schreiben
					writer.write(line);
					// einen Zeilenumbruch schreiben
					writer.newLine();
				}
			} catch (IOException e) {
				System.out.println("Fehler beim Speichern: " + e);
			}
			meinFenster.modelEreignisse.addElement("File speichern" + selFile);
		} else {
			System.out.println("Es wurde kein File ausgewählt");
		}
	}

	public void onNew(ActionEvent evt) {
		System.out.println("onNew, command = " + evt.getActionCommand());
		
		int auswahl = JOptionPane.showConfirmDialog(meinFenster, "Möchtest du wirklich ein neues Document erstellen?", "Neu",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (auswahl == JOptionPane.YES_OPTION) {
			// das Hauptfenster schließen
			meinFenster.textArea.setText(null);
		} else {
			System.out.println("Neu erstellen abgebrochen");
			meinFenster.modelEreignisse.addElement("Neu erstellen wurde abgebrochen");
		}
		

	}

	public void onExit(ActionEvent evt) {
		System.out.println("onExit,command = " + evt.getActionCommand());
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
			System.out.println("Der Benutzer hat ein File ausgewählt: " + file.getAbsolutePath());
			// wenn das File existiert -> zurückliefern
			if (file.exists()) {
				// als absoluten Pfad zurückliefern
				return file.getAbsolutePath();
			} else {
				// File exitiert nicht-> Fehler anzeigen und Methode rekursiv aufrufen
				JOptionPane.showConfirmDialog(meinFenster, "Das File existiert nicht, bitte wähle ein anderes",
						"Öffnen", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				return selectOpenFilename();
			}
		} else {
			// es wurde Abbrechen gedrückt
			System.out.println("Der Benutzer hat Abbrechen gedrückt");
			return null; // kein File ausgewählt
		}
	}

	private String selectSaveFilename() {
		int auswahl = fileDialog.showSaveDialog(meinFenster);
		// wenn der Dialog mit OK beendet wurde
		if (auswahl == JFileChooser.APPROVE_OPTION) {
			File file = fileDialog.getSelectedFile();
			System.out.println("Der Benutzer hat ein File ausgewählt: " + file.getAbsolutePath());
			// wenn es nicht existiert -> zurückliefern
			if (!file.exists()) {
				// als absoluten Pfad zurückliefern
				return file.getAbsolutePath();
			} else {
				int auswahl2 = JOptionPane.showConfirmDialog(meinFenster,
						"Das File existiert bereits, möchtest du es überschreiben?", "Speichern",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (auswahl2 == JOptionPane.YES_OPTION) {
					System.out.println("File existiert, der Benutzer möchte das File überschreiben");
					return file.getAbsolutePath();
				} else {
					// Methode rekursiv aufrufen
					return selectSaveFilename();
				}
			}
		} else {
			// es wurde Abbrechen gedrückt
			System.out.println("Der Benutzer hat Abbrechen gedrückt");
			return null; // kein File ausgewählt
		}
	}
	
}

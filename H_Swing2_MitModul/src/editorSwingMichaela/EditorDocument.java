package editorSwingMichaela;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EditorDocument {
	private boolean changed = false;
	private String fileName = null;

	public boolean isChanged() {
		return changed;
	}

	public void setChanged(boolean c) {
		changed = c;
	}

	public String getFileName() {
		return fileName;
	}

	public String open(String file) throws IOException {
		// kein Filename oder das File existiert nicht
		if (!new File(file).exists()) {
			throw new FileNotFoundException("Das File " + file + " existiert nicht!");
		}

		// Namen merken
		fileName = file;

		final int MAX_BUFFER = 1024;
		char[] buffer = new char[MAX_BUFFER];
		int size;

		// File öffnen
		try (FileReader aFileReader = new FileReader(file)) {

			StringBuilder sbText = new StringBuilder(256);

			// portionsweise lesen und im Stringbuilder einfügen
			while ((size = aFileReader.read(buffer, 0, MAX_BUFFER)) > 0) {
				sbText.append(buffer, 0, size);
			}
			return sbText.toString();
		}
	}

	public void save(String text, String file) throws IOException {
		// neuen Filenamen merken
		fileName = file;
		// speichern
		save(text);

	}

	public void save(String text) throws IOException {
		if (fileName == null) {
			throw new FileNotFoundException("Der Filename darf nicht null sein!");
		}
		if (isChanged()) {
			// File öffnen
			FileWriter aFileWriter = new FileWriter(fileName);
			// den gesamten Text schreiben
			aFileWriter.write(text);
			// Writer schließen
			aFileWriter.close();
			setChanged(false);
		}

	}

	public void reset() {
		fileName = null;
		setChanged(false);

	}
}

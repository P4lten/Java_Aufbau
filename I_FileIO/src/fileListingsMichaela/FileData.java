package fileListingsMichaela;

import java.time.Instant;

/**
 * die Klasse kapselt die erforderlichen Informationen für ein File
 * @author Michaela
 *
 */
public class FileData {

	// Attribute für name, path, size und lastModified
	private final String name, path;

	private final long size;

	private final Instant lastModified;

	/**
	 * passender Konstruktor mit dem alle Attribute initialisiert werden 
	 * @param name
	 * @param path
	 * @param size
	 * @param lastModified
	 */
	public FileData(String name, String path, long size, Instant lastModified) {
		super();
		this.name = name;
		this.path = path;
		this.size = size;
		this.lastModified = lastModified;
	}

	/**
	 * getter für den Namen
	 */
	public String getName() {
		return name;
	}

	/**
	 * getter für den Pfad
	 */
	public String getPfad() {
		return path;
	}

	/**
	 * getter für die Größe 
	 */
	public long getSize() {
		return size;
	}

	/**
	 * getter für das Datum der letzen Änderung
	 */
	public Instant getLastModified() {
		return lastModified;
	}

	// toString überschreiben
	@Override
	public String toString() {

		return String.format("%-25s - %10d Byte - %s - %s", name, size, lastModified, path);
	}
}

package fileListingsMichaela;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * die Klasse gruppiert die Files eines Verzeichnisses (samt
 * Unterverzeichnissen) nach der File-Extension und unterstützt statistische
 * Auswertungen nach Extension.
 * 
 * @author Michaela
 *
 */
public class FileStatistics {
	// passende Zuordnung für File-Extension zu Liste von Filenamen
	// statt einzelner Listen
	// private List<FileData> textFiles , javaFiles, classFiles;
	private Map<String, List<FileData>> dataMap = new TreeMap<>();

	/**
	 * das Objekt mit dem angegebenen Verzeichnis initialisieren. Alle Files des
	 * Verzeichnisses werden rekursiv nach Extension gruppiert
	 * 
	 * @param dirName
	 */
	public FileStatistics(String dirName) {
		// rekursiv das Verzeichnis verarbeiten
		readData(new File(dirName));
	}

	// ein Verzeichnis verarbeiten
	private void readData(File dir) {
		// alle Files im Verzeichnis auflisten
		File[] files = dir.listFiles();
		// alle Files verarbeiten
		for (File file : files) {
			// wenn es ein File ist
			if (file.isFile()) {
				String fileName = file.getName();
				// 1) Extension herausfinden
				int index = fileName.lastIndexOf('.');
				String ext = index >= 0 ? fileName.substring(index) : "";

				// 2) wenn die Datamap noch keine Liste für die Extension enthält ->
				if (!dataMap.containsKey(ext)) {

					// neue( leere) Liste hinzufügen
					dataMap.put(ext, new ArrayList<>());
				}

				// 3) in der Liste für die Extension den Filenamen hinzufügen
				FileData fd = new FileData(fileName, file.getAbsolutePath(), file.length(),
						Instant.ofEpochMilli(file.lastModified()));
				dataMap.get(ext).add(fd);
			}

			// sonst: wenn es ein Verzeichnis ist
			else if (file.isDirectory()) {
				// rekursiv aufrufen
				readData(file);
			}
		}
	}

	/**
	 * Alle Files von allen Extensions anzeigen
	 */
	public void showAll() {
		System.out.println("Alle Extensions:");
		// für alle Extensions
		for (String ext : dataMap.keySet()) {
			// alle Files der Extension anzeigen
			showFiles(ext);
		}
		System.out.println();
	}

	/**
	 * alle Files der angegebenen Extension anzeigen
	 * 
	 * @param ext die Extension
	 */
	public void showFiles(String ext) {

		// wenn die Extension nicht existiert -> Fehler anzeigen und raus
		if (!dataMap.containsKey(ext)) {
			System.out.printf("Extension %s: \n", ext);
			System.out.printf("\tEs gibt keine Files mit dieser Extension \n");
			return;
		}

		System.out.printf("Extension %s: \n", ext);
		// die File-Liste abrufen
		List<FileData> files = dataMap.get(ext);
		// ... und alle Files anzeigen
		for (FileData path : files) {
			System.out.printf("\t%s\n", path);
		}

	}

	/**
	 * Die Anzahl der Files pro Extension anzeigen
	 */
	public void showFileCount() {
		System.out.println("Anzahl Files pro Extension:");
		for (String ext : dataMap.keySet()) {
			System.out.printf("%-12s %d File(s)\n", ext, dataMap.get(ext).size());
		}
		System.out.println();
	}

	/**
	 * ältestes und neuestes File pro Extension anzeigen
	 */
	public void showNewestOldest() {
		System.out.println("Ältestes/neuestes File pro Extension");
		for (String ext : dataMap.keySet()) {
			showNewestOldest(ext);
		}
		System.out.println();
	}

	/**
	 * das älteste und neueste File der angegebenen Extension anzeigen
	 * 
	 * @param ext die Extension
	 */
	public void showNewestOldest(String ext) {
		// wenn die Extension nicht existiert -> Fehler anzeigen und raus
		if (!dataMap.containsKey(ext)) {
			System.out.printf("Extension %s: \n", ext);
			System.out.printf("\tEs gibt keine Files mit dieser Extension \n");
			return;
		}

		List<FileData> files = dataMap.get(ext);
		FileData minFile = null, maxFile = null;

		// FileData mit min und max Datum suchen
//		for (FileData fileData : files) {
//			if (minFile == null || fileData.getLastModified().isBefore(minFile.getLastModified())) {
//				minFile = fileData;
//			}
//			if (maxFile == null || fileData.getLastModified().isAfter(maxFile.getLastModified())) {
//				maxFile = fileData;
//			}
//		}

		// statt mit Schleife mit min/max aus Stream API
		minFile = files.stream().min(Comparator.comparing(FileData::getLastModified)).orElse(null);
		maxFile = files.stream().max(Comparator.comparing(FileData::getLastModified)).orElse(null);

		// Ergebnis anzeigen
		System.out.printf("Extension %s: \n", ext);
		System.out.printf("\tÄltestes File: %s\n", minFile);
		System.out.printf("\tJüngstes File: %s\n", maxFile);

	}

	public void showExitWithMostFiles() {
		System.out.println("Extension mit den meisten Files");

		// Comparator, der die extensions nach File-Anzahl sortiert
		Comparator<String> cmp = Comparator.comparing(ext -> dataMap.get(ext).size());
		// das größte Element gemäß diesem Comparatpr holen
		dataMap.keySet().stream().max(cmp).ifPresentOrElse(
				ext -> System.out.printf("\t%s (%d Files)\n", ext, dataMap.get(ext).size()),
				() -> System.out.println("\tKeine Files vorhanden"));
	}

	public void showExitWithMostFiles2() {

		System.out.println("Extension mit den meisten Files (2): ");
		// Temp Map mit Extensions (string zu Anzahl (int)
		Map<String, Integer> tmpMap = new HashMap<>();
		for (String ext : dataMap.keySet()) {
			tmpMap.put(ext, dataMap.get(ext).size());
		}
		// Alternative mit Stream API
		Map<String, Integer> tmpMap2 = dataMap.keySet().stream().collect(Collectors.toMap(
				// Die Extension ist auch der neue Key
				ext -> ext,
				// die Anzahl ist der Value der neuen Map
				ext -> dataMap.get(ext).size()));

		// tmpMap.keySet().stream().max((ext1, ext2) -> Integer.compare(, 0))
		tmpMap2.entrySet().stream().max((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue())).ifPresentOrElse(
				e -> System.out.printf("\t%s (%d Files)\n", e.getKey(), e.getValue()),
				() -> System.out.println("\tKeine Files vorhanden"));
	}

}

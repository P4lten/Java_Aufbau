package fileListingsMichaela;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileNameStatistics {
	// passende Zuornung für File-Extension zu Liste von Filenamen
	// statt einzelner Listen
	// private List<String> textFiles , javaFiles, classFiles;
	private Map<String, List<String>> dataMap = new TreeMap<>();

	public FileNameStatistics(String dirName) {
		// TODO: rekursiv das Verzeichnis verarbeiten
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
				String filePath = file.getAbsolutePath();
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
				dataMap.get(ext).add(filePath);
			}

			// sonst: wenn es ein Verzeichnis ist
			else if (file.isDirectory()) {
				// rekursiv aufrufen
				readData(file);
			}
		}
	}

	public void showAll() {
		System.out.println("Alle Extensions:");
		// für alle Extensions 
		for (String ext : dataMap.keySet()) {
			// alle Files der Extension anzeigen
			showFiles(ext);
		}
	}

	public void showFiles(String ext) {
		if (!dataMap.containsKey(ext)) {
			System.out.printf("Keine File zu Extension %s vorhanden\n", ext);
			return;
		}
		System.out.println(ext);
		// die File-Liste abrufen
		List<String> files = dataMap.get(ext);
		// ... und alle Files anzeigen
		for (String path : files) {
			System.out.printf("\t%s\n", path);
		}
	}

	public void showSizes() {
		System.out.println("Alle Extensions:");
		for (String ext : dataMap.keySet()) {
			System.out.printf("%-12s %d File(s)\n", ext, dataMap.get(ext).size());
		}
		System.out.println();
	}


}

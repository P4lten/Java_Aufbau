package fileListingsSmallChangesMP;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileStatistics {

	// passende zuordungn für File-Extension zu Liste von Filenamen
	// statt einzelner Listen
	// private List<String> textFiles, javaFiles, classFiles;

	private Map<String, List<FileData>> dataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	public FileStatistics(String dirName) {
		// TODO: rekursiv das Verzeichnis verarbeiten
		readData(new File(dirName));
	}

	// ein Verzeichnis verarbeiten
	// Michaela: die Logik ist absolut korrekt, nur der Datentyp passt noch nicht
	private void readData(File dir) {
		// TODO: alle Files in dem Vereichnis auflisten
		File[] files = dir.listFiles();

		// wenn es ein File ist:
		for (File file : files) {
			if (file.isFile()) {
				// 1 Extension herausfinden
				String extension = getExtension(file);
				String name = file.getName();
				// 2 wenn die Datamap noch keine Liste für die Extension enthält -> neue (leere)
				// Liste hinzufügen
				if (dataMap.get(extension) == null) {
					// Michaela: FileData statt String
					dataMap.put(extension, new ArrayList<FileData>());
				}
				// 3 ein FileData Objekt aus den File-Informationen erzeugen und
				// in der Liste für die Extension hinzufügen
				// Michaela: FileData-Objekt statt String
				dataMap.get(extension).add(new FileData(name, file.getAbsolutePath(), file.length(),
						Instant.ofEpochMilli(file.lastModified())));
			} else {
				// sonst: wenn es ein Verzeichnis ist
				readData(file);
			}
		}

	}

	public void showAll() {
		// Michaela: Daten auch anzeigen ;-) 
		for (String ext : dataMap.keySet()) {
			System.out.println(ext);
			List<FileData> files = dataMap.get(ext);
			for (FileData fileData : files) {
				System.out.println(fileData);
			}
			System.out.println();
		}

	}

	public void showSizes() {

	}

	public void showOldest() {

	}

//	public void showFiles(String ext) {
//		
//		for (String ext1 : dataMap.keySet()) {
//			FileData minFile = null, maxFile =null;
//			List<FileData> files = dataMap.get(ext1);
//			
//			for (FileData fileData : files) {
//				if(minFile == null || fileData.getLastModified().isBefore(minFile.getLastModified())) {
//					minFile = fileDate;
//				}
//			}
//		}
//	}

	private String getExtension(File file) {
		String name = file.getName();
		int lastIndex = name.lastIndexOf('.');
		if (lastIndex == -1) {
			return "";
		}
		return name.substring(lastIndex);
	}

}

package fileListings.preperation;

import java.io.File;
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

	}

//	// ein Verzeichnis verarbeiten
//	private void readData(File dir) {
//		// TODO: alle Files in dem Vereichnis auflisten
//		File[] files = dir.listFiles();
//
//		// wenn es ein File ist:
//		for (File file : files) {
//			if (file.isFile()) {
//				// 1 Extension herausfinden
//				String extension = getExtension(file);
//				String name = file.getName();
//				// 2 wenn die Datamap noch keine Liste für die Extension enthält -> neue (leere)
//				// Liste hinzufügen
//				if (dataMap.get(extension) == null) {
//					dataMap.put(extension, new ArrayList<String>());
//				}
//				// 3 ein FileData Objekt aus den File-Informationen erzeugen und
//				// in der Liste für die Extension hinzufügen
//				dataMap.get(extension).add(name);
//			} else {
//				// sonst: wenn es ein Verzeichnis ist
//				readData(file);
//			}
//		}
//
//	}

	public void showAll() {
		for (String ext : dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
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

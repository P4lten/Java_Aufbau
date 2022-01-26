package fileListings.preperation;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FileNameStatistics {

	// passende zuordungn für File-Extension zu Liste von Filenamen
	// statt einzelner Listen
	// private List<String> textFiles, javaFiles, classFiles;

	private Map<String, List<FileData>> dataMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

	public FileNameStatistics(String dirName) {
		// TODO: rekursiv das Verzeichnis verarbeiten
		File file = new File(dirName);
		this.readData(file);
		this.showAll();
		

	}

	// ein Verzeichnis verarbeiten
	private void readData(File dir) {
		// TODO: alle Files in dem Vereichnis auflisten
		File[] files = dir.listFiles();

		// wenn es ein File ist:
		for (File file : files) {
			if (file.isFile()) {
				// 1 Extension herausfinden
				String extension = getExtension(file);
			
				// 2 wenn die Datamap noch keine Liste für die Extension enthält -> neue (leere)
				// Liste hinzufügen
				if(dataMap.get(extension) == null) {
					dataMap.put(extension, new ArrayList<FileData>());
				}
				// 3 in der Liste für die Extension den Filenamen hinzufügen
				FileData fileNew = new FileData(file.getName(), file.getAbsolutePath(), file.length(), Instant.ofEpochMilli(file.lastModified()));
				dataMap.get(extension).add(fileNew);
			} else {
				// sonst: wenn es ein Verzeichnis ist
				readData(file);
			}
		}

	}

	public void showAll() {
		for (String ext: dataMap.keySet()) {
			List<FileData> files = dataMap.get(ext);
			System.out.println(files);
		}
		
	}
	
	private String getExtension (File file) {
		String name = file.getName();
		int lastIndex = name.lastIndexOf('.');
		if (lastIndex == -1) {
			return "";
		}
		return name.substring(lastIndex);
	}
		
		
		
	
	
		
	


}

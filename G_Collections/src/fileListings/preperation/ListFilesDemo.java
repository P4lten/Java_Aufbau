package fileListings.preperation;

import java.io.File;
import java.time.Instant;

public class ListFilesDemo {

	public static void main(String[] args) {
		String dirName = args.length == 0 ? "." :args[0];
		
		
		showContent(new File(dirName));

	}
	
	static void showContent(File dir) {
		System.out.printf("Verzeichnis %s:\n", dir.getAbsolutePath());
		
		File[] files = dir.listFiles();
		for (File file : files) {
			// wenn es ein File ist
			if (file.isFile()) {
				String filePath = file.getAbsolutePath();
				
				String name = file.getName();
				
				long size = file.length();
				long lastModiefiedMS = file.lastModified();
				Instant lastModiefied = Instant.ofEpochMilli(lastModiefiedMS);
				
				System.out.printf("\tFile %s - %d Byte - %s - %s\n",name, size, lastModiefied, filePath);
			} else if(file.isDirectory()) {
				// System.out.printf("\tUnterverzeichnis %s\n", file.getName());
				// rekursiv aufrufen
				showContent(file);
			}
			
		}
	}

}

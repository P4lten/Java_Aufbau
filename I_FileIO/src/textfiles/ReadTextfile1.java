package textfiles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.charset.Charset;

public class ReadTextfile1 {

	public static void main(String[] args) {
		
		// liefert nicht den korrekten Inhalt (wegen Encoding)
		// blockweiseLesen("Textfile1.txt");
		
		zeilenweiseLesen("Textfile1.txt");
		
	}

	static void blockweiseLesen(String filename) {
		try (FileReader reader = new FileReader(filename, Charset.forName("UTF-8"))) {
			char[] buffer = new char[16]; // Sehr klein, damit die Schleife mehrmals ausgeführt wird
			int count;
			// Speicherplatz für das Gelesene
			StringBuilder content = new StringBuilder();
			while ((count = reader.read(buffer)) > 0) {
				System.out.printf("%d Zeichen gelesen\n", count);
				// die Zeichen im StringBuilder anhängen
				content.append(buffer, 0,count);
			}
			
			// alles gelesen -> Ausgeben
			System.out.println("Vom File gelesen: ");
			System.out.println(content.toString());
			
		} catch (Exception e) {
			System.out.println("Fehler beim Einlesen" + e);
		}
	}

	static void zeilenweiseLesen(String filename) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename, Charset.forName("UTF-8")))){
			String line;
			String content = " ";
			// null == EOF (end of Line)
			while((line = reader.readLine()) != null) {
				System.out.println("Zeile: " + line);
				content += line;
			}
			
			System.out.println("Gelesen:" );
			System.out.println(content);
		} catch (Exception e) {
			System.out.println("Fehler beim Einlesen" + e);
		}
		
	}
}

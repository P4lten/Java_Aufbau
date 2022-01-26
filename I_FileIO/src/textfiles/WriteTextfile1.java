package textfiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Scanner;

 /*
  * wenn ein Java Programm 
  */


public class WriteTextfile1 {
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		
		mitZeilenUmbrueche();
		// scanner schließen
		input.close();
	}

	static void ohneZeilenUmbrueche() {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");
		try {
			FileWriter writer = new FileWriter("Textfile1.txt");
			String line;
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				writer.write(line);
			}
			// den writer schließen
			writer.close();
		} catch (Exception e) {
			System.out.println("Fehler beim Speichern: " + e);
		}
	}

	static void mitZeilenUmbruecheAlt() {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");
		BufferedWriter writer = null;
		try {
//			FileWriter fwriter = new FileWriter("Textfile1.txt");
			writer = new BufferedWriter(new FileWriter("Textfile1.txt", Charset.forName("UTF-8")));
			String line;
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				// die Zeile ins File schreiben
				writer.write(line);
				// einen Zeilenumbruch schreiben
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
			if (writer != null) {
				try {
					// den writer schließen
					writer.close();
				} catch (Exception d) {
					d.printStackTrace();
				}
			}
		}

	}
	
	static void mitZeilenUmbrueche() {

		System.out.println("Text zeilenweise eingeben, Beenden mit Leerzeile");
		
		try(BufferedWriter writer = new BufferedWriter(new FileWriter("Textfile1.txt"))) {
			
//			FileWriter fwriter = new FileWriter("Textfile1.txt");
			String line;
			while ((line = input.nextLine()) != null && !line.isEmpty()) {
				// die Zeile ins File schreiben
				writer.write(line);
				// einen Zeilenumbruch schreiben
				writer.newLine();
			}
			// nicht nötig wird vom Compiler aufgerufen
			// writer.close();
		} catch (IOException e) {
			System.out.println("Fehler beim Speichern: " + e);
			
		}

	}
}
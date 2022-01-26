package bankverwaltung;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;

public class BankverwaltungMain {

	
	private static Bankverwaltung bkVerwaltung = new Bankverwaltung("Bankverwaltung.dat", "Transaktionsliste.dat");
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		try {
			File fzFile = new File("Bankverwaltung.dat");
			File fzFile2 = new File("Transaktionsliste.dat");
			//File nicht vorhanden?
			if (!fzFile.exists() || !fzFile2.exists()) {
				// Erst-speicherung
				initalSaveData();
				initalSaveDataList();
			} else {
				// Sonst: Laden, anzeigen, usw...
				bkVerwaltung.loadData();
				bkVerwaltung.loadData2();
				bkVerwaltung.alleAnzeigen();
				bkVerwaltung.anzeigenListe();
				bkVerwaltung.anmelden(new Sparbuch("Peter Altenberg", LocalDate.now(), 0, 0.5f));
				System.out.println("Nach anmelden Peter: ");
				bkVerwaltung.alleAnzeigen();
				bkVerwaltung.saveData();
				
			}
		} catch (ClassNotFoundException | IOException e) {
			System.out.println("Es ist ein Fehler aufgetreten: ");
			e.printStackTrace();
		}
		
		System.out.println("Liste");
		
		bkVerwaltung.einzahlen(2, 10000);
		bkVerwaltung.abheben(2, 500);
		bkVerwaltung.ueberweisung(2, 1, 1000);

		bkVerwaltung.anzeigenListe();
		System.out.println();
		
//		bkVerwaltung.alleAnzeigen();
//		bkVerwaltung.kontoInfo(2);
//		bkVerwaltung.schließen(1);
//		bkVerwaltung.kontoInfo(2);
//		System.out.println();
//		bkVerwaltung.alleAnzeigen();
	}

	private static void initalSaveData() throws IOException {

		bkVerwaltung.anmelden(new Sparbuch("Kurt Bauer", LocalDate.of(2019, 12, 15), 0, 0.4f));
		bkVerwaltung.anmelden(new Gehaltskonto("Johannes Leitner", LocalDate.of(2020, 10, 2), 500, 15000));
		bkVerwaltung.anmelden(new Gehaltskonto("Alexander Weiß", LocalDate.of(2020, 5, 21), 1000, 20000));

		System.out.println("Folgende Bankkonten werden gespeichert");
		bkVerwaltung.alleAnzeigen();

		bkVerwaltung.saveData();

	}
	
	private static void initalSaveDataList() throws IOException{
		
		bkVerwaltung.saveData2();
	}

	private static Bankkonto readData(String fileName) {
		// Objekte einlesen
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

			// das Bankkonto-Objekt lesen
			Object obj1 = ois.readObject();
			if (!(obj1 instanceof Bankkonto)) {
				System.out.println("Ungültiges File, erwartet wurde ein Bankkonto-Object");
				return null;
			}

			Bankkonto bankkontoVerwaltung = (Bankkonto) obj1;
			System.out.println("Vom File gelesen:");
			bankkontoVerwaltung.alleAnzeigen();
			// den String lesen
			Object obj2 = ois.readObject();
			System.out.println("Außerdem vom File gelesen:");
			System.out.println(obj2);

			return bankkontoVerwaltung;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

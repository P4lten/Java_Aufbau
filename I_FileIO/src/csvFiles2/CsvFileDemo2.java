package csvFiles2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import csvFiles1.Auto;

public class CsvFileDemo2 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveCsv("autos2.csv") : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData) {
			loadCsv("autos2.csv");
		}

	}

	static boolean saveCsv(String fileName) {

		Auto[] data = { new Auto("Mercedes", 29999.99, LocalDate.of(2020, 12, 15), 130, "Silber"),
				new Auto("Audi", 26599.99, LocalDate.of(2020, 10, 2), 120, "Rot"),
				new Auto("Opel", 20999.99, LocalDate.of(2020, 5, 21), 90, "Weiß") };
		System.out.println("Folgende Daten werden gespeichert:");
		for (Auto auto : data) {
			System.out.println(auto);
		}
		
		// mit Zeichenfolgen-Darstellung mit Regionaleinstellungen bei Primitiven Datentypen
		try(PrintWriter writer = new PrintWriter(new FileWriter(fileName, Charset.forName("UTF-8")))){
			for (Auto auto : data) {
				// %n ... plattformspezifisch 
				writer.printf("%d;%s;%.2f;%s;%d;%s%n", auto.getNr(),auto.getMarke(),auto.getPreis(),
						auto.getErzeugt(),auto.getLeistung(),auto.getFarbe());
				
			}
			return true;
		}catch (Exception e) {
			System.out.println("Fehler beim Speichern" + e);
		}

		// TODO Daten speichern
		return false;
	}

	static void loadCsv(String fileName) {
		List<Auto> alleAutos = new ArrayList<>();
		// Zeilenweise lesen
		try (BufferedReader reader = new BufferedReader(new FileReader(fileName, Charset.forName("UTF-8")))){
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				// eine Scanner verwenden, um die Daten zu lesen
				Scanner csvScanner = new Scanner(line);
				csvScanner.useDelimiter(";");
//				String values[] = line.split(";");
				Auto a = new Auto();
				a.setNr(csvScanner.nextInt()); 					// Nummer Int
				a.setMarke(csvScanner.next()); 					// Marke String
				a.setPreis(csvScanner.nextDouble());			// Preis double
				a.setErzeugt(LocalDate.parse(csvScanner.next()));// Erzeugt LocalDate
				a.setLeistung(csvScanner.nextInt()); 			// Leistung int
				a.setFarbe(csvScanner.next());					// Farbe String
				alleAutos.add(a);
				
				csvScanner.close();
			}
		}catch(Exception e) {
			System.out.println("Fehler beim Einlesen: " + e);
			e.printStackTrace();
		}
		System.out.println("Folgende Daten wurden gelesen");
		for (Auto auto : alleAutos) {
			System.out.println(auto);
		}
	}

}

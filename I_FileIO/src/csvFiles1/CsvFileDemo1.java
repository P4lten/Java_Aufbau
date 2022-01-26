package csvFiles1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvFileDemo1 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveCsv("autos1.csv") : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData) {
			loadCsv("autos1.csv");
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
		
		//mit Standart Java Zeuichenfolgen Darstellung der primitiven Typen sichern
		// ohne regionaleinstellungen
		try(PrintWriter writer = new PrintWriter(new FileWriter(fileName, Charset.forName("UTF-8")))){
			for (Auto auto : data) {
				writer.print(auto.getNr() + ";");
				writer.print(auto.getMarke() + ";");
				writer.print(auto.getPreis() + ";");
				writer.print(auto.getErzeugt() + ";");
				writer.print(auto.getLeistung() + ";");
				writer.println(auto.getFarbe());
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
				String values[] = line.split(";");
				Auto a = new Auto();
				a.setNr(Integer.parseInt(values[0])); // Nummer Int
				a.setMarke(values[1]); 					// Marke String
				a.setPreis(Double.parseDouble(values[2]));// Preis double
				a.setErzeugt(LocalDate.parse(values[3]));// Erzeugt LocalDate
				a.setLeistung(Integer.parseInt(values[4])); // Leistung int
				a.setFarbe(values[5]);						// Farbe String
				alleAutos.add(a);
				
				
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

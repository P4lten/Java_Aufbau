package csvFiles3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class CsvFileDemo3 {

	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		Locale region = Locale.JAPAN;
		String filename = "autos_jp.csv";
		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveCsv(filename, region) : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData) {
			loadCsv(filename, region);
		}

	}

	static boolean saveCsv(String fileName, Locale region) {

		Auto[] data = { new Auto("Mercedes", 29999.99, LocalDate.of(2020, 12, 15), 130, "Silber"),
				new Auto("Audi", 26599.99, LocalDate.of(2020, 10, 2), 120, "Rot"),
				new Auto("Opel", 20999.99, LocalDate.of(2020, 5, 21), 90, "Weiß") };
		System.out.println("Folgende Daten werden gespeichert:");
		for (Auto auto : data) {
			System.out.println(auto);
		}
		
		// Format für den Preis
		NumberFormat preisFormat = getNumberFormat_Preis(region);
		DateTimeFormatter dateFormat = getDateFormat(region);
		
		//mit hilfe der Fromatter in Text umwandeln (mit regionaleinstellungen)
		try(PrintWriter writer = new PrintWriter(new FileWriter(fileName, Charset.forName("UTF-8")))){
			for (Auto auto : data) {
				writer.print(auto.getNr() + ";");
				writer.print(auto.getMarke() + ";");
				// Numberformat für die Ausgabe des Datums
				writer.print(preisFormat.format(auto.getPreis()) + ";");
				// DateTimeFormatter für die Ausgabe des Datums
				writer.print(dateFormat.format(auto.getErzeugt()) + ";");
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

	static void loadCsv(String fileName, Locale region) {
		// Format für den Preis
		NumberFormat preisFormat = getNumberFormat_Preis(region);
		DateTimeFormatter dateFormat = getDateFormat(region);
		
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
				// mit Formatter einlesen
				a.setPreis(preisFormat.parse(values[2]).doubleValue());// Preis double
				// mit Formatter einlesen
				a.setErzeugt(LocalDate.parse(values[3], dateFormat));// Erzeugt LocalDate
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
	
	private static NumberFormat getNumberFormat_Preis(Locale region) {
		// formatter-Instanz holen (ohne Locale verwendet es die Regionaleinstellungen des Computers)
		//NumberFormat fmt = NumberFormat.getNumberInstance();
		NumberFormat fmt = NumberFormat.getNumberInstance(region);
		// genau 2 Nachkommastellen
		fmt.setMaximumFractionDigits(2);
		fmt.setMinimumFractionDigits(2);
		// 1000-er Trennzeichen: ja
		fmt.setGroupingUsed(true);
		return fmt;
	}

	
	private static DateTimeFormatter getDateFormat(Locale region) {
//		nur das Datum, in mittleren Format
		return DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(region);
	}
	
	
}

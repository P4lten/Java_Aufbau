package xml;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.Scanner;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import xml.model.Auto;
import xml.model.Fahrrad;
import xml.model.Fahrzeuge;
import xml.model.LKW;

public class JAXBDemo {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveData("fahrzeuge.xml") : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData)
			readData("fahrzeuge.xml");
	}

	private static boolean saveData(String fileName) {

		Fahrzeuge fz = new Fahrzeuge();
		fz.getFahrzeuge().add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
		fz.getFahrzeuge().add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
		fz.getFahrzeuge().add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

		System.out.println("Folgende Fahrzeuge werden gespeichert");
		for (int i = 0; i < fz.getFahrzeuge().size(); ++i) {
			System.out.println(fz.getFahrzeuge().get(i));
		}

		// Daten speichern (Brückenklasse statt FileWriter)
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName), "UTF-8")){
			// JAXBContext für das Speichern und Laden von einem Fahrzeuge-Objekt erzeugen
			JAXBContext xmlContext = JAXBContext.newInstance(Fahrzeuge.class);
			// Objekt für die Serialisierung holen
			Marshaller serializer =  xmlContext.createMarshaller();
			
			serializer.marshal(fz, writer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	private static boolean readData(String fileName) {
		// TODO: Daten laden
		try {

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
}

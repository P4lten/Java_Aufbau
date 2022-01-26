package serialization;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Scanner;
import serialization.model.Auto;
import serialization.model.Fahrrad;
import serialization.model.Fahrzeuge;
import serialization.model.LKW;

public class ObjectIODemo {
	private static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		System.out.print("Daten speichern? j/n");
		boolean saveData = scanner.nextLine().charAt(0) == 'j';
		boolean readData = saveData ? saveData("fahrzeuge.dat") : true;

		if (saveData) {
			System.out.println("Weiter mit Return");
			scanner.nextLine();
		}
		if (readData)
			readData("fahrzeuge.dat");
	}

	private static boolean saveData(String fileName) {
		Fahrzeuge fz = new Fahrzeuge();
		fz.add(new Auto("Mercedes", 30000, LocalDate.of(2019, 12, 15), 110, "Silber"));
		fz.add(new LKW("MAN", 150000, LocalDate.of(2020, 10, 2), 320, "Rot", 8000, 100, 3));
		fz.add(new Fahrrad("KTM", 2000, LocalDate.of(2020, 5, 21), 27, "Mountainbike"));

		System.out.println("Folgende Fahrzeuge werden gespeichert");
		fz.showAll();

		// TODO: Objekte speichern
		// eine ObjectOutputStream erzeugen, der Daten in das angegebene File schreibt
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			// das Fahrzeuge-Objekt in den Stream schreiben
			oos.writeObject(fz);
			// weitere Daten schreiben
			oos.writeObject("Alle Daten wurden gespeichert");

			System.out.println("Serialisierung erfolgreich!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	private static boolean readData(String fileName) {
		// Objekte einlesen
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

			// das Fahrzeuge-Objekt lesen
			Object obj1 = ois.readObject();
			if (!(obj1 instanceof Fahrzeuge)) {
				System.out.println("Ungültiges File, erwartet wurde ein Fahrzeuge-Object");
				return false;
			}

			Fahrzeuge fahrzeugVerwaltung = (Fahrzeuge) obj1;
			System.out.println("Vom File gelesen:");
			fahrzeugVerwaltung.showAll();
			// den String lesen
			Object obj2 = ois.readObject();
			System.out.println("Außerdem vom File gelesen:");
			System.out.println(obj2);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}

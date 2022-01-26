package serialization.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Serializable
// - kennzeichnet die Klasse als serialisierbar -> sie darf
// von den Object-Streams verarbeitet werden
// - ist ein Marker Interface (enthält keine abstrakten Methoden)
public class FahrzeugVerwaltung implements Serializable {

	// Version von unserem Dokument-Format
	private static final long serialVersionUID = 1L;
	private Map<Integer, Fahrzeug> fahrzeuge = new HashMap<>();

	private String fileName;

	public FahrzeugVerwaltung(String fileName) {
		this.fileName = fileName;
	}

	public Map<Integer, Fahrzeug> getFahrzeuge() {
		return fahrzeuge;
	}

	// ein Fahrzeug in der Map einfügen
	public void add(Fahrzeug fz) {
		fahrzeuge.put(fz.getNr(), fz);
	}

	public void showAll() {
		fahrzeuge.values().forEach(System.out::println);
	}

	public void loadData() throws ClassNotFoundException, IOException {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {

			// das Fahrzeuge-Objekt lesen
			Object obj1 = ois.readObject();
			fahrzeuge = (Map<Integer, Fahrzeug>) obj1;

//			if (!(obj1 instanceof Fahrzeuge)) {
//				System.out.println("Ungültiges File, erwartet wurde ein Fahrzeuge-Object");
//				return null;
//			}

			System.out.printf("%d Fahrzeuge geladen \n", fahrzeuge.size());
			// den nextNr lesen(int-Wert binär lesen)
			Fahrzeug.initNextNr(ois.readInt());
			
//			Object obj2 = ois.readObject();
//			System.out.println("Außerdem vom File gelesen:");
//			System.out.println(obj2);

			// die Exceptions hier nicht fangen, sodern weitergeben
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
		}

	}

	public void saveData() throws IOException {

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
			// das Fahrzeuge-Objekt in den Stream schreiben
			oos.writeObject(fahrzeuge);
//			// weitere Daten schreiben
//			oos.writeObject("Alle Daten wurden gespeichert");
			// und die akt. nextNr ins File schreiben
			oos.writeInt(Fahrzeug.getNextNr());
			
			

			System.out.println("Serialisierung erfolgreich!");

		}
	}

}

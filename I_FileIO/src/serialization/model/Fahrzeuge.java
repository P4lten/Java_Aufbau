package serialization.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

// Serializable
// - kennzeichnet die Klasse als serialisierbar -> sie darf
// von den Object-Streams verarbeitet werden
// - ist ein Marker Interface (enthält keine abstrakten Methoden)
public class Fahrzeuge implements Serializable {

	// Version von unserem Dokument-Format
	private static final long serialVersionUID = 1L;
	private final Map<Integer, Fahrzeug> fahrzeuge = new HashMap<>();

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
}

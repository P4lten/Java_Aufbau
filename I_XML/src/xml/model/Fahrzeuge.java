package xml.model;


import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
// es sollen auch die Felder von Auto, LKW und Fahrrad verarbeitet werden
@XmlSeeAlso({Auto.class, LKW.class, Fahrrad.class })
public class Fahrzeuge {

	
	// name: der Name für jedes Fahrzeug-Element
	@XmlElement(name = "fahrzeug")
	private final List<Fahrzeug> fahrzeuge = new ArrayList<>();
	
	// die nächstmögliche Nummer, der Nummernzähler für dieses Fahrzeuge-Objekt
	private int nextNummer;

	public List<Fahrzeug> getFahrzeuge() {
		return fahrzeuge;
	}
	
	public int getNextNummer() {
		return nextNummer;
	}
	
	public void setNextNummer(int nextNummer) {
		this.nextNummer = nextNummer;
	}

}

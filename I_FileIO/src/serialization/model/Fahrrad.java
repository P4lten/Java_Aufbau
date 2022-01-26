package serialization.model;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
public class Fahrrad extends Fahrzeug {
	private static final long serialVersionUID = 1;
	
	private double rahmengroesse;

	private String typ;
	
	transient private double rahmengroesseCm;

	public Fahrrad(String marke, double preis, LocalDate erzeugt, double rahmengroesse, String typ) {
		super(marke, preis, erzeugt);
		this.rahmengroesse = rahmengroesse;
		this.rahmengroesseCm = calcRahmengroesseCm();
		this.typ = typ;
	}

	private double calcRahmengroesseCm() {
		return rahmengroesse * 2.54;
	}

	public double getRahmengroesse() {
		return rahmengroesse;
	}

	public void setRahmengroesse(double rahmengroesse) {
		this.rahmengroesse = rahmengroesse;
		this.rahmengroesseCm = calcRahmengroesseCm();

	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}
	
	@Override
	public String toString() {
		return super.toString() + 
			String.format("%n\tRahmengröße: %.2f Zoll (%.2f cm); Typ: %s", rahmengroesse, rahmengroesseCm, typ);
	}

	// Custum Serialization: die Methode wird automatisch für das Objekt aufgerufen,
	// wenn sie in dieser Signatur vorhanden ist
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		System.out.println("Custom Serialization für Klasse Fahrrad");
		// die Daten für das Objekt lesen
		ois.defaultReadObject();
		// das transient-Feld initialisieren
		rahmengroesseCm = calcRahmengroesseCm();
	}
	
//	// so würde die entsprechende writeObject-Methode aussehen
//	public void writeObject(ObjectOutputStream oos) throws IOException{
//		System.out.println("Custom Serialization für Klasse Fahrrad (speichern");
//		oos.defaultWriteObject();
//	}
	
}

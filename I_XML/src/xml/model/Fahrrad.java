package xml.model;

import java.time.LocalDate;

import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.annotation.XmlTransient;

public class Fahrrad extends Fahrzeug {

	private double rahmengroesse;

	private String typ;

	// dieses Feld nicht verarbeiten
	@XmlTransient
	private double rahmengroesseCm;

	public Fahrrad(String marke, double preis, LocalDate erzeugt, double rahmengroesse, String typ) {
		super(marke, preis, erzeugt);
		this.rahmengroesse = rahmengroesse;
		this.rahmengroesseCm = calcRahmengroesseCm();
		this.typ = typ;
	}
	
	// Default-Konstruktor ist erforderlich für Unmarshaller.unmarshal
	public Fahrrad() {
		//this.rahmengroesseCm = calcRahmengroesseCm();
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



	@SuppressWarnings("unused")
	private void afterUnmarshal(Unmarshaller u, Object parent) {
		System.out.println("afterUnmarshal für Fahrrad");
		this.rahmengroesseCm = calcRahmengroesseCm();
	}
}

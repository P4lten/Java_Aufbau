package xml.model;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.XmlAttribute;

public abstract class Fahrzeug {
	private static int nextNr = 1;
	
	// ein Feld/Property kann auch als XML-Attribut gespeichert werden
	@XmlAttribute
	private int nr;

	private String marke;

	private double preis;
	
	private LocalDate erzeugt;
	
	public Fahrzeug(String marke, double preis, LocalDate erzeugt) {
		super();
		this.marke = marke;
		this.preis = preis;
		this.erzeugt = erzeugt;
		nr = nextNr++;
	}
	
	public Fahrzeug() {
		
	}

	public static int getNextNr() {
		return nextNr;
	}
	
	static void initNextNr(int nr) {
		nextNr = nr;
	}
	
	public int getNr() {
		return nr;
	}
	
//	// setter für die Nummer
//	/*public */void setNr(int nr) {
//		this.nr = nr;
//	}

	public String getMarke() {
		return marke;
	}

	public void setMarke(String marke) {
		this.marke = marke;
	}

	public double getPreis() {
		return preis;
	}

	public void setPreis(double preis) {
		this.preis = preis;
	}

	public LocalDate getErzeugt() {
		return erzeugt;
	}

	public void setErzeugt(LocalDate erzeugt) {
		this.erzeugt = erzeugt;
	}

	@Override
	public String toString() {
		return String.format("Nr=%d; Marke=%s; Preis=%.2f; Erzeugt=%s", nr, marke, preis, erzeugt);
	}

}

package csvFiles1;

import java.time.LocalDate;

public class Auto {
	private static int nextNr = 1;
	private int nr;
	private String marke;

	private double preis;

	private LocalDate erzeugt;

	private int leistung;

	private String farbe;

	// Konstruktor für CSV
	public Auto() {

	}

	public Auto(String marke, double preis, LocalDate erzeugt, int leistung, String farbe) {
		this.marke = marke;
		this.preis = preis;
		this.erzeugt = erzeugt;
		this.leistung = leistung;
		this.farbe = farbe;
		nr = nextNr++;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

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

	public int getLeistung() {
		return leistung;
	}

	public void setLeistung(int leistung) {
		this.leistung = leistung;
	}

	public String getFarbe() {
		return farbe;
	}

	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}

	@Override
	public String toString() {
		return String.format("Nr=%d; Marke=%s; Preis=%.2f; Erzeugt=%s%n\tLeistung: %d kW; Farbe: %s",
				nr, marke, preis, erzeugt, leistung, farbe);
	}
}

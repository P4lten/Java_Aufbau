package bankverwaltung;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

public abstract class Bankkonto implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Map<Integer, Bankkonto> konten = new TreeMap<>();

	private static int nextKtNr;

	private LocalDate eröffnungsDatum;
	private double kontostand;
	private String name;
	private int kontonr;
	private float zinsen;
	private int uez_rahmen;

	public Bankkonto(String name, LocalDate eröffungnsDatum, double kontostand) {
		nextKtNr++;
		this.kontonr = nextKtNr;
		this.eröffnungsDatum = eröffungnsDatum;
		this.name = name;
		this.kontostand = kontostand;
	}

	abstract public void abheben(int kontonr, double wert);

	abstract public void einzahlen(int kontonr, double wert);

	abstract public void kontoInfo(int kontoNr);

	// die Nummernzählung ins File zu speichern
	public static int getNextKtNr() {
		return nextKtNr;
	}

	// und vom File zu laden
	public static void setNextKtNr(int ktNr) {
		nextKtNr = ktNr;
		System.out.println("Init: nextNr = " + nextKtNr);
	}

	public void alleAnzeigen() {
		konten.values().forEach(System.out::println);
	}

	public LocalDate getEröffnungsDatum() {
		return eröffnungsDatum;
	}

	public double getKontostand() {
		return kontostand;
	}

	public String getName() {
		return name;
	}

	public int getKontonr() {
		return kontonr;
	}

	public void setKontostand(double kontostand) {
		this.kontostand = kontostand;
	}

	public int getUez_rahmen() {
		return uez_rahmen;
	}

	public void setUez_rahmen(int uez_rahmen) {
		this.uez_rahmen = uez_rahmen;
	}

	public String toString() {

		return String.format("Kontonummer: " + getKontonr() + "\nInhaber: " + getName() + "\nAktueller Kontostand: "
				+ getKontostand());
	}

}

package serialization.model;


import java.io.Serializable;
import java.time.LocalDate;
public abstract class Fahrzeug implements Serializable {
	
	//private static final long serialVersionUID = -7696928967713796475L;
	private static final long serialVersionUID = 1;
	
	private static int nextNr = 1;
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
	// die Nummernzählung ins File zu speichern
	public static int getNextNr() {
		return nextNr;
	}
	// und vom File zu laden
	static void initNextNr(int nr) {
		nextNr = nr;
		System.out.println("Init: nextNr = "+ nextNr);

	}
	
	public int getNr() {
		return nr;
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
	@Override
	public String toString() {
		return String.format("Nr= %d; Marke= %s; Preis= %.2f; Erzeugt= %s", nr, marke, preis, erzeugt);
	}

}

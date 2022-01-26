package serialization.model;


import java.time.LocalDate;
public class Auto extends Fahrzeug {
	private static final long serialVersionUID = 1;
	
	private int leistung;

	private String farbe;
	
	public Auto(String marke, double preis, LocalDate erzeugt, int leistung, String farbe) {
		super(marke, preis, erzeugt);
		this.leistung = leistung;
		this.farbe = farbe;
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
		return super.toString() + String.format("%n\tLeistung: %d kW; Farbe: %s", leistung, farbe);
	}
}

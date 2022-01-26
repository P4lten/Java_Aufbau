package mitarbeiter.repository;

import java.time.LocalDate;

public class Experte extends Mitarbeiter {

	private String fachgebiet;

	public Experte(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double gehalt, String fachgebiet, int areaCode) {
		super(name, fachgebiet, geburtsdatum, eintrittsdatum, gehalt, areaCode);
		this.fachgebiet = fachgebiet;
	}

	@Override
	public double calcJahrGehalt() {

		return getGehalt() * 15;
	}
	
	public Experte() {
		
	}

	@Override
	public String mitarbeiterblatt() {

		return "MitarbeiterID: " + getMitarbeiterId() + "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum()
				+ "\nEintrittsdatum: " + getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\nFachgebiet: "
				+ fachgebiet + "\n";
	}
	@Override
	public String getFachgebiet() {
		return fachgebiet;
	}

	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}
}

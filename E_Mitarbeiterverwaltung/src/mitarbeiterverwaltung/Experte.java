package mitarbeiterverwaltung;

import java.time.LocalDate;


public class Experte extends Mitarbeiter {

	

	private String fachgebiet;
	

	
	public Experte(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double gehalt, String fachgebiet) {
		super(name, geburtsdatum, eintrittsdatum, gehalt);
		this.fachgebiet = fachgebiet;
	}
	

	@Override
	public double calcJahrGehalt() {
		
		return  getGehalt()*15;
	}

	

	@Override
	public String mitarbeiterblatt() {
		
		return "MitarbeiterID: " + getMitarbeiterId()+  "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum() + "\nEintrittsdatum: "
		+ getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\nFachgebiet: " + fachgebiet + "\n";
	}
	
	
}

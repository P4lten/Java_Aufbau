package mitarbeiterverwaltung;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Mitarbeiter {

	private static int zaehler;
	private int mitarbeiterId = 0;
	private String name;
	private LocalDate geburtsdatum;
	private LocalDate eintrittsdatum;
	private double gehalt;
	
	
	

	
	public Mitarbeiter(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double gehalt) {
		super();
		zaehler++;
		this.mitarbeiterId = zaehler;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.eintrittsdatum = eintrittsdatum;
		this.gehalt = gehalt;
	}

	public double calcMonatGehalt() {
		return getGehalt();
	}
		

	public double calcJahrGehalt() {
		return getGehalt() * 14;
	}

	public void calcErhoehung(float a) {
		double erhöhung = getGehalt()* a;
		System.out.printf("Der Bonus dieses Jahr betraegt %.2f€ \n", erhöhung);
	}

	public int anstellungsDauer() {
		long monateDazwischen = ChronoUnit.MONTHS.between(getEintrittsdatum(), LocalDate.now());
		return (int)monateDazwischen;
	}
	
	public String mitarbeiterblatt() {
		return "MitarbeiterID: " + getMitarbeiterId()+ "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum() + "\nEintrittsdatum: "
				+ getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\n";
			
	}

	public int getMitarbeiterId() {
		return mitarbeiterId;
	}

	public String getName() {
		return name;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public LocalDate getEintrittsdatum() {
		return eintrittsdatum;
	}

	public double getGehalt() {
		return gehalt;
	}

	public void setGehalt(double gehalt) {
		this.gehalt = gehalt;
	}

	
	public String toString() {
		return "MitarbeiterID: " + getMitarbeiterId()+ "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum() + "\nEintrittsdatum: "
				+ getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\n";
	}

}

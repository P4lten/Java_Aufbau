package mitarbeiter.repository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.xml.bind.annotation.XmlType;

@XmlType(name = "employee", propOrder = {"mitarbeiterId", "name", "geburtsdatum","eintrittsdatum", "typ", "areaCode", "city",
		"gehalt","comment", "fachgebiet", "bonus", "experte", "manager" })

public class Mitarbeiter {

	private static int zaehler;
	private int mitarbeiterId, areaCode;
	private String name, city, comment, fachgebiet;
	private LocalDate geburtsdatum;
	private LocalDate eintrittsdatum;
	private double gehalt, bonus;
	private Typ typ;
	private boolean experte, manager;


	public Mitarbeiter(String typ, String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double gehalt, int areaCode) {
		super();
		zaehler++;
		this.mitarbeiterId = zaehler;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.eintrittsdatum = eintrittsdatum;
		this.gehalt = gehalt;
		this.areaCode = areaCode;
	}
	
	public Mitarbeiter() {
		
	}

	public double calcMonatGehalt() {
		return getGehalt();
	}

	public double calcJahrGehalt() {
		return getGehalt() * 14;
	}

	public void calcErhoehung(float a) {
		double erhöhung = getGehalt() * a;
		System.out.printf("Der Bonus dieses Jahr betraegt %.2f€ \n", erhöhung);
	}

	public int anstellungsDauer() {
		long monateDazwischen = ChronoUnit.MONTHS.between(getEintrittsdatum(), LocalDate.now());
		return (int) monateDazwischen;
	}

	public String mitarbeiterblatt() {
		return "MitarbeiterID: " + getMitarbeiterId() + "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum()
				+ "\nEintrittsdatum: " + getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\n";

	}
	
	public boolean isExperte() {
		return experte;
	}
	
	public void setExperte(boolean experte) {
		this.experte = experte;
	}
	
	public boolean isManager() {
		return manager;
	}
	
	public void setManager(boolean manager) {
		this.manager = manager;
	}
	
	public String getFachgebiet() {
		return fachgebiet;
	}
	
	public void setFachgebiet(String fachgebiet) {
		this.fachgebiet = fachgebiet;
	}
		
	public double getBonus() {
		return bonus;
	}
	
	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	public Typ getTyp() {
		return typ;
	}

	public void setTyp(Typ typ) {
		this.typ = typ;
	}

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setMitarbeiterId(int mitarbeiterId) {
		this.mitarbeiterId = mitarbeiterId;
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
	
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public void setEintrittsdatum(LocalDate eintrittsdatum) {
		this.eintrittsdatum = eintrittsdatum;
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

	public void setName(String name) {
		this.name = name;
	}

	

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Student [id=");
		builder.append(getMitarbeiterId());
		builder.append(", name=");
		builder.append(getName());
		builder.append(", areaCode=");
		builder.append(getAreaCode());
		builder.append(", city=");
		builder.append(getCity());
		builder.append("\ntyp=");
		builder.append(getTyp());
		builder.append(", birthDate=");
		builder.append(getGeburtsdatum());
		builder.append("\neintrittsdatum=");
		builder.append(getEintrittsdatum());
		builder.append("\nGehalt=");
		builder.append(getGehalt());
		builder.append("\ncomment=");
		builder.append(getComment());
		builder.append("]");
		return builder.toString();
	}

}

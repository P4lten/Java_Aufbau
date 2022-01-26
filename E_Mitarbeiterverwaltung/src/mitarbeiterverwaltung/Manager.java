package mitarbeiterverwaltung;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Manager extends Mitarbeiter {

	private double bonus;

	public Manager(String name, LocalDate geburtsdatum, LocalDate eintrittsdatum, double gehalt, double bonus) {
		super(name, geburtsdatum, eintrittsdatum, gehalt);
		this.bonus = bonus;
	}

	@Override
	public double calcJahrGehalt() {

		return getGehalt() * 14 + bonus;
	}

	@Override
	public void calcErhoehung(float a) {
		if (anstellungsDauer() > 24) {
			double erhoehung = getGehalt() * a;
			setGehalt(erhoehung + getGehalt());
			double erBonus = getBonus() * a;
			bonus += erBonus;
			System.out.printf("Der Bonus wird um %.2f€ erhoeht\n", erBonus);
			System.out.printf("Das Monatsgehalt wird um %.2f€ erhoeht\n", erhoehung);
		} else {
			System.out.println("Gehaltserhoehung bekommt man erst nach einem Jahr!");
		}

	}

	@Override
	public String mitarbeiterblatt() {

		return "MitarbeiterID: " + getMitarbeiterId() + "\nName: " + getName() + "\nGeburtsdatum: " + getGeburtsdatum()
				+ "\nEintrittsdatum: " + getEintrittsdatum() + "\nGehalt: " + getGehalt() + "€" + "\nBonus: "
				+ getBonus() + "\n";
	}

	public double getBonus() {
		return bonus;
	}

}
